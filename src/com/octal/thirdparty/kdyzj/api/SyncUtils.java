package com.octal.thirdparty.kdyzj.api;

import org.apache.log4j.*;
import com.alibaba.fastjson.*; 

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class SyncUtils
{
    private static Logger logger;
    private String pubaccId;
    private String pubacckey;
    private String pushOpendId;
    private String key;
    static final char SBC_SPACE = '\u3000';
    static final char DBC_CHAR_START = '!';
    static final char DBC_SPACE = ' ';
    static final char DBC_CHAR_END = '~';
    static final int CONVERT_STEP = 65248;
    
    static {
        SyncUtils.logger = Logger.getLogger((Class)SyncUtils.class);
    }
    
    public SyncUtils() {
        this.pubaccId = PropertiesUtils.getInstance().getProperty("PubaccId").trim();
        this.pubacckey = PropertiesUtils.getInstance().getProperty("Pubacckey").trim();
        this.pushOpendId = PropertiesUtils.getInstance().getProperty("Push_OpenId").trim();
        this.key = PropertiesUtils.getInstance().getProperty("key").trim();
    }
    
    public void CheckErrorcode(final List<Map<String, Object>> resultData, final List<Map<String, Object>> errorData, final List<Map<String, Object>> rightData) {
        for (int i = 0; i < resultData.size(); ++i) {
            if (resultData.get(i).get("msgCode") != null) {
                final int result = Integer.parseInt(String.valueOf(resultData.get(i).get("msgCode")));
                switch (result) {
                    case 111: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 201: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 202: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 203: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 204: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 205: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 206: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 207: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 208: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 209: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 210: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 211: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 212: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 213: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 214: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 215: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 216: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 217: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 218: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 219: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 220: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 221: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 222: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                    case 223: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 224: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 225: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 226: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 227: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 228: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 230: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 231: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 232: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 233: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 234: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 235: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 236: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 237: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 300: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    case 301: {
                        errorData.add(resultData.get(i));
                        break;
                    }
                    default: {
                        rightData.add(resultData.get(i));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < errorData.size(); ++i) {
            SyncUtils.logger.error(JSON.toJSON((Object)errorData.get(i)));
        }
        for (int i = 0; i < rightData.size(); ++i) {
            SyncUtils.logger.info(JSON.toJSON((Object)rightData.get(i)));
        }
    }
    
    public String GetStatusType(final String oldStatus, final String newStatus) {
        String type = "0";
        if (oldStatus.trim().equals("1") && newStatus.trim().equals("0")) {
            type = "1";
        }
        else if (oldStatus.trim().equals("0") && newStatus.trim().equals("1")) {
            type = "2";
        }
        else if (oldStatus.trim().equals("1") && newStatus.trim().equals("2")) {
            type = "3";
        }
        else if (oldStatus.trim().equals("2") && newStatus.trim().equals("1")) {
            type = "4";
        }
        return type;
    }
    
    public boolean JudgeEquals(final Object value1, final Object value2) {
        return value1.toString().trim().equals(value2.toString().trim());
    }
    
    public boolean JudgeEquals(final Object value1, final List<String> value2) {
        return value2.contains(value1);
    }
    
    public String GetGender(final String gender) {
        String back = "0";
        if (gender.trim().equals("M")) {
            back = "1";
        }
        else if (gender.trim().equals("F")) {
            back = "2";
        }
        else {
            back = "3";
        }
        return back;
    }
    
    public void PageByListMap(final List<Map<String, Object>> totalData, final List<Map<String, Object>> currentData, final int countperpage, final int pagenum) {
        for (int i = 0; i < totalData.size(); ++i) {
            if (i >= (pagenum - 1) * countperpage && i < pagenum * countperpage) {
                currentData.add(totalData.get(i));
            }
            if (currentData.size() == countperpage) {
                break;
            }
        }
    }
    
    public void PageByListString(final List<String> totalData, final List<String> currentData, final int countperpage, final int pagenum) {
        for (int i = 0; i < totalData.size(); ++i) {
            if (i >= (pagenum - 1) * countperpage && i < pagenum * countperpage) {
                currentData.add(totalData.get(i));
            }
            if (currentData.size() == countperpage) {
                break;
            }
        }
    }
    
    public List<String> processDepartment(final List<String> departList) {
        final List<String> newdepartmentList = new ArrayList<String>();
        for (int i = 0; i < departList.size(); ++i) {
            final String deartment = departList.get(i);
            String tempMap = departList.get(i);
            if (!StringUtils.isEmpty(deartment)) {
                final StringBuilder tempMap2 = new StringBuilder();
                final String[] newDepts = deartment.replace("\\", "!").split("!");
                String[] array;
                for (int length = (array = newDepts).length, j = 0; j < length; ++j) {
                    final String de = array[j];
                    if (StringUtils.isEmpty(tempMap2.toString())) {
                        tempMap2.append(String.valueOf(de.trim()) + "!");
                    }
                    else {
                        tempMap2.append(String.valueOf(de.trim()) + "!");
                    }
                }
                final int len = tempMap2.toString().length();
                final String department = tempMap2.toString();
                tempMap = department.substring(0, len - 1);
                newdepartmentList.add(tempMap);
            }
        }
        return newdepartmentList;
    }
    
    public List<String> ConverNoNumberDepartment(final List<List<String>> allDepartment) {
        final List<String> noNumDepartment = new ArrayList<String>();
        for (int i = 0; i < allDepartment.size(); ++i) {
            switch (i) {
                case 0: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(allDepartment.get(i).get(j).split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 1: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(String.valueOf(allDepartment.get(i).get(j).split("\\\\")[0].split("\\.")[1]) + "\\" + allDepartment.get(i).get(j).split("\\\\")[1].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 2: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(String.valueOf(allDepartment.get(i).get(j).split("\\\\")[0].split("\\.")[1]) + "\\" + allDepartment.get(i).get(j).split("\\\\")[1].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[2].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(String.valueOf(allDepartment.get(i).get(j).split("\\\\")[0].split("\\.")[1]) + "\\" + allDepartment.get(i).get(j).split("\\\\")[1].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[2].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[3].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 4: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(String.valueOf(allDepartment.get(i).get(j).split("\\\\")[0].split("\\.")[1]) + "\\" + allDepartment.get(i).get(j).split("\\\\")[1].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[2].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[3].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[4].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                default: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartment.add(String.valueOf(allDepartment.get(i).get(j).split("\\\\")[0].split("\\.")[1]) + "\\" + allDepartment.get(i).get(j).split("\\\\")[1].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[2].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[3].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[4].split("\\.")[1] + "\\" + allDepartment.get(i).get(j).split("\\\\")[5].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return noNumDepartment;
    }
    
    public List<List<String>> ConverNoNumberDepartmentForSort(final List<List<String>> allDepartment) {
        final List<List<String>> allnoNumDepartment = new ArrayList<List<String>>();
        final List<String> noNumDepartmento2 = new ArrayList<String>();
        final List<String> noNumDepartmento3 = new ArrayList<String>();
        final List<String> noNumDepartmento4 = new ArrayList<String>();
        final List<String> noNumDepartmento5 = new ArrayList<String>();
        final List<String> noNumDepartmento6 = new ArrayList<String>();
        final List<String> noNumDepartmento7 = new ArrayList<String>();
        for (int i = 0; i < allDepartment.size(); ++i) {
            switch (i) {
                case 0: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento2.add(allDepartment.get(i).get(j).split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 1: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento3.add(String.valueOf(allDepartment.get(i).get(j).split("!")[0].split("\\.")[1]) + "!" + allDepartment.get(i).get(j).split("!")[1].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 2: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento4.add(String.valueOf(allDepartment.get(i).get(j).split("!")[0].split("\\.")[1]) + "!" + allDepartment.get(i).get(j).split("!")[1].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[2].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento5.add(String.valueOf(allDepartment.get(i).get(j).split("!")[0].split("\\.")[1]) + "!" + allDepartment.get(i).get(j).split("!")[1].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[2].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[3].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                case 4: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento6.add(String.valueOf(allDepartment.get(i).get(j).split("!")[0].split("\\.")[1]) + "!" + allDepartment.get(i).get(j).split("!")[1].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[2].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[3].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[4].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
                default: {
                    if (!allDepartment.get(i).isEmpty()) {
                        for (int j = 0; j < allDepartment.get(i).size(); ++j) {
                            noNumDepartmento7.add(String.valueOf(allDepartment.get(i).get(j).split("!")[0].split("\\.")[1]) + "!" + allDepartment.get(i).get(j).split("!")[1].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[2].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[3].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[4].split("\\.")[1] + "!" + allDepartment.get(i).get(j).split("!")[5].split("\\.")[1]);
                        }
                        break;
                    }
                    break;
                }
            }
        }
        allnoNumDepartment.add(noNumDepartmento2);
        allnoNumDepartment.add(noNumDepartmento3);
        allnoNumDepartment.add(noNumDepartmento4);
        allnoNumDepartment.add(noNumDepartmento5);
        allnoNumDepartment.add(noNumDepartmento6);
        allnoNumDepartment.add(noNumDepartmento7);
        return allnoNumDepartment;
    }
    
    public List<List<String>> SplitDepartmentandPinLongName(final List<String> departments) {
        final List<List<String>> alldepartmentsList = new ArrayList<List<String>>();
        final Set<String> departmento2 = new HashSet<String>();
        final Set<String> departmento3 = new HashSet<String>();
        final Set<String> departmento4 = new HashSet<String>();
        final Set<String> departmento5 = new HashSet<String>();
        final Set<String> departmento6 = new HashSet<String>();
        final Set<String> departmento7 = new HashSet<String>();
        for (int i = 0; i < departments.size(); ++i) {
            final String[] department = departments.get(i).split("\\\\");
            for (int j = 0; j < department.length; ++j) {
                switch (j) {
                    case 0: {
                        departmento2.add(department[j]);
                        break;
                    }
                    case 1: {
                        departmento3.add(department[j]);
                        break;
                    }
                    case 2: {
                        departmento4.add(department[j]);
                        break;
                    }
                    case 3: {
                        departmento5.add(department[j]);
                        break;
                    }
                    case 4: {
                        departmento6.add(department[j]);
                        break;
                    }
                    default: {
                        departmento7.add(department[j]);
                        break;
                    }
                }
            }
        }
        final List<String> listo2 = new ArrayList<String>();
        final List<String> listo3 = new ArrayList<String>();
        final List<String> listo4 = new ArrayList<String>();
        final List<String> listo5 = new ArrayList<String>();
        final List<String> listo6 = new ArrayList<String>();
        final List<String> listo7 = new ArrayList<String>();
        for (final String seto2 : departmento2) {
            listo2.add(seto2);
        }
        Collections.sort(listo2);
        for (final String seto3 : departmento3) {
            listo3.add(seto3);
        }
        Collections.sort(listo3);
        for (final String seto4 : departmento4) {
            listo4.add(seto4);
        }
        Collections.sort(listo4);
        for (final String seto5 : departmento5) {
            listo5.add(seto5);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo5.add(seto6);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo6.add(seto6);
        }
        Collections.sort(listo6);
        for (final String seto7 : departmento7) {
            listo7.add(seto7);
        }
        Collections.sort(listo7);
        List<String> longdepartmento2 = new ArrayList<String>();
        final List<String> longdepartmento3 = new ArrayList<String>();
        final List<String> longdepartmento4 = new ArrayList<String>();
        final List<String> longdepartmento5 = new ArrayList<String>();
        final List<String> longdepartmento6 = new ArrayList<String>();
        final List<String> longdepartmento7 = new ArrayList<String>();
        if (!listo2.isEmpty()) {
            longdepartmento2 = listo2;
        }
        if (!longdepartmento2.isEmpty() && !listo3.isEmpty()) {
            for (int k = 0; k < longdepartmento2.size(); ++k) {
                for (int l = 0; l < listo3.size(); ++l) {
                    if (listo3.get(l).split("\\.")[0].contains(longdepartmento2.get(k).split("\\.")[0])) {
                        longdepartmento3.add(String.valueOf(longdepartmento2.get(k)) + "\\" + listo3.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty()) {
            for (int k = 0; k < longdepartmento3.size(); ++k) {
                for (int l = 0; l < listo4.size(); ++l) {
                    if (listo4.get(l).split("\\.")[0].contains(longdepartmento3.get(k).split("\\.")[1].split("\\\\")[1])) {
                        longdepartmento4.add(String.valueOf(longdepartmento3.get(k)) + "\\" + listo4.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty()) {
            for (int k = 0; k < longdepartmento4.size(); ++k) {
                for (int l = 0; l < listo5.size(); ++l) {
                    if (listo5.get(l).split("\\.")[0].contains(longdepartmento4.get(k).split("\\.")[2].split("\\\\")[1])) {
                        longdepartmento5.add(String.valueOf(longdepartmento4.get(k)) + "\\" + listo5.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty() && !longdepartmento5.isEmpty() && !listo6.isEmpty()) {
            for (int k = 0; k < longdepartmento5.size(); ++k) {
                for (int l = 0; l < listo6.size(); ++l) {
                    if (listo6.get(l).split("\\.")[0].contains(longdepartmento5.get(k).split("\\.")[3].split("\\\\")[1])) {
                        longdepartmento6.add(String.valueOf(longdepartmento5.get(k)) + "\\" + listo6.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty() && !longdepartmento5.isEmpty() && !listo6.isEmpty() && !longdepartmento6.isEmpty() && !listo7.isEmpty()) {
            for (int k = 0; k < longdepartmento6.size(); ++k) {
                for (int l = 0; l < listo7.size(); ++l) {
                    if (listo7.get(l).split("\\.")[0].contains(longdepartmento6.get(k).split("\\.")[4].split("\\\\")[1])) {
                        longdepartmento7.add(String.valueOf(longdepartmento6.get(k)) + "\\" + listo7.get(l));
                    }
                }
            }
        }
        Collections.sort(longdepartmento2);
        Collections.sort(longdepartmento3);
        Collections.sort(longdepartmento4);
        Collections.sort(longdepartmento5);
        Collections.sort(longdepartmento6);
        Collections.sort(longdepartmento7);
        alldepartmentsList.add(longdepartmento2);
        alldepartmentsList.add(longdepartmento3);
        alldepartmentsList.add(longdepartmento4);
        alldepartmentsList.add(longdepartmento5);
        alldepartmentsList.add(longdepartmento6);
        alldepartmentsList.add(longdepartmento7);
        return alldepartmentsList;
    }
    
    public List<List<String>> BackTurnDepartmentForSort(final List<String> alldepartments) {
        final List<List<String>> alldepartmentsList = new ArrayList<List<String>>();
        final Set<String> departmento2 = new HashSet<String>();
        final Set<String> departmento3 = new HashSet<String>();
        final Set<String> departmento4 = new HashSet<String>();
        final Set<String> departmento5 = new HashSet<String>();
        final Set<String> departmento6 = new HashSet<String>();
        final Set<String> departmento7 = new HashSet<String>();
        for (int i = 0; i < alldepartments.size(); ++i) {
            final String[] department = alldepartments.get(i).split("\\\\");
            for (int j = 0; j < department.length; ++j) {
                switch (j) {
                    case 0: {
                        try {
                            departmento2.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o2\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 1: {
                        try {
                            departmento3.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o3\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 2: {
                        try {
                            departmento4.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o4\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 3: {
                        try {
                            departmento5.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o5\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 4: {
                        try {
                            departmento6.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o6\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 5: {
                        try {
                            departmento7.add(String.valueOf(department[j].split("\\.")[1]) + "." + department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o7\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    default: {
                        SyncUtils.logger.info((Object)("\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd,\ufffd\ufffd\u05af\ufffd\u3f36\ufffd\ufffd\ufffd\ufffd6:" + department));
                        break;
                    }
                }
            }
        }
        final List<String> sortlisto2 = new ArrayList<String>();
        final List<String> sortlisto3 = new ArrayList<String>();
        final List<String> sortlisto4 = new ArrayList<String>();
        final List<String> sortlisto5 = new ArrayList<String>();
        final List<String> sortlisto6 = new ArrayList<String>();
        final List<String> sortlisto7 = new ArrayList<String>();
        for (final String seto2 : departmento2) {
            sortlisto2.add(seto2);
        }
        Collections.sort(sortlisto2);
        for (final String seto3 : departmento3) {
            sortlisto3.add(seto3);
        }
        Collections.sort(sortlisto3);
        for (final String seto4 : departmento4) {
            sortlisto4.add(seto4);
        }
        Collections.sort(sortlisto4);
        for (final String seto5 : departmento5) {
            sortlisto5.add(seto5);
        }
        Collections.sort(sortlisto5);
        for (final String seto6 : departmento6) {
            sortlisto5.add(seto6);
        }
        Collections.sort(sortlisto5);
        for (final String seto6 : departmento6) {
            sortlisto6.add(seto6);
        }
        Collections.sort(sortlisto6);
        for (final String seto7 : departmento7) {
            sortlisto7.add(seto7);
        }
        Collections.sort(sortlisto7);
        final List<String> listo2 = new ArrayList<String>();
        final List<String> listo3 = new ArrayList<String>();
        final List<String> listo4 = new ArrayList<String>();
        final List<String> listo5 = new ArrayList<String>();
        final List<String> listo6 = new ArrayList<String>();
        final List<String> listo7 = new ArrayList<String>();
        for (int k = 0; k < sortlisto2.size(); ++k) {
            try {
                listo2.add((String.valueOf(sortlisto2.get(k).split("\\.")[1]) + "." + sortlisto2.get(k).split("\\.")[0]).trim());
            }
            catch (Exception ex) {}
        }
        for (int k = 0; k < sortlisto3.size(); ++k) {
            try {
                listo3.add((String.valueOf(sortlisto3.get(k).split("\\.")[1]) + "." + sortlisto3.get(k).split("\\.")[0]).trim());
            }
            catch (Exception e2) {
                SyncUtils.logger.error((Object)("\ufffd\ufffd\u05ea\ufffd\ufffd\u05af\ufffd\ufffd\u03e2\ufffd\ufffd\ufffd\ufffd:" + JSON.toJSONString((Object)sortlisto3)));
            }
        }
        for (int k = 0; k < sortlisto4.size(); ++k) {
            try {
                listo4.add((String.valueOf(sortlisto4.get(k).split("\\.")[1]) + "." + sortlisto4.get(k).split("\\.")[0]).trim());
            }
            catch (Exception e2) {
                SyncUtils.logger.error((Object)("\ufffd\ufffd\u05ea\ufffd\ufffd\u05af\ufffd\ufffd\u03e2\ufffd\ufffd\ufffd\ufffd:" + JSON.toJSONString((Object)sortlisto4)));
            }
        }
        for (int k = 0; k < sortlisto5.size(); ++k) {
            try {
                listo5.add((String.valueOf(sortlisto5.get(k).split("\\.")[1]) + "." + sortlisto5.get(k).split("\\.")[0]).trim());
            }
            catch (Exception e2) {
                SyncUtils.logger.error((Object)("\ufffd\ufffd\u05ea\ufffd\ufffd\u05af\ufffd\ufffd\u03e2\ufffd\ufffd\ufffd\ufffd:" + JSON.toJSONString((Object)sortlisto5)));
            }
        }
        for (int k = 0; k < sortlisto6.size(); ++k) {
            try {
                listo6.add((String.valueOf(sortlisto6.get(k).split("\\.")[1]) + "." + sortlisto6.get(k).split("\\.")[0]).trim());
            }
            catch (Exception e2) {
                SyncUtils.logger.error((Object)("\ufffd\ufffd\u05ea\ufffd\ufffd\u05af\ufffd\ufffd\u03e2\ufffd\ufffd\ufffd\ufffd:" + JSON.toJSONString((Object)sortlisto6)));
            }
        }
        for (int k = 0; k < sortlisto7.size(); ++k) {
            try {
                listo7.add((String.valueOf(sortlisto7.get(k).split("\\.")[1]) + "." + sortlisto7.get(k).split("\\.")[0]).trim());
            }
            catch (Exception e2) {
                SyncUtils.logger.error((Object)("\ufffd\ufffd\u05ea\ufffd\ufffd\u05af\ufffd\ufffd\u03e2\ufffd\ufffd\ufffd\ufffd:" + JSON.toJSONString((Object)sortlisto7)));
            }
        }
        alldepartmentsList.add(listo2);
        alldepartmentsList.add(listo3);
        alldepartmentsList.add(listo4);
        alldepartmentsList.add(listo5);
        alldepartmentsList.add(listo6);
        alldepartmentsList.add(listo7);
        return alldepartmentsList;
    }
    
    public List<List<String>> BackTurnDepartmentForSort1(final List<String> alldepartments) {
        final List<List<String>> alldepartmentsList = new ArrayList<List<String>>();
        final Map<String, String> departmentMapo2 = new HashMap<String, String>();
        final Map<String, String> departmentMapo3 = new HashMap<String, String>();
        final Map<String, String> departmentMapo4 = new HashMap<String, String>();
        final Map<String, String> departmentMapo5 = new HashMap<String, String>();
        final Map<String, String> departmentMapo6 = new HashMap<String, String>();
        final Map<String, String> departmentMapo7 = new HashMap<String, String>();
        for (int i = 0; i < alldepartments.size(); ++i) {
            final String[] department = alldepartments.get(i).split("\\\\");
            for (int j = 0; j < department.length; ++j) {
                switch (j) {
                    case 0: {
                        try {
                            departmentMapo2.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o2\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 1: {
                        try {
                            departmentMapo3.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o3\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 2: {
                        try {
                            departmentMapo4.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o4\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 3: {
                        try {
                            departmentMapo5.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o5\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 4: {
                        try {
                            departmentMapo6.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o6\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    case 5: {
                        try {
                            departmentMapo7.put(department[j].split("\\.")[1], department[j].split("\\.")[0]);
                        }
                        catch (Exception e) {
                            SyncUtils.logger.error((Object)("o7\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd:" + JSON.toJSON((Object)department)));
                        }
                        break;
                    }
                    default: {
                        SyncUtils.logger.info((Object)("\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\uda4e\udf32\ufffd\ufffdhd\ufffd\ufffd\u05af\ufffd\ufffd\ufffd\u0773\ufffd\ufffd\ufffd,\ufffd\ufffd\u05af\ufffd\u3f36\ufffd\ufffd\ufffd\ufffd6:" + department));
                        break;
                    }
                }
            }
        }
        final List<String> orgNumLinko2 = new LinkedList<String>();
        final List<String> orgNumLinko3 = new LinkedList<String>();
        final List<String> orgNumLinko4 = new LinkedList<String>();
        final List<String> orgNumLinko5 = new LinkedList<String>();
        final List<String> orgNumLinko6 = new LinkedList<String>();
        final List<String> orgNumLinko7 = new LinkedList<String>();
        final Set<Map.Entry<String, String>> entrieso2 = departmentMapo2.entrySet();
        for (final Map.Entry<String, String> entry : entrieso2) {
            orgNumLinko2.add(entry.getKey());
        }
        final Set<Map.Entry<String, String>> entrieso3 = departmentMapo3.entrySet();
        for (final Map.Entry<String, String> entry2 : entrieso3) {
            orgNumLinko3.add(entry2.getKey());
        }
        final Set<Map.Entry<String, String>> entrieso4 = departmentMapo4.entrySet();
        for (final Map.Entry<String, String> entry3 : entrieso4) {
            orgNumLinko4.add(entry3.getKey());
        }
        final Set<Map.Entry<String, String>> entrieso5 = departmentMapo5.entrySet();
        for (final Map.Entry<String, String> entry4 : entrieso5) {
            orgNumLinko5.add(entry4.getKey());
        }
        final Set<Map.Entry<String, String>> entrieso6 = departmentMapo6.entrySet();
        for (final Map.Entry<String, String> entry5 : entrieso6) {
            orgNumLinko6.add(entry5.getKey());
        }
        final Set<Map.Entry<String, String>> entrieso7 = departmentMapo7.entrySet();
        for (final Map.Entry<String, String> entry6 : entrieso7) {
            orgNumLinko7.add(entry6.getKey());
        }
        final List<String> departmentListo2 = new ArrayList<String>();
        final List<String> departmentListo3 = new ArrayList<String>();
        final List<String> departmentListo4 = new ArrayList<String>();
        final List<String> departmentListo5 = new ArrayList<String>();
        final List<String> departmentListo6 = new ArrayList<String>();
        final List<String> departmentListo7 = new ArrayList<String>();
        for (final String orgo2 : this.Sort(orgNumLinko2)) {
            departmentListo2.add((String.valueOf(departmentMapo2.get(orgo2)) + "." + orgo2).trim());
        }
        for (final String orgo3 : this.Sort(orgNumLinko3)) {
            departmentListo3.add((String.valueOf(departmentMapo3.get(orgo3)) + "." + orgo3).trim());
        }
        for (final String orgo4 : this.Sort(orgNumLinko4)) {
            departmentListo4.add((String.valueOf(departmentMapo4.get(orgo4)) + "." + orgo4).trim());
        }
        for (final String orgo5 : this.Sort(orgNumLinko5)) {
            departmentListo5.add((String.valueOf(departmentMapo5.get(orgo5)) + "." + orgo5).trim());
        }
        for (final String orgo6 : this.Sort(orgNumLinko6)) {
            departmentListo6.add((String.valueOf(departmentMapo6.get(orgo6)) + "." + orgo6).trim());
        }
        for (final String orgo7 : this.Sort(orgNumLinko7)) {
            departmentListo7.add((String.valueOf(departmentMapo7.get(orgo7)) + "." + orgo7).trim());
        }
        alldepartmentsList.add(departmentListo2);
        alldepartmentsList.add(departmentListo3);
        alldepartmentsList.add(departmentListo4);
        alldepartmentsList.add(departmentListo5);
        alldepartmentsList.add(departmentListo6);
        alldepartmentsList.add(departmentListo7);
        return alldepartmentsList;
    }
    
    public List<List<String>> SplitDepartmentByNameForSort(final List<String> departments) {
        final List<List<String>> alldepartmentsList = new ArrayList<List<String>>();
        final Set<String> departmento2 = new HashSet<String>();
        final Set<String> departmento3 = new HashSet<String>();
        final Set<String> departmento4 = new HashSet<String>();
        final Set<String> departmento5 = new HashSet<String>();
        final Set<String> departmento6 = new HashSet<String>();
        final Set<String> departmento7 = new HashSet<String>();
        for (int i = 0; i < departments.size(); ++i) {
            final String[] department = departments.get(i).split("\\\\");
            for (int j = 0; j < department.length; ++j) {
                switch (j) {
                    case 0: {
                        departmento2.add(department[j]);
                        break;
                    }
                    case 1: {
                        departmento3.add(department[j]);
                        break;
                    }
                    case 2: {
                        departmento4.add(department[j]);
                        break;
                    }
                    case 3: {
                        departmento5.add(department[j]);
                        break;
                    }
                    case 4: {
                        departmento6.add(department[j]);
                        break;
                    }
                    default: {
                        departmento7.add(department[j]);
                        break;
                    }
                }
            }
        }
        final List<String> listo2 = new ArrayList<String>();
        final List<String> listo3 = new ArrayList<String>();
        final List<String> listo4 = new ArrayList<String>();
        final List<String> listo5 = new ArrayList<String>();
        final List<String> listo6 = new ArrayList<String>();
        final List<String> listo7 = new ArrayList<String>();
        for (final String seto2 : departmento2) {
            listo2.add(seto2);
        }
        Collections.sort(listo2);
        for (final String seto3 : departmento3) {
            listo3.add(seto3);
        }
        Collections.sort(listo3);
        for (final String seto4 : departmento4) {
            listo4.add(seto4);
        }
        Collections.sort(listo4);
        for (final String seto5 : departmento5) {
            listo5.add(seto5);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo5.add(seto6);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo6.add(seto6);
        }
        Collections.sort(listo6);
        for (final String seto7 : departmento7) {
            listo7.add(seto7);
        }
        Collections.sort(listo7);
        alldepartmentsList.add(listo2);
        alldepartmentsList.add(listo3);
        alldepartmentsList.add(listo4);
        alldepartmentsList.add(listo5);
        alldepartmentsList.add(listo6);
        alldepartmentsList.add(listo7);
        return alldepartmentsList;
    }
    
    public List<List<String>> SplitDepartmentandPinLongNameForSort(final List<String> departments) {
        final List<List<String>> alldepartmentsList = new ArrayList<List<String>>();
        final Set<String> departmento2 = new HashSet<String>();
        final Set<String> departmento3 = new HashSet<String>();
        final Set<String> departmento4 = new HashSet<String>();
        final Set<String> departmento5 = new HashSet<String>();
        final Set<String> departmento6 = new HashSet<String>();
        final Set<String> departmento7 = new HashSet<String>();
        for (int i = 0; i < departments.size(); ++i) {
            final String[] department = departments.get(i).split("\\\\");
            for (int j = 0; j < department.length; ++j) {
                switch (j) {
                    case 0: {
                        departmento2.add(department[j]);
                        break;
                    }
                    case 1: {
                        departmento3.add(department[j]);
                        break;
                    }
                    case 2: {
                        departmento4.add(department[j]);
                        break;
                    }
                    case 3: {
                        departmento5.add(department[j]);
                        break;
                    }
                    case 4: {
                        departmento6.add(department[j]);
                        break;
                    }
                    default: {
                        departmento7.add(department[j]);
                        break;
                    }
                }
            }
        }
        final List<String> listo2 = new ArrayList<String>();
        final List<String> listo3 = new ArrayList<String>();
        final List<String> listo4 = new ArrayList<String>();
        final List<String> listo5 = new ArrayList<String>();
        final List<String> listo6 = new ArrayList<String>();
        final List<String> listo7 = new ArrayList<String>();
        for (final String seto2 : departmento2) {
            listo2.add(seto2);
        }
        Collections.sort(listo2);
        for (final String seto3 : departmento3) {
            listo3.add(seto3);
        }
        Collections.sort(listo3);
        for (final String seto4 : departmento4) {
            listo4.add(seto4);
        }
        Collections.sort(listo4);
        for (final String seto5 : departmento5) {
            listo5.add(seto5);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo5.add(seto6);
        }
        Collections.sort(listo5);
        for (final String seto6 : departmento6) {
            listo6.add(seto6);
        }
        Collections.sort(listo6);
        for (final String seto7 : departmento7) {
            listo7.add(seto7);
        }
        Collections.sort(listo7);
        List<String> longdepartmento2 = new ArrayList<String>();
        final List<String> longdepartmento3 = new ArrayList<String>();
        final List<String> longdepartmento4 = new ArrayList<String>();
        final List<String> longdepartmento5 = new ArrayList<String>();
        final List<String> longdepartmento6 = new ArrayList<String>();
        final List<String> longdepartmento7 = new ArrayList<String>();
        if (!listo2.isEmpty()) {
            longdepartmento2 = listo2;
        }
        if (!longdepartmento2.isEmpty() && !listo3.isEmpty()) {
            for (int k = 0; k < longdepartmento2.size(); ++k) {
                for (int l = 0; l < listo3.size(); ++l) {
                    if (listo3.get(l).split("\\.")[0].contains(longdepartmento2.get(k).split("\\.")[0])) {
                        longdepartmento3.add(String.valueOf(longdepartmento2.get(k)) + "!" + listo3.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty()) {
            for (int k = 0; k < longdepartmento3.size(); ++k) {
                for (int l = 0; l < listo4.size(); ++l) {
                    if (listo4.get(l).split("\\.")[0].contains(longdepartmento3.get(k).split("\\.")[1].split("!")[1])) {
                        longdepartmento4.add(String.valueOf(longdepartmento3.get(k)) + "!" + listo4.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty()) {
            for (int k = 0; k < longdepartmento4.size(); ++k) {
                for (int l = 0; l < listo5.size(); ++l) {
                    if (listo5.get(l).split("\\.")[0].contains(longdepartmento4.get(k).split("\\.")[2].split("!")[1])) {
                        longdepartmento5.add(String.valueOf(longdepartmento4.get(k)) + "!" + listo5.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty() && !longdepartmento5.isEmpty() && !listo6.isEmpty()) {
            for (int k = 0; k < longdepartmento5.size(); ++k) {
                for (int l = 0; l < listo6.size(); ++l) {
                    if (listo6.get(l).split("\\.")[0].contains(longdepartmento5.get(k).split("\\.")[3].split("!")[1])) {
                        longdepartmento6.add(String.valueOf(longdepartmento5.get(k)) + "!" + listo6.get(l));
                    }
                }
            }
        }
        if (!listo2.isEmpty() && !listo3.isEmpty() && !listo4.isEmpty() && !longdepartmento3.isEmpty() && !longdepartmento4.isEmpty() && !listo5.isEmpty() && !longdepartmento5.isEmpty() && !listo6.isEmpty() && !longdepartmento6.isEmpty() && !listo7.isEmpty()) {
            for (int k = 0; k < longdepartmento6.size(); ++k) {
                for (int l = 0; l < listo7.size(); ++l) {
                    if (listo7.get(l).split("\\.")[0].contains(longdepartmento6.get(k).split("\\.")[4].split("!")[1])) {
                        longdepartmento7.add(String.valueOf(longdepartmento6.get(k)) + "!" + listo7.get(l));
                    }
                }
            }
        }
        Collections.sort(longdepartmento2);
        Collections.sort(longdepartmento3);
        Collections.sort(longdepartmento4);
        Collections.sort(longdepartmento5);
        Collections.sort(longdepartmento6);
        Collections.sort(longdepartmento7);
        alldepartmentsList.add(longdepartmento2);
        alldepartmentsList.add(longdepartmento3);
        alldepartmentsList.add(longdepartmento4);
        alldepartmentsList.add(longdepartmento5);
        alldepartmentsList.add(longdepartmento6);
        alldepartmentsList.add(longdepartmento7);
        return alldepartmentsList;
    }
    
    public String ParePushMessage(final List<Map<String, String>> pushMessage, final String interfaceName) {
        final StringBuilder sb = new StringBuilder();
        final Map<String, String> interfaceMap = new HashMap<String, String>();
        final Map<String, String> errorMap = new HashMap<String, String>();
        final Map<String, String> rightMap = new HashMap<String, String>();
        final Map<String, String> backDataMap = new HashMap<String, String>();
        final Map<String, String> errormessageMap = new HashMap<String, String>();
        if (interfaceName.length() == 0 || interfaceName == null) {
            return sb.toString();
        }
        interfaceMap.put("interfaceName", interfaceName);
        for (int i = 0; i < pushMessage.size(); ++i) {
            if (pushMessage.get(i).get("interfaceName").trim().equals(interfaceName.trim())) {
                if (pushMessage.get(i).get("error") != null) {
                    try {
                        if (errorMap.get("error") != null) {
                            errorMap.put("error", new StringBuilder(String.valueOf(Integer.parseInt(errorMap.get("error")) + Integer.parseInt(pushMessage.get(i).get("error")))).toString());
                        }
                        else {
                            errorMap.put("error", pushMessage.get(i).get("error"));
                        }
                    }
                    catch (Exception e) {
                        SyncUtils.logger.error((Object)(String.valueOf(interfaceName) + "\ufffd\ufffderror\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd"));
                    }
                }
                if (pushMessage.get(i).get("right") != null) {
                    try {
                        if (rightMap.get("right") != null) {
                            rightMap.put("right", new StringBuilder(String.valueOf(Integer.parseInt(rightMap.get("right")) + Integer.parseInt(pushMessage.get(i).get("right")))).toString());
                        }
                        else {
                            rightMap.put("right", pushMessage.get(i).get("right"));
                        }
                    }
                    catch (Exception e) {
                        SyncUtils.logger.error((Object)(String.valueOf(interfaceName) + "\ufffd\ufffdright\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd"));
                    }
                }
                if (pushMessage.get(i).get("backData") != null) {
                    try {
                        if (backDataMap.get("backData") != null) {
                            backDataMap.put("backData", new StringBuilder(String.valueOf(Integer.parseInt(backDataMap.get("backData")) + Integer.parseInt(pushMessage.get(i).get("backData")))).toString());
                        }
                        else {
                            backDataMap.put("backData", pushMessage.get(i).get("backData"));
                        }
                    }
                    catch (Exception e) {
                        SyncUtils.logger.error((Object)(String.valueOf(interfaceName) + "\ufffd\ufffdbackData\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd"));
                    }
                }
                if (pushMessage.get(i).get("errormessage") != null) {
                    if (errormessageMap.get("errormessage") != null) {
                        errormessageMap.put("errormessage", String.valueOf(errormessageMap.get("errormessage")) + "," + pushMessage.get(i).get("errormessage"));
                    }
                    else {
                        errormessageMap.put("backData", pushMessage.get(i).get("errormessage"));
                    }
                }
            }
        }
        sb.append("\ufffd\u04ff\ufffd\ufffd\ufffd\ufffd\ufffd:" + interfaceName);
        if (errorMap.size() > 0) {
            sb.append("\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd:");
            sb.append(errorMap.get("error"));
        }
        if (rightMap.size() > 0) {
            sb.append("\ufffd\ufffd\u0237\ufffd\ufffd\ufffd\ufffd:");
            sb.append(rightMap.get("right"));
        }
        if (backDataMap.size() > 0) {
            sb.append("\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd:");
            sb.append(backDataMap.get("backData"));
        }
        if (errormessageMap.size() > 0) {
            sb.append("\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\u03e2:");
            sb.append(errormessageMap.get("errormessage"));
        }
        if (errorMap.size() > 0 || rightMap.size() > 0 || backDataMap.size() > 0 || errormessageMap.size() > 0) {
            return sb.toString();
        }
        sb.setLength(0);
        return sb.toString();
    }
    
    public List<String> GetAllHdDepartment(final Set<Map<String, String>> departments) {
        final Set<String> setO2 = new HashSet<String>();
        final Set<String> setO3 = new HashSet<String>();
        final Set<String> setO4 = new HashSet<String>();
        final Set<String> setO5 = new HashSet<String>();
        final Set<String> setO6 = new HashSet<String>();
        final Set<String> setO7 = new HashSet<String>();
        final List<String> alldepartments = new ArrayList<String>();
        for (final Map<String, String> department : departments) {
            if (!department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null) {
                setO2.add(String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit"));
            }
        }
        for (final Map<String, String> department : departments) {
            if (!department.get("division").trim().equals("") && !department.get("division_name").trim().equals("") && department.get("division") != null && department.get("division_name") != null && !department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null) {
                for (final String departmentO2 : setO2) {
                    if ((String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit")).trim().equals(departmentO2)) {
                        setO3.add((String.valueOf(departmentO2) + "\\" + department.get("division_name") + "." + department.get("division")).trim());
                    }
                }
            }
        }
        for (final Map<String, String> department : departments) {
            if (!department.get("division").trim().equals("") && !department.get("division_name").trim().equals("") && department.get("division") != null && department.get("division_name") != null && !department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null && !department.get("department").trim().equals("") && !department.get("department_name").trim().equals("") && department.get("department") != null && department.get("department_name") != null) {
                for (final String departmentO3 : setO3) {
                    if ((String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit") + "\\" + department.get("division_name") + "." + department.get("division")).trim().equals(departmentO3)) {
                        setO4.add((String.valueOf(departmentO3) + "\\" + department.get("department_name") + "." + department.get("department")).trim());
                    }
                }
            }
        }
        for (final Map<String, String> department : departments) {
            if (!department.get("division").trim().equals("") && !department.get("division_name").trim().equals("") && department.get("division") != null && department.get("division_name") != null && !department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null && !department.get("department").trim().equals("") && !department.get("department_name").trim().equals("") && department.get("department") != null && department.get("department_name") != null && !department.get("customString2").trim().equals("") && !department.get("customString2_name").trim().equals("") && department.get("customString2") != null && department.get("customString2_name") != null) {
                for (final String departmentO4 : setO4) {
                    if ((String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit") + "\\" + department.get("division_name") + "." + department.get("division") + "\\" + department.get("department_name") + "." + department.get("department")).trim().equals(departmentO4)) {
                        setO5.add((String.valueOf(departmentO4) + "\\" + department.get("customString2_name") + "." + department.get("customString2")).trim());
                    }
                }
            }
        }
        for (final Map<String, String> department : departments) {
            if (!department.get("division").trim().equals("") && !department.get("division_name").trim().equals("") && department.get("division") != null && department.get("division_name") != null && !department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null && !department.get("department").trim().equals("") && !department.get("department_name").trim().equals("") && department.get("department") != null && department.get("department_name") != null && !department.get("customString2").trim().equals("") && !department.get("customString2_name").trim().equals("") && department.get("customString2") != null && department.get("customString2_name") != null && !department.get("customString3").trim().equals("") && !department.get("customString3_name").trim().equals("") && department.get("customString3") != null && department.get("customString3_name") != null) {
                for (final String departmentO5 : setO5) {
                    if ((String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit") + "\\" + department.get("division_name") + "." + department.get("division") + "\\" + department.get("department_name") + "." + department.get("department") + "\\" + department.get("customString2_name") + "." + department.get("customString2")).trim().equals(departmentO5)) {
                        setO6.add((String.valueOf(departmentO5) + "\\" + department.get("customString3_name") + "." + department.get("customString3")).trim());
                    }
                }
            }
        }
        for (final Map<String, String> department : departments) {
            if (!department.get("division").trim().equals("") && !department.get("division_name").trim().equals("") && department.get("division") != null && department.get("division_name") != null && !department.get("businessUnit").trim().equals("") && !department.get("businessUnit_name").trim().equals("") && department.get("businessUnit") != null && department.get("businessUnit_name") != null && !department.get("department").trim().equals("") && !department.get("department_name").trim().equals("") && department.get("department") != null && department.get("department_name") != null && !department.get("customString2").trim().equals("") && !department.get("customString2_name").trim().equals("") && department.get("customString2") != null && department.get("customString2_name") != null && !department.get("customString3").trim().equals("") && !department.get("customString3_name").trim().equals("") && department.get("customString3") != null && department.get("customString3_name") != null && !department.get("customString4").trim().equals("") && !department.get("customString4_name").trim().equals("") && department.get("customString4") != null && department.get("customString4_name") != null) {
                for (final String departmentO6 : setO6) {
                    if ((String.valueOf(department.get("businessUnit_name")) + "." + department.get("businessUnit") + "\\" + department.get("division_name") + "." + department.get("division") + "\\" + department.get("department_name") + "." + department.get("department") + "\\" + department.get("customString2_name") + "." + department.get("customString2") + "\\" + department.get("customString3_name") + "." + department.get("customString3")).trim().equals(departmentO6)) {
                        setO7.add((String.valueOf(departmentO6) + "\\" + department.get("customString4_name") + "." + department.get("customString4")).trim());
                    }
                }
            }
        }
        for (final String department2 : setO2) {
            alldepartments.add(department2);
        }
        for (final String department2 : setO3) {
            alldepartments.add(department2);
        }
        for (final String department2 : setO4) {
            alldepartments.add(department2);
        }
        for (final String department2 : setO5) {
            alldepartments.add(department2);
        }
        for (final String department2 : setO6) {
            alldepartments.add(department2);
        }
        for (final String department2 : setO7) {
            alldepartments.add(department2);
        }
        return alldepartments;
    }
    
    public static String bj2qj(final String src) {
        if (src == null) {
            return src;
        }
        final StringBuilder buf = new StringBuilder(src.length());
        final char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; ++i) {
            if (ca[i] == ' ') {
                buf.append('\u3000');
            }
            else if (ca[i] >= '!' && ca[i] <= '~') {
                buf.append((char)(ca[i] + '\ufee0'));
            }
            else {
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }
    
    public List<String> Sort(final List<String> orgNum) {
        for (int i = 0; i < orgNum.size() - 1; ++i) {
            for (int j = 0; j < orgNum.size() - i - 1; ++j) {
                final List<String> list1 = new ArrayList<String>();
                final List<String> list2 = new ArrayList<String>();
                final StringBuilder nums1 = new StringBuilder();
                final StringBuilder nums2 = new StringBuilder();
                for (int k = 0; k < orgNum.get(j).toCharArray().length; ++k) {
                    if (JudgeWord(String.valueOf(orgNum.get(j).toCharArray()[k]))) {
                        if (nums1.length() > 0) {
                            list1.add(nums1.toString());
                            nums1.setLength(0);
                        }
                        list1.add(String.valueOf(orgNum.get(j).toCharArray()[k]));
                    }
                    if (JudgeNum(String.valueOf(orgNum.get(j).toCharArray()[k]))) {
                        nums1.append(String.valueOf(orgNum.get(j).toCharArray()[k]));
                    }
                    if (k == orgNum.get(j).toCharArray().length - 1 && nums1.length() > 0) {
                        list1.add(nums1.toString());
                        nums1.setLength(0);
                    }
                }
                for (int k = 0; k < orgNum.get(j + 1).toCharArray().length; ++k) {
                    if (JudgeWord(String.valueOf(orgNum.get(j + 1).toCharArray()[k]))) {
                        if (nums2.length() > 0) {
                            list2.add(nums2.toString());
                            nums2.setLength(0);
                        }
                        list2.add(String.valueOf(orgNum.get(j + 1).toCharArray()[k]));
                    }
                    if (JudgeNum(String.valueOf(orgNum.get(j + 1).toCharArray()[k]))) {
                        nums2.append(String.valueOf(orgNum.get(j + 1).toCharArray()[k]));
                    }
                    if (k == orgNum.get(j + 1).toCharArray().length - 1 && nums2.length() > 0) {
                        list2.add(nums2.toString());
                        nums2.setLength(0);
                    }
                }
                if (list1.size() > list2.size()) {
                    try {
                        for (int l1 = 0; l1 <= list2.size(); ++l1) {
                            if (JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1))) {
                                final int num1 = Integer.parseInt(list1.get(l1));
                                final int num2 = Integer.parseInt(list2.get(l1));
                                if (num1 != num2) {
                                    if (num1 > num2) {
                                        final String temp = orgNum.get(j);
                                        orgNum.set(j, orgNum.get(j + 1));
                                        orgNum.set(j + 1, temp);
                                        break;
                                    }
                                    break;
                                }
                            }
                            else if ((JudgeNum(list1.get(l1)) && !JudgeNum(list2.get(l1))) || (!JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1)))) {
                                if (JudgeNum(list2.get(l1))) {
                                    final String temp = orgNum.get(j);
                                    orgNum.set(j, orgNum.get(j + 1));
                                    orgNum.set(j + 1, temp);
                                    break;
                                }
                                break;
                            }
                            else if (JudgeWord(list1.get(l1)) && JudgeWord(list2.get(l1))) {
                                final int ascii1 = list1.get(l1).toCharArray()[0];
                                final int ascii2 = list2.get(l1).toCharArray()[0];
                                if (ascii1 != ascii2) {
                                    if (ascii1 > ascii2) {
                                        final String temp = orgNum.get(j);
                                        orgNum.set(j, orgNum.get(j + 1));
                                        orgNum.set(j + 1, temp);
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        if (list1.size() > list2.size()) {
                            final String temp = orgNum.get(j);
                            orgNum.set(j, orgNum.get(j + 1));
                            orgNum.set(j + 1, temp);
                        }
                    }
                }
                else if (list1.size() < list2.size()) {
                    try {
                        for (int l1 = 0; l1 <= list1.size(); ++l1) {
                            if (JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1))) {
                                final int num1 = Integer.parseInt(list1.get(l1));
                                final int num2 = Integer.parseInt(list2.get(l1));
                                if (num1 != num2) {
                                    if (num1 > num2) {
                                        final String temp = orgNum.get(j);
                                        orgNum.set(j, orgNum.get(j + 1));
                                        orgNum.set(j + 1, temp);
                                        break;
                                    }
                                    break;
                                }
                            }
                            else if ((JudgeNum(list1.get(l1)) && !JudgeNum(list2.get(l1))) || (!JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1)))) {
                                if (JudgeNum(list2.get(l1))) {
                                    final String temp = orgNum.get(j);
                                    orgNum.set(j, orgNum.get(j + 1));
                                    orgNum.set(j + 1, temp);
                                    break;
                                }
                                break;
                            }
                            else if (JudgeWord(list1.get(l1)) && JudgeWord(list2.get(l1))) {
                                final int ascii1 = list1.get(l1).toCharArray()[0];
                                final int ascii2 = list2.get(l1).toCharArray()[0];
                                if (ascii1 != ascii2) {
                                    if (ascii1 > ascii2) {
                                        final String temp = orgNum.get(j);
                                        orgNum.set(j, orgNum.get(j + 1));
                                        orgNum.set(j + 1, temp);
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        if (list1.size() > list2.size()) {
                            final String temp = orgNum.get(j);
                            orgNum.set(j, orgNum.get(j + 1));
                            orgNum.set(j + 1, temp);
                        }
                    }
                }
                else {
                    for (int l1 = 0; l1 < list1.size(); ++l1) {
                        if (JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1))) {
                            final int num1 = Integer.parseInt(list1.get(l1));
                            final int num2 = Integer.parseInt(list2.get(l1));
                            if (num1 != num2) {
                                if (num1 > num2) {
                                    final String temp = orgNum.get(j);
                                    orgNum.set(j, orgNum.get(j + 1));
                                    orgNum.set(j + 1, temp);
                                    break;
                                }
                                break;
                            }
                        }
                        else if ((JudgeNum(list1.get(l1)) && !JudgeNum(list2.get(l1))) || (!JudgeNum(list1.get(l1)) && JudgeNum(list2.get(l1)))) {
                            if (JudgeNum(list2.get(l1))) {
                                final String temp = orgNum.get(j);
                                orgNum.set(j, orgNum.get(j + 1));
                                orgNum.set(j + 1, temp);
                                break;
                            }
                            break;
                        }
                        else {
                            final int ascii1 = list1.get(l1).toCharArray()[0];
                            final int ascii2 = list2.get(l1).toCharArray()[0];
                            if (ascii1 != ascii2) {
                                if (ascii1 > ascii2) {
                                    final String temp = orgNum.get(j);
                                    orgNum.set(j, orgNum.get(j + 1));
                                    orgNum.set(j + 1, temp);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return orgNum;
    }
    
    public static boolean JudgeWord(final String s) {
        final Pattern pattern = Pattern.compile("[a-zA-Z]+");
        final Matcher m = pattern.matcher(s);
        return m.matches();
    }
    
    public static boolean JudgeNum(final String s) {
        final Pattern pattern = Pattern.compile("^[0-9]*$");
        final Matcher m = pattern.matcher(s);
        return m.matches();
    }
}
