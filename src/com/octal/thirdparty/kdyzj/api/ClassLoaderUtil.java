package com.octal.thirdparty.kdyzj.api;

import java.lang.reflect.*;
import java.net.*;
import java.io.*;
import java.util.*;


public final class ClassLoaderUtil
{
    private static Method addURL;
    private static URLClassLoader system;
    
    static {
        ClassLoaderUtil.addURL = initAddMethod();
        ClassLoaderUtil.system = (URLClassLoader)ClassLoader.getSystemClassLoader();
    }
    
    private static final Method initAddMethod() {
        try {
            final Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            return add;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static final void loopFiles(final File file, final List<File> files) {
        if (file.isDirectory()) {
            final File[] tmps = file.listFiles();
            File[] array;
            for (int length = (array = tmps).length, i = 0; i < length; ++i) {
                final File tmp = array[i];
                loopFiles(tmp, files);
            }
        }
        else if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {
            files.add(file);
        }
    }
    
    public static final void loadJarFile(final File file) {
        try {
            ClassLoaderUtil.addURL.invoke(ClassLoaderUtil.system, file.toURI().toURL());
            System.out.println("Load Jar\ufffd\ufffd" + file.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void loadJarPath(final String path) {
        final List<File> files = new ArrayList<File>();
        final File lib = new File(path);
        loopFiles(lib, files);
        if (files.isEmpty()) {
            System.out.println("no jar in lib:" + lib.getAbsolutePath());
        }
        for (final File file : files) {
            loadJarFile(file);
        }
    }
}
