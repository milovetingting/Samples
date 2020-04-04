package com.wangyz.shell;

import java.io.File;
import java.io.FilenameFilter;

import com.wangyz.shell.util.DexUtils;
import com.wangyz.shell.util.EncryptUtils;
import com.wangyz.shell.util.FileUtils;
import com.wangyz.shell.util.SignUtils;
import com.wangyz.shell.util.ZipUtil;

public class Main {

	public static void main(String[] args) {
		try {
			// APK
			File apkFile = new File("input/app-debug.apk");
			// 壳AAR
			File shellFile = new File("input/shell-debug.aar");
			// 判断文件是否存在
			if (!apkFile.exists() || !shellFile.exists()) {
				System.out.println("apkFile or shellFile missing");
				return;
			}

			// *************解压APK*************
			System.out.println("解压APK");
			// 先删除输出文件夹下的所有文件
			File outputDir = new File("output/");
			if (outputDir.exists()) {
				FileUtils.deleteAllInDir(outputDir);
			}
			// 创建apk的解压目录
			File apkUnzipDir = new File("output/unzip/apk/");
			if (!apkUnzipDir.exists()) {
				apkUnzipDir.mkdirs();
			}
			// 解压APK
			ZipUtil.unZip(apkFile, apkUnzipDir);
			// 删除META-INF/CERT.RSA,META-INF/CERT.SF,META-INF/MANIFEST.MF
			File certRSA = new File(apkUnzipDir, "/META-INF/CERT.RSA");
			certRSA.delete();
			File certSF = new File(apkUnzipDir, "/META-INF/CERT.SF");
			certSF.delete();
			File manifestMF = new File(apkUnzipDir, "/META-INF/MANIFEST.MF");
			manifestMF.delete();
			// 获取dex文件
			File[] apkFiles = apkUnzipDir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File file, String s) {
					return s.endsWith(".dex");
				}
			});
			for (int i = apkFiles.length - 1; i >= 0; i--) {
				File file = apkFiles[i];
				String name = file.getName();
				System.out.println("dex:" + name);
				String bakName = name.substring(0, name.indexOf(".dex")) + "_bak.dex";
				System.out.println("备份dex:" + bakName);
				bakName = file.getParent() + File.separator + name.substring(0, name.indexOf(".dex")) + "_bak.dex";

				// 加密dex文件
				EncryptUtils.encrypt(file.getAbsolutePath(), bakName);
				System.out.println("加密dex:" + name);
				// 删除原文件
				file.delete();
			}
			// *************解压APK*************

			// *************解压壳AAR*************
			// 创建壳AAR的解压目录
			System.out.println("解压壳AAR");
			File shellUnzipDir = new File("output/unzip/shell/");
			if (!shellUnzipDir.exists()) {
				shellUnzipDir.mkdirs();
			}
			// 解压AAR
			ZipUtil.unZip(shellFile, shellUnzipDir);
			// 将jar转成dex
			System.out.println("将jar转成dex");
			File shellJar = new File(shellUnzipDir, "classes.jar");
			File shellDex = new File(apkUnzipDir, "classes.dex");
			DexUtils.dxCommand(shellJar, shellDex);
			// 打包
			System.out.println("打包APK");
			File unsignedApk = new File("output/unsigned.apk");
			ZipUtil.zip(apkUnzipDir, unsignedApk);
			// 删除解压目录

			FileUtils.delete("output/unzip/");
			System.out.println("签名APK");
			File signedApk = new File("output/signed.apk");
			SignUtils.signature(unsignedApk, signedApk, "keystore/android.keystore");
			System.out.println("Finished!!!");

			// *************解压壳AAR*************
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
