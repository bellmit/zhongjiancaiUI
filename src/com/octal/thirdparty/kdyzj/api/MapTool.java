package com.octal.thirdparty.kdyzj.api;

import java.util.*;

public class MapTool
{
    public static String toJson(final Map<String, String> map) {
        if (map == null) {
            return null;
        }
        final StringBuffer result = new StringBuffer();
        result.append("{");
        final Object[] keys = map.keySet().toArray();
        for (int i = 0; i < keys.length; ++i) {
            final String clumnName = (String)keys[i];
            Object value = map.get(clumnName);
            if (value == null) {
                value = "";
            }
            if (i == 0) {
                result.append("\"" + clumnName + "\":\"" + value.toString() + "\"");
            }
            else {
                result.append(",\"" + clumnName + "\":\"" + value.toString() + "\"");
            }
        }
        result.append("}");
        return result.toString();
    }
}
