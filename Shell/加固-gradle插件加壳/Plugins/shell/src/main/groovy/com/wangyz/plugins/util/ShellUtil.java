package com.wangyz.plugins.util;

import java.io.File;

/**
 * @author wangyz
 * @time 2020/3/31 14:26
 * @description ShellUtil
 */
public class ShellUtil {

    public static void shell(String apk, String aar, String root, String keystore, String keyStorePassword, String keyPassword, String alias) {
        try {
            // APK
            File apkFile = new File(apk);
            // 壳AAR
            File shellFile = new File(aar);
            // 判断文件是否存在
            if (!apkFile.exists() || !shellFile.exists()) {
                System.out.println("apkFile or shellFile missing");
                return;
            }

            // *************解压APK*************
            System.out.println("unzip APK");
            // 先删除输出文件夹下的所有文件
            File outputDir = new File(root, "/output/");
            if (outputDir.exists()) {
                FileUtils.deleteAllInDir(outputDir);
            }
            // 创建apk的解压目录
            File apkUnzipDir = new File(root, "/output/unzip/apk/");
            if (!apkUnzipDir.exists()) {
                apkUnzipDir.mkdirs();
            }
            // 解压APK
            ZipUtil.unZip(apkFile, apkUnzipDir);
            // 获取dex文件
            File[] apkFiles = apkUnzipDir.listFiles((file, s) -> s.endsWith(".dex"));
            for (int i = apkFiles.length - 1; i >= 0; i--) {
                File file = apkFiles[i];
                String name = file.getName();
                System.out.println("dex:" + name);
                String bakName = name.substring(0, name.indexOf(".dex")) + "_bak.dex";
                System.out.println("bak dex:" + bakName);
                bakName = file.getParent() + File.separator + name.substring(0, name.indexOf(".dex")) + "_bak.dex";

                // 加密dex文件
                EncryptUtils.encrypt(file.getAbsolutePath(), bakName);
                System.out.println("encrypt dex:" + name);
                // 删除原文件
                file.delete();
            }
            // *************解压APK*************

            // *************解压壳AAR*************
            // 创建壳AAR的解压目录
            System.out.println("unzip shell AAR");
            File shellUnzipDir = new File(root, "/output/unzip/shell/");
            if (!shellUnzipDir.exists()) {
                shellUnzipDir.mkdirs();
            }
            // 解压AAR
            ZipUtil.unZip(shellFile, shellUnzipDir);
            // 将jar转成dex
            System.out.println("convert jar to dex");
            File shellJar = new File(shellUnzipDir, "classes.jar");
            File shellDex = new File(apkUnzipDir, "classes.dex");
            DexUtils.dxCommand(shellJar, shellDex);
            // 打包
            System.out.println("pack APK");
            File unsignedApk = new File(root, "/output/unsigned.apk");
            ZipUtil.zip(apkUnzipDir, unsignedApk);
            // 删除解压目录

            FileUtils.delete(new File(root, "output/unzip/"));
            System.out.println("sign APK");
            File signedApk = new File(root, "output/signed.apk");
            SignUtils.signature(unsignedApk, signedApk, keystore, keyStorePassword, keyPassword, alias);
            System.out.println("Finished!!!");

            // *************解压壳AAR*************
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
