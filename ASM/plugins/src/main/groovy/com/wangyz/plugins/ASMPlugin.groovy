package com.wangyz.plugins

import com.android.build.api.transform.*
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

class ASMPlugin extends Transform implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(this)
    }

    @Override
    String getName() {
        return "ASMPlugin"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        println('--------------------ASMPlugin transform start--------------------')
        def startTime = System.currentTimeMillis()
        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        //删除旧的输出
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }
        //遍历inputs
        inputs.each { input ->
            //遍历directoryInputs
            input.directoryInputs.each {
                directoryInput -> handleDirectoryInput(directoryInput, outputProvider)
            }
            //遍历jarInputs
            input.jarInputs.each {
                jarInput -> handleJarInput(jarInput, outputProvider)
            }
        }
        def time = (System.currentTimeMillis() - startTime) / 1000
        println('-------------------- ASMPlugin transform end --------------------')
        println("ASMPlugin cost $time s")
    }

    /**
     * 处理目录下的class文件
     * @param directoryInput
     * @param outputProvider
     */
    static void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        //是否为目录
        if (directoryInput.file.isDirectory()) {
            //列出目录所有文件（包含子文件夹，子文件夹内文件）
            directoryInput.file.eachFileRecurse {
                file ->
                    def name = file.name
                    if (isClassFile(name)) {
                        println("-------------------- handle class file:<$name> --------------------")
                        ClassReader classReader = new ClassReader(file.bytes)
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor classVisitor = new ActivityClassVisitor(classWriter)
                        classReader.accept(classVisitor, org.objectweb.asm.ClassReader.EXPAND_FRAMES)
                        byte[] bytes = classWriter.toByteArray()
                        FileOutputStream fileOutputStream = new FileOutputStream(file.parentFile.absolutePath + File.separator + name)
                        fileOutputStream.write(bytes)
                        fileOutputStream.close()
                    }
            }
        }
        def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
        FileUtils.copyDirectory(directoryInput.file, dest)
    }

    /**
     * 处理Jar中的class文件
     * @param jarInput
     * @param outputProvider
     */
    static void handleJarInput(JarInput jarInput, TransformOutputProvider outputProvider) {
        if (jarInput.file.getAbsolutePath().endsWith(".jar")) {
            //重名名输出文件,因为可能同名,会覆盖
            def jarName = jarInput.name
            def md5Name = DigestUtils.md5Hex(jarInput.file.absolutePath)
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4)
            }
            JarFile jarFile = new JarFile(jarInput.file)
            Enumeration enumeration = jarFile.entries()
            File tempFile = new File(jarInput.file.parent + File.separator + "temp.jar")
            //避免上次的缓存被重复插入
            if (tempFile.exists()) {
                tempFile.delete()
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tempFile))
            //保存
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = enumeration.nextElement()
                String entryName = jarEntry.name
                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream inputStream = jarFile.getInputStream(zipEntry)
                if (isClassFile(entryName)) {
                    println("-------------------- handle jar file:<$entryName> --------------------")
                    jarOutputStream.putNextEntry(zipEntry)
                    ClassReader classReader = new ClassReader(IOUtils.toByteArray(inputStream))
                    ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                    ClassVisitor classVisitor = new ActivityClassVisitor(classWriter)
                    classReader.accept(classVisitor, org.objectweb.asm.ClassReader.EXPAND_FRAMES)
                    byte[] bytes = classWriter.toByteArray()
                    jarOutputStream.write(bytes)
                } else {
                    jarOutputStream.putNextEntry(zipEntry)
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
            jarFile.close()
            def dest = outputProvider.getContentLocation(jarName + "_" + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
            FileUtils.copyFile(tempFile, dest)
            tempFile.delete()
        }
    }

    /**
     * 判断是否为需要处理class文件
     * @param name
     * @return
     */
    static boolean isClassFile(String name) {
        return (name.endsWith(".class") && !name.startsWith("R\$")
                && "R.class" != name && "BuildConfig.class" != name && name.contains("Activity"))
    }
}