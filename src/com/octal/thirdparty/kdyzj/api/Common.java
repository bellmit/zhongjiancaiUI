package com.octal.thirdparty.kdyzj.api;

import org.apache.log4j.*;
import org.apache.commons.codec.binary.*;


import java.security.*;
import java.io.*;

public class Common
{
    private static Logger log;
    private static String key;
    private static String src;
    
    static {
        Common.log = Logger.getLogger((Class)Common.class);
        Common.key = PropertiesUtils.getInstance().getProperty("key");
        Common.src = PropertiesUtils.getInstance().getProperty("KEY_PATH");
    }
    
    public static String enyte(final String data) {
        try {
            final InputStream in = PropertiesUtils.class.getResourceAsStream(String.valueOf(Common.key) + ".key");
            final ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            final byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            final byte[] b = swapStream.toByteArray();
            final PrivateKey restorePublicKey = RSAUtils.restorePrivateKey(b);
            final byte[] bytes = Base64.encodeBase64(RSAUtils.encryptLarger(data.getBytes(), restorePublicKey));
            return new String(bytes, "UTF-8");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    public static byte[] getBytes(final File file) {
        byte[] buffer = null;
        try {
            final FileInputStream fis = new FileInputStream(file);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            final byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return buffer;
    }
}
