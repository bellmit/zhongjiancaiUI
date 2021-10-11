package com.kingdee.eas.custom;

import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.framework.CoreBaseInfo;

import java.math.BigDecimal;
import java.text.*;
import com.kingdee.util.enums.*;

import java.util.*;

public class BillInfoUtil
{
    public static String getValue(final String fieldType, final String classname, final Object obj ) {
        try {
            if (obj == null || "null".equals(obj)) {
                return "";
            }
            if ("Enum".equals(fieldType)) {
                try {
                    final int intValue = UIRuleUtil.getIntValue(obj);
                    try {
                        final IntEnum enumValue = EnumUtils.getEnum((Class)Class.forName(classname), intValue);
                        return enumValue.toString();
                    }   
                    catch (Exception e3) {
                        return EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()).toString();
                    } 
                }
                catch (Exception e4) {
                	
                	try {
                		return EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()).toString();
                          
                    }
                    catch (Exception e3) {  
                    	Map<String, DynamicEnum> map = DynamicEnum.getEnumMap(classname); 
                    	Iterator<Map.Entry<String, DynamicEnum>> entries = map.entrySet().iterator(); 
                    	while (entries.hasNext()) { 
                    	    Map.Entry<String, DynamicEnum> entry = entries.next();
                    	    if(entry.getValue().getAlias().equals(obj)){
                    	    	return DynamicEnum.getEnumByName(classname, entry.getKey() ).toString();
                    	    }
                    	    if(entry.getValue().getValue().equals(obj)){
                    	    	return DynamicEnum.getEnumByName(classname, entry.getValue().getName()).toString();
                    	    }
                    	    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
                    	} 
                        //return DynamicEnum.getEnum(classname, obj.toString()).toString();
                        
                    }  
                    
                }
            }
            if ("Date".equals(fieldType)) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(UIRuleUtil.getDateValue(obj));
            }
            if ("TimeStamp".equals(fieldType)) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final Date date = sdf.parse(obj.toString());
                return sdf.format(date);
            }
            if (!"Boolean".equals(fieldType)) {
                return obj.toString();
            }
            if (!(obj instanceof String)) {
                final boolean flag = UIRuleUtil.getBooleanValue(obj);
                return flag ? "\u662f" : "\u5426";
            }
            if (((String)obj).toLowerCase().equals("true")) {
                return "\u662f";
            }
            return "\u5426";
        }
        catch (ParseException e2) {
            e2.printStackTrace();
            return "";
        }
    }
     
    public static CoreBaseInfo putValue(final int type ,final String title ,final String fieldType, final String classname, 
    		CoreBaseInfo  info ,final Object obj) {
        try {
            if (obj == null || "null".equals(obj) || "".equals(obj)) {
                return info;
            }
            if ("Enum".equals(fieldType)) {
                try {
                    final int intValue = UIRuleUtil.getIntValue(obj);
                    try {
                        final IntEnum enumValue = EnumUtils.getEnum((Class)Class.forName(classname), intValue);
                        if(type == 0 ){
                        	info.put(title, EnumUtils.getEnum((Class)Class.forName(classname), intValue));
                        }else{ 
                        	if(title.indexOf(".") >= 0){
                        		info.put(title.split("\\.")[1], EnumUtils.getEnum((Class)Class.forName(classname), intValue));
                        	}else{
                        		info.put(title, EnumUtils.getEnum((Class)Class.forName(classname), intValue));
                        	}
                        }
                        
                        return info;
                    }
                    catch (Exception e3) {
                    	
                    	
                    	try { 
                    		if(type == 0 ){
                        		info.put(title, EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString())); 
                            }else{
                            	if(title.indexOf(".") >= 0){Map<String, DynamicEnum> map = DynamicEnum.getEnumMap(classname); 
                            		info.put(title.split("\\.")[1], EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()));
                            	}else{
                            		info.put(title, EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()));
                            	}
                            	
                            }
                		}catch (Exception e2) {
                			Map<String, DynamicEnum> map = DynamicEnum.getEnumMap(classname); 
                        	Iterator<Map.Entry<String, DynamicEnum>> entries = map.entrySet().iterator(); 
                        	while (entries.hasNext()) { 
                        	    Map.Entry<String, DynamicEnum> entry = entries.next();
                        	    if(entry.getValue().getAlias().equals(obj.toString())){
                        	    	//jsonEntryInfo.put("coreid", DynamicEnum.getEnumByName(classname, entry.getKey()).getValue());
                        	    	if(title.indexOf(".") >= 0){ 
	                        			info.put(title.split("\\.")[1],entry.getValue().getValue());
	                            	}else{
	                            		info.put(title,entry.getValue().getValue());
	                            	}
                        	    }else if(entry.getValue().getValue().equals(obj.toString())){   
                        	    	if(title.indexOf(".") >= 0){ 
                            			info.put(title.split("\\.")[1],entry.getValue().getValue());
	                            	}else{
	                            		info.put(title,entry.getValue().getValue());
	                            	} 
                        	    } 
                        	}
                		} 
                		
                        return info;
                    }
                }
                catch (Exception e4) {
                	if(type == 0 ){
                    	//info.put(title, EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()));
                		info.put(title, DynamicEnum.getEnum(classname, obj.toString()));
                    }else{  
                    	//info.put(title.split("\\.")[1], EnumUtils.getEnumByValue((Class)Class.forName(classname), obj.toString()));
                    	if(title.indexOf(".") >= 0){
                    		info.put(title.split("\\.")[1], DynamicEnum.getEnum(classname, obj.toString()));
                    	}else{
                    		info.put(title, DynamicEnum.getEnum(classname, obj.toString()));
                    	}
                    	
                    
                    }
                    return info;
                }
            }
            if ("Date".equals(fieldType)) {
                //final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(type == 0 ){
                	info.put(title,UIRuleUtil.getDateValue(obj));
                }else{
                	info.put(title.split("\\.")[1], UIRuleUtil.getDateValue(obj));
                }
                return info;
            }
            if ("TimeStamp".equals(fieldType)) {
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final Date date = sdf.parse(obj.toString());
                
                if(type == 0 ){
                	info.put(title,date);
                }else{
                	info.put(title.split("\\.")[1], date);
                }
                return info;
                 
            }
            if ("Boolean".equals(fieldType) || "boolean".equals(fieldType)) {
            	if(type == 0 ){
                	info.put(title,UIRuleUtil.getBooleanValue(obj));
                }else{
                	info.put(title.split("\\.")[1], UIRuleUtil.getBooleanValue(obj));
                }
                return info; 
            }
            if ( "String".equals(fieldType) ) { 
                if(type == 0 ){
                	info.put(title,obj.toString());
                }else{
                	info.put(title.split("\\.")[1], obj.toString());
                }
                return info;
                 
            } 
            
            if ( "BigDecimal".equals(fieldType) ) { 
                if(type == 0 ){
                	info.put(title,new BigDecimal(obj.toString()));
                }else{
                	info.put(title.split("\\.")[1], new BigDecimal(obj.toString()));
                }
                return info;
                 
            } 
            
            if ( "F7".equals(fieldType) ) { 
            	PersonInfo perinfo = new  PersonInfo();
            	perinfo.setId(BOSUuid.read(obj.toString()));
                if(type == 0 ){
                	info.put(title,perinfo);
                }else{
                	info.put(title.split("\\.")[1], perinfo);
                }
                return info;
                 
            } 
            
            return info;
        }
        catch (ParseException e2) {
            e2.printStackTrace();
            return info;
        }
    }
}
