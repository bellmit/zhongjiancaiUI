package com.octal.thirdparty.kdyzj.api;

import java.util.*;
import org.apache.commons.lang.*;
import org.apache.commons.codec.digest.*;

import org.apache.commons.lang.StringUtils;
public class shacode
{
    public static String sha(final String... data) {
        Arrays.sort(data);
        return DigestUtils.shaHex(StringUtils.join((Object[])data));
    }
}
