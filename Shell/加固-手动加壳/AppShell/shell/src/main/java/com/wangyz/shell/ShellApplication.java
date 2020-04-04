package com.wangyz.shell;

import android.app.Application;
import android.content.Context;

import com.wangyz.shell.util.ClassLoaderUtil;
import com.wangyz.shell.util.EncryptUtils;
import com.wangyz.shell.util.ZipUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * @author wangyz
 * @time 2020/3/27 14:05
 * @description ShellApplication
 */
public class ShellApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            //获取应用APK
            File apkFile = new File(getApplicationInfo().sourceDir);
            //解压目录
            File apkUnzipDir = getDir("apk", Context.MODE_PRIVATE);
            apkUnzipDir = new File(apkUnzipDir, "unzip");
            //如果不存在，则解压
            if (!apkUnzipDir.exists()) {
                apkUnzipDir.mkdirs();
                //解压
                ZipUtils.unzipFile(apkFile, apkUnzipDir);
                //过滤所有.dex文件
                File[] files = apkUnzipDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".dex");
                    }
                });
                //解密
                File decryptDir = new File(apkUnzipDir, "decrypt");
                decryptDir.mkdirs();
                ArrayList<File> list = new ArrayList<>();
                for (File file : files) {
                    if (file.getName().endsWith("classes.dex")) {
                        list.add(file);
                    } else {
                        File decryptFile = new File(decryptDir, file.getName());
                        EncryptUtils.decrypt(file.getAbsolutePath(), decryptFile.getAbsolutePath());
                        //添加到list中
                        list.add(decryptFile);
                        //删除加密的dex文件
                        file.delete();
                    }
                }
                //加载.dex文件
                ClassLoaderUtil.loadDex(this, list);
            } else {
                ArrayList<File> list = new ArrayList<>();
                list.add(new File(apkUnzipDir, "classes.dex"));
                File decryptDir = new File(apkUnzipDir, "decrypt");
                File[] files = decryptDir.listFiles();
                for (File file : files) {
                    list.add(file);
                }
                //加载.dex文件
                ClassLoaderUtil.loadDex(this, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
