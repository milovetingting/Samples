package com.wangyz.shell.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SignUtils {

	private SignUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static void signature(File unsignedApk, File signedApk, String keyStore)
			throws InterruptedException, IOException {
		String cmd[] = { "cmd.exe", "/C ", "jarsigner", "-sigalg", "SHA1withRSA", "-digestalg", "SHA1", "-keystore",
				keyStore, "-storepass", "android", "-keypass", "android", "-signedjar", signedApk.getAbsolutePath(),
				unsignedApk.getAbsolutePath(), "android" };
		Process process = Runtime.getRuntime().exec(cmd);
		System.out.println("start sign");
		try {
			int waitResult = process.waitFor();
			System.out.println("waitResult: " + waitResult);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("process.exitValue() " + process.exitValue());
		if (process.exitValue() != 0) {
			InputStream inputStream = process.getErrorStream();
			int len;
			byte[] buffer = new byte[2048];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			System.out.println(new String(bos.toByteArray(), "gbk"));
			throw new RuntimeException("签名执行失败");
		}
		System.out.println("finish signed");
		process.destroy();
	}

}
