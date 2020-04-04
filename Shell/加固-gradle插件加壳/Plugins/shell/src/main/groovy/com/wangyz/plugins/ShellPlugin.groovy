package com.wangyz.plugins

import com.wangyz.plugins.util.ShellUtil
import org.gradle.api.Plugin
import org.gradle.api.Project

class ShellPlugin implements Plugin<Project> {

    def printLog(Object msg) {
        println("******************************")
        println(msg)
        println("******************************\n")
    }

    def createDir(Project project) {
        File shellDir = new File("${project.rootDir}/ShellAPK")
        if (!shellDir.exists()) {
            printLog("create dir")
            shellDir.mkdirs()
        }
    }

    def deleteDir(Project project) {
        File shellDir = new File("${project.rootDir}/ShellAPK")
        if (shellDir.exists()) {
            printLog("delete dir")
            shellDir.deleteDir()
        }
    }

    @Override
    void apply(Project project) {

        printLog('ShellPlugin apply')

        project.extensions.create("shellConfig", ShellConfig)

        project.afterEvaluate {
            project.tasks.matching {
                it.name == 'assembleRelease'
            }.each {
                task ->
                    printLog(task.name)

                    def shellProject = project.parent.findProject("${project.shellConfig.shellModuleName}")
                    printLog("shellProject:$shellProject")

                    File shellDir = new File("${project.rootDir}/ShellAPK")

                    File apkFile

                    File aarFile = new File("${shellProject.buildDir}/outputs/aar/shell-release.aar")

                    project.android.applicationVariants.all {
                        variant ->
                            variant.outputs.each {
                                output ->
                                    def outputFile = output.outputFile
                                    printLog("outputFile:${outputFile.getAbsolutePath()}")
                                    if (outputFile.name.contains("release")) {
                                        apkFile = outputFile
                                    }
                            }
                    }

                    task.doFirst {
                        //删除原来的文件夹
                        deleteDir(project)
                        //生成文件夹
                        createDir(project)
                        //生成aar
                        printLog("begin generate aar")
                        project.exec {
                            workingDir("../${project.shellConfig.shellModuleName}/")
                            commandLine('cmd', '/c', 'gradle', 'assembleRelease')
                        }
                        printLog("generate aar complete")

                        //复制文件
                        printLog("begin copy aar")
                        project.copy {
                            from aarFile
                            into shellDir
                        }
                        printLog("copy aar complete")
                    }

                    task.doLast {
                        printLog("begin copy apk")
                        //复制文件
                        project.copy {
                            from apkFile
                            into shellDir
                        }
                        printLog("copy ${apkFile.name} complete")

                        printLog("begin shell")

                        ShellUtil.shell(apkFile.getAbsolutePath(), aarFile.getAbsolutePath(), shellDir.getAbsolutePath(), project.shellConfig.keyStore, project.shellConfig.keyStorePassword, project.shellConfig.keyPassword, project.shellConfig.alias)

                        printLog("end shell")
                    }
            }
        }
    }

}