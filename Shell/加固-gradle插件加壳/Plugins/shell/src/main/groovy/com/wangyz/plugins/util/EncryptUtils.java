package com.wangyz.plugins.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptUtils {

    private static final byte PASSWORD = 'A';

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 加密
     *
     * @param input
     * @param output
     * @return
     */
    public static boolean encrypt(String input, String output) {
        boolean result = false;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File in = new File(input);
            if (!in.exists()) {
                return false;
            }
            fis = new FileInputStream(input);
            fos = new FileOutputStream(output);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    buffer[i] ^= PASSWORD;
                }
                fos.write(buffer, 0, len);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 解密
     *
     * @param input
     * @param output
     * @return
     */
    public static boolean decrypt(String input, String output) {
        boolean result = false;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File in = new File(input);
            if (!in.exists()) {
                return false;
            }
            fis = new FileInputStream(input);
            fos = new FileOutputStream(output);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    buffer[i] ^= PASSWORD;
                }
                fos.write(buffer, 0, len);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
