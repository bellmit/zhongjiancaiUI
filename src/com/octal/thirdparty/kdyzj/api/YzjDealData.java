package com.octal.thirdparty.kdyzj.api;

import org.apache.log4j.*;
import com.alibaba.fastjson.*; 
import org.apache.commons.codec.binary.*;
import java.security.*;
import java.io.*;

public class YzjDealData
{
    private static Logger logger;
    
    static {
        YzjDealData.logger = Logger.getLogger((Class)YzjDealData.class);
    }
    
    public YzjBackData SendYzj(final String eid, final Object data, final String url, final String interfaceName) {
        YzjBackData yzjBackDate = new YzjBackData();
        final SendDataToYzj sendData = new SendDataToYzj();
        sendData.setEid(eid);
        sendData.setNonce();
        final String dataStr = JSON.toJSONString(data);
        final String comString = this.enyte(dataStr);
        sendData.setData(comString);
        try {
            final String result = HttpClientTool.httpPost(url, MapUtils.convertBean(sendData));
            yzjBackDate = (YzjBackData)JSON.parseObject(result, (Class)YzjBackData.class);
            if (yzjBackDate.getSuccess() != null) {
                return yzjBackDate;
            }
            YzjDealData.logger.error(JSON.toJSON((Object)yzjBackDate));
        }
        catch (Exception e) {
            YzjDealData.logger.error((Object)interfaceName, (Throwable)e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return yzjBackDate;
    }
    
    public String enyte(final String data) {
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getResourceAsStream("32199.key");
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
            throw new RuntimeException(e.getLocalizedMessage());
        }
        catch (IOException e2) {
            throw new RuntimeException(e2.getLocalizedMessage());
        }
        catch (Exception e3) {
            throw new RuntimeException(e3.getLocalizedMessage());
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e4) {
                    throw new RuntimeException(e4.getLocalizedMessage());
                }
            }
        }
    }
    
    public byte[] getBytes(final File file) {
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
            throw new RuntimeException(e.getLocalizedMessage());
        }
        catch (IOException e2) {
            throw new RuntimeException(e2.getLocalizedMessage());
        }
        return buffer;
    }
}
