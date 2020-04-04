package com.wangyz.shell.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DexUtils {

	private DexUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static void dxCommand(File jar, File dex) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime
				.exec("cmd.exe /C dx --dex --output=" + dex.getAbsolutePath() + " " + jar.getAbsolutePath());

		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		}
		if (process.exitValue() != 0) {
			InputStream inputStream = process.getErrorStream();
			int len;
			byte[] buffer = new byte[2048];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			System.out.println(new String(bos.toByteArray(), "gbk"));
			throw new RuntimeException("dx run failed");
		}
		process.destroy();
	}

}
