package com.octal.thirdparty.kdyzj.api;

import java.util.*;
import java.beans.*;
import java.lang.reflect.*;

public class MapUtils
{
    public static Map<String, String> convertBean(final Object bean) {
        final Class<?> type = bean.getClass();
        final Map<String, String> returnMap = new HashMap<String, String>();
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(type);
            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                final PropertyDescriptor descriptor = propertyDescriptors[i];
                final String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    final Method readMethod = descriptor.getReadMethod();
                    try {
                        final String result = readMethod.invoke(bean, new Object[0]).toString();
                        if (result != null) {
                            returnMap.put(propertyName, result);
                        }
                        else {
                            returnMap.put(propertyName, "");
                        }
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                    }
                    catch (InvocationTargetException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        catch (IntrospectionException e4) {
            e4.printStackTrace();
        }
        return returnMap;
    }
}
