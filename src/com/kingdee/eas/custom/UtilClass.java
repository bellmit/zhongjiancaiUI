package com.kingdee.eas.custom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.mozilla.javascript.Context;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import bsh.This;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTMergeManager;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.util.KDTableUtil;
import com.kingdee.bos.ctrl.swing.KDDatePicker;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.bot.BOTMappingCollection;
import com.kingdee.bos.metadata.bot.BOTMappingFactory;
import com.kingdee.bos.metadata.bot.BOTMappingInfo;
import com.kingdee.bos.metadata.bot.BOTRelationCollection;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.sql.ParserException;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.btp.BTPManagerFactory;
import com.kingdee.eas.base.btp.BTPTransformResult;
import com.kingdee.eas.base.btp.IBTPManager;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.base.message.BMCMessageFactory;
import com.kingdee.eas.base.message.BMCMessageInfo;
import com.kingdee.eas.base.message.IBMCMessage;
import com.kingdee.eas.base.message.MsgBizType;
import com.kingdee.eas.base.message.MsgPriority;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.MsgType;
import com.kingdee.eas.base.netctrl.IMutexServiceControl;
import com.kingdee.eas.base.netctrl.MutexParameter;
import com.kingdee.eas.base.netctrl.MutexServiceControlFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeGroupCollection;
import com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeGroupFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CtrlUnitCollection;
import com.kingdee.eas.basedata.org.CtrlUnitFactory;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.basedata.org.PositionInfo;
import com.kingdee.eas.basedata.person.PersonCollection;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fm.common.FMIsqlFacadeFactory;
import com.kingdee.eas.fm.common.IFMIsqlFacade;
import com.kingdee.eas.framework.CoreBillBaseInfo;
import com.kingdee.eas.framework.client.CoreBillEditUI;
import com.kingdee.eas.framework.client.EditUI;
import com.kingdee.eas.hr.emp.PersonPositionFactory;
import com.kingdee.eas.hr.emp.PersonPositionInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.Uuid;
/***
 * EAS开发工具类
 * @author 赵戬
 *
 */
public class UtilClass {
    /***
     * 信息提示框
     * @param value 提示内容
     */
    public static void alert(String value){
        MsgBox.showInfo(value);
        SysUtil.abort();
    }

    /***
     * 信息提示框(带详细信息)
     * @param value 提示内容
     */
    public static void alert2(String Title,String info){
        MsgBox.showDetailAndOK(null, Title, info, 0);
        SysUtil.abort();
    }

    /***
     * 信息提示框（带提示）
     * @return value 提示内容
     */
    public static boolean alertReturn(String value){
        return MsgBox.isYes(MsgBox.showConfirm2(value));
    }

    /***
     * 程序停止运行
     */
    public static void Stop(){
        SysUtil.abort();
    }

    /**
     * 单据数据加锁
     * @param billId 单据ID
     */
    public static void addBillDataLock(String BillFID){
        IMutexServiceControl mutex = MutexServiceControlFactory.getRemoteInstance();
        mutex.requestBizObjIDForUpdate(BillFID);
    }

    /**
     * 单据数据解锁
     * @param billId 单据ID
     */
    public static void removeBillDataLock(String BillFID){
        IMutexServiceControl mutex = MutexServiceControlFactory.getRemoteInstance();
        mutex.releaseObjIDForUpdate(BillFID);
    }

    /**
     * 根据ID获取数据是否加锁
     * @param id 单据编号
     * @return true 已加锁 or false 未加锁
     */
    public static boolean getBillDataLockStatus(String BillFID){
        IMutexServiceControl mutex = MutexServiceControlFactory.getRemoteInstance();
        boolean returnvalue = false;
        HashMap map = mutex.getObjIDForUpdateList();
        for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
            String key = iter.next().toString();
            if(BillFID.equals(key.substring(0, 28))){
                returnvalue = true;
            }
        }
        return returnvalue;
    }

    /**
     * 表格获取选中行某列值  
     * 返回ArrayList集合
     * @param tblMain 列表对象
     * @param CellName 列名
     * @return ArrayList集合
     * 
     * 返回遍历
            try {
                ArrayList list = UtilClass.getTableCellsValue(kDTable1, "cell1");
                if(list.size()>0){
                    for (Iterator iter = list.iterator(); iter.hasNext();) { 
                        System.out.println((String)iter.next()); 
                    }
                }else{
                    UtilClass.alert("请选择要操作的记录");
                }
            } catch (Exception e1) {
            }
     */
    public static ArrayList getTableCellsValue(KDTable tblMain, String CellName){
        ICell cellstr;
        Object strObj = null;
        KDTSelectBlock block = null;
        ArrayList returnValue = new ArrayList();
        int size = tblMain.getSelectManager().size();
        for (int i = 0; i < size; i++) {
            block = tblMain.getSelectManager().get(i);
            for (int j = block.getTop(); j <= block.getBottom(); j++) {
                cellstr = tblMain.getRow(j).getCell(CellName);
                strObj =  cellstr.getValue();
                returnValue.add(strObj);
            }
        }
        return returnValue;
    }

    /**
     * 设置List默认查询条件
     * @param mainQuery List MainQuery
     * @param Filter 条件
     * 例：UtilClass.setListQueryFilter(mainQuery, "orderid.batchnum is not null");
     */
    public static void setListQueryFilter(EntityViewInfo mainQuery,String Filter){
        try {
            mainQuery.setFilter(Filter);
        } catch (ParserException e) {
            alert2("设置List默认查询条件出错！",e.getMessage());
        }
    }
    /**
     * 获取KDTable选择行的行号(选择行的第一行)
     * @param tblMain
     * @return 行号
     */
    public static int getRowNumFirst(KDTable tblMain){
        return tblMain.getSelectManager().get(0).getTop();
    }
    /**
     * 获取KDTable选择行的行号
     * @param tblMain
     * @return 行号
     */
    public static int[] getRowNum(KDTable tblMain){
        return KDTableUtil.getSelectedRows(tblMain);
    }
    /**
     * 导出KDTable表格数据到Excel文件
     * @param table KDTable
     * @param RowNums 行号集合 如果RowNums行数为0，导出全部数据
     * return 生成文件目录
     * 例：UtilClass.TableExpot(kDTable1, new int[0], null);
     */
    public static String TableExpot(KDTable table,int[] RowNums,String FileName){
        String returnvaleu = "";
        String Filepath = "";
        //打开目录选择器
        try {
            Filepath = UtilClass.OpenPathSelect();
            String File = "";
            if("".equals(Filepath)){
                return returnvaleu;
            }else{
                if(FileName==null||"".equals(FileName)){
                    FileName = "temp";
                }
                File = Filepath+"\\"+FileName+".xls";
            }
            File file = new File(File);
            //如果找到相同的文件，执行删除
            if(file.exists() && file.isFile()){
                file.delete();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(new File(File));
            //创建工作表
            wwb.createSheet("sheet1", 0);
            //获取工作表 
            WritableSheet ws = wwb.getSheet(0);

            //表头行样式
            WritableCellFormat TableHead = new WritableCellFormat();
            TableHead.setBorder(Border.ALL, BorderLineStyle.THIN);
            TableHead.setAlignment(Alignment.CENTRE);
            TableHead.setBackground(Colour.GRAY_25);

            //表体数据行样式
            WritableCellFormat TableRow = new WritableCellFormat();
            TableRow.setAlignment(Alignment.CENTRE);

            if(RowNums==null){
                //生成表头
                for(int i=0;i<table.getColumnCount();i++){
                    if(table.getHeadRow(0).getCell(i).getValue()!=null){
                        ws.addCell(new Label(i,0,table.getHeadRow(0).getCell(i).getValue().toString(),TableHead));
                    }
                }
                //生成表体数据
                for(int i=0;i<table.getRowCount();i++){
                    for(int j=0;j<table.getColumnCount();j++){
                        if(table.getRow(i).getCell(j).getValue()!=null){
                            ws.addCell(new Label(j,i+1,table.getRow(i).getCell(j).getValue().toString(),TableRow));
                        }
                    }
                }
            }else{
                //生成表头
                for(int i=0;i<table.getColumnCount();i++){
                    if(table.getHeadRow(0).getCell(i).getValue()!=null){
                        ws.addCell(new Label(i,0,table.getHeadRow(0).getCell(i).getValue().toString(),TableHead));
                    }
                }
                //生成表体数据
                for(int z=0;z<RowNums.length;z++){
                    int i = RowNums[z];
                    for(int j=0;j<table.getColumnCount();j++){
                        if(table.getRow(i).getCell(j).getValue()!=null){
                            ws.addCell(new Label(j,z+1,table.getRow(i).getCell(j).getValue().toString(),TableRow));
                        }
                    }
                }
            }
            wwb.write();    
            wwb.close();
            returnvaleu = File;
        } catch (Exception e) {
            alert2("生成Excel文件出错",Filepath);
        } 
        return returnvaleu;
    }
    /***
     * 设置表格列名
     * @param Table Table名称
     * @param Colunm 列名
     * @param name 值
     * 
     */
    public static void setTableColumnName(KDTable Table,String ColunmID,String ColunmName){
        KDTable kt = new KDTable();
        kt = Table;
        kt.getHeadRow(0).getCell(ColunmID).setValue(ColunmName);
        Table = kt;
    }
    /**
     * 设置表格融合方式
     * @param tblMain 表格
     * @param type 融合方式 0 ：行融合 1：列融合 2：自由融合
     */
    public static void setTableMergeMode(KDTable tblMain,int type){
        if(type==0){
            tblMain.getMergeManager().setMergeMode(KDTMergeManager.FREE_ROW_MERGE);
        }
        if(type==1){
            tblMain.getMergeManager().setMergeMode(KDTMergeManager.FREE_COLUMN_MERGE);
        }
        if(type==2){
            tblMain.getMergeManager().setMergeMode(KDTMergeManager.FREE_MERGE);
        }
    }
    /***
     * 表格行选择器，从第R1行选到R2行
     * @param tblMain 表格
     * @param R1 开始行号
     * @param R2 结束行号
     */
    public static void setTableSelectRows(KDTable tblMain,int R1,int R2){
        tblMain.getSelectManager().select(R1-1, 0, R2-1, 0);
    }


    /**
     * 设置按钮图片名称
     * @param btnName 按钮名称
     * @param imgName 图片名称
     * EAS图片名称保存位置：BOS环境下   工程\lib\client\eas\eas_resource_common_ico.jar
     */
    public static void setButtonImg(com.kingdee.bos.ctrl.swing.KDWorkButton ButtonName,String imgName){
        ButtonName.setIcon(EASResource.getIcon(imgName));
        ButtonName.setEnabled(true);
    }
    /**
     * 设置F7
     * @param F7Name F7名称
     * @param ConditionSQL 过滤条件SQL(如果输入值不等于""，则添加条件)
     *        例   " fbilltypestatr = '1' and (entrys.id is null or entrys.seq = 1)"
     * @param Query 属性 例："com.kingdee.eas.cmt.basedata.app.OperatorOrgQuery"
     * @param EditFrmat 属性 例："$number$"
     * @param DisplayFormat 属性 例："$name$"
     * @param CommitFormat 属性 例："$number$"
     * @throws BOSException 
     */
    public static void setF7(KDBizPromptBox F7Name,String ConditionSQL ,String Query,String EditFrmat,String DisplayFormat,String CommitFormat){
        //添加分录过滤条件
        try {
            EntityViewInfo view = new EntityViewInfo();
            if(ConditionSQL != ""){
                view.setFilter(ConditionSQL);
            }
            //设置F7属性
            F7Name.setQueryInfo(Query);//关联Query
            F7Name.setEditFormat(EditFrmat);//编辑样式
            F7Name.setDisplayFormat(DisplayFormat);//展现样式
            F7Name.setCommitFormat(CommitFormat);//提交样式
            F7Name.setEntityViewInfo(view);
            F7Name.setEnabledMultiSelection(false);
        } catch (Exception e) {
            alert2("F7["+F7Name+"]初始化出错，请联系管理员！",e.getMessage());
        }
    }
    /**
     * 设置分录F7
     * @param col 列名 kdtEntrys.getColumn("boxType")
     * @param ConditionSQL 过滤条件SQL(如果输入值不等于""，则添加条件)
     *        例   " fbilltypestatr = '1' and (entrys.id is null or entrys.seq = 1)"
     * @param Query 属性 例："com.kingdee.eas.cmt.basedata.app.OperatorOrgQuery"
     * @param EditFrmat 属性 例："$number$"
     * @param DisplayFormat 属性 例："$name$"
     * @param CommitFormat 属性 例："$number$"
     */
    public static void setEntryF7(IColumn col,String ConditionSQL ,String Query,String EditFrmat,String DisplayFormat,String CommitFormat){
        try {
            KDBizPromptBox prmt = new KDBizPromptBox();
            EntityViewInfo view = new EntityViewInfo();
            if(ConditionSQL != ""){
                view.setFilter(ConditionSQL);
            }
            prmt.setQueryInfo(Query);
            prmt.setEditFormat(EditFrmat);
            prmt.setCommitFormat(CommitFormat);
            prmt.setDisplayFormat(DisplayFormat);
            prmt.setEntityViewInfo(view);
            prmt.setEnabledMultiSelection(false);
            KDTDefaultCellEditor editor = new KDTDefaultCellEditor(prmt);
            col.setEditor(editor);
        } catch (ParserException e) {
            alert2("分录F7初始化出错，请联系管理员！",e.getMessage());
        }

    }
    /**
     * 日期转换字符串
     * @param date 日期
     * @param type 显示格式 yyyy-MM-dd yyyy-MM-dd HH:mm:ss
     * String returnvalue = UtilClass.DateToString(this.Startdatetest.getValue(), "yyyy-MM-dd");
     */
    public static String DateToString (Object date,String type){
        String returnvalue = "";
        if(date != null){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat(type);
                returnvalue = sdf.format(date);
            }
            catch(Exception ex){
                alert("日期格式转换出错");
            }
        }
        return returnvalue;
    }
    /**
     * 字符串转为日期
     * @param DateStr 字符串
     * @param type 类型 "yyyy-MM-dd HH:mm:ss"
     * @return Date  java.util.Date
     */
    public static Date StringToDate(String DateStr,String type){
        Date returnvalue = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            returnvalue = sdf.parse(DateStr);
        } catch (ParseException e) {
            alert2("日期转换出错",DateStr+"-->"+type);
        }
        return returnvalue;
    }
    /**
     * 设置DKDatePicker控件显示格式
     * @param date 日期控制 
     * @param dateType 格式样式 例："yyyy-MM-dd HH:mm:ss"      "yyyy-MM-dd"
     */
    public static void setKDDatePicker(KDDatePicker date,String dateType){
        date.setDatePattern(dateType);
    }
    /**
     * 获取当前时间(KDDatePicker控件)默认值 例如：创建时间 修改时间 审核时间
     * @return java.sql.Timestamp 当前时间
     */
    public static java.sql.Timestamp getTime(){
        java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
        return time;
    }

    /**
     * 打开文件
     * @param FilePath 文件路径
     */
    public static void OpenFile(String FilePath){
        try {
            Runtime.getRuntime().exec("cmd /c start \"\" \""+FilePath.replaceAll("\\\\", "\\\\\\\\")+"\"");
        } catch (IOException e) {
            alert2("打开文件出错",FilePath);
        }
    }
    /**
     * 打开文件选择器
     * @return 文件路径
     */
    public static String OpenFilesSelect(){
        String returnvalue = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("请选择文件");
        chooser.showDialog(null, "确定");
        if(chooser.getSelectedFile()!=null){
            File file = chooser.getSelectedFile();
            returnvalue = file.getPath();
        }
        return returnvalue;
    }

    /**
     * 打开目录选择器
     * @return
     */
    public static String OpenPathSelect(){
        String returnvalue = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("请选择目录");
        chooser.showDialog(null,"保存");
        if(chooser.getSelectedFile()!=null){
            File file = chooser.getSelectedFile();
            returnvalue = file.getPath();
        }
        return returnvalue;
    }
    /**
     * 向Excel文件插入数据
     * @param File 
     * @param sheetNum 工作表序号
     * @param y 行
     * @param x 列
     * @param value 内容
     */
    public static void setExcelValue(String File,int sheetNum,int x,int y,String value){
        try {
            File file = new File(File);
            //如果找到相同的文件，执行删除
            if(!file.exists() && !file.isFile()){
                return;
            }
            //Excel获得文件 
            Workbook wb = Workbook.getWorkbook(new File(File)); 
            //打开一个文件的副本，并且指定数据写回到原文件 
            WritableWorkbook book = Workbook.createWorkbook(new File(File),wb); 
            //获取工作表 
            WritableSheet sheet=book.getSheet(sheetNum);
            sheet.addCell(new Label(y,x,value)); 
            book.write(); 
            book.close(); 
        } catch (Exception e) {
        }  
    }
    /**
     * 读取Excel文件
     * @param File 文件名
     * @param sheetNum 工作表序号
     * @param y 行
     * @param x 列
     */
    public static String getExcelValue(String File,int sheetNum,int y,int x){
        String result = "";
        try {
            File file = new File(File);
            //如果找到相同的文件，执行删除
            if(!file.exists() && !file.isFile()){
                alert(File+"文件没找到！");
            }
            Workbook book= Workbook.getWorkbook(new File(File)); 
            //获得第一个工作表对象 
            Sheet sheet=book.getSheet(sheetNum); 
            //得到第一列第一行的单元格 
            Cell cell1=sheet.getCell(x,y); 
            result=cell1.getContents().toString(); 
            book.close();
        } catch (FileNotFoundException e) {
            alert2("读取Excel文件出错","请关闭当前打开的Excel文件");
        } catch (BiffException e) {
            alert2("读取Excel文件出错",e.toString());
        } catch (IOException e) {
            alert2("读取Excel文件出错",e.toString());
        }
        return result;
    }

    /**
     * 读取Excel文件(第一sheet页中的内容)
     * @param File 文件路径
     * @param sheetNum sheet页号
     * @return 二维数组
     * 
     */
    public static Object[][] getExcelValue(String File,int sheetNum){
        Object [][] returnvalue = null;
        try {
            Workbook book= Workbook.getWorkbook(new File(File)); 
            Sheet sheet=book.getSheet(sheetNum);
            returnvalue = new Object[sheet.getRows()][sheet.getColumns()];
            for(int i=1;i<sheet.getRows();i++){
                for(int j=0;j<sheet.getColumns();j++){
                    returnvalue[i][j]=sheet.getCell(j,i).getContents();
                }
            }
        } catch (FileNotFoundException e) {
            alert2("读取Excel文件出错","请关闭当前打开的Excel文件");
        } catch (BiffException e) {
            alert2("读取Excel文件出错",e.toString());
        } catch (IOException e) {
            alert2("读取Excel文件出错",e.toString());
        }
        return returnvalue;
    }

    /***
     * 发送即时消息
     * @param FSuser 发送人ID
     * @param JSuser 接收人ID
     * @param MessageTitle 标题
     * @param Messages 内容
     */
    public static void addMessage(String FSuser,String JSuser,String MessageTitle,String Messages){
        try {
            IBMCMessage i = BMCMessageFactory.getRemoteInstance();
            BMCMessageInfo info = new BMCMessageInfo(); 
            info.setType(MsgType.ONLINE);// 消息类型，例如通知消息，任务消息，状态更新消息
            info.setBizType(MsgBizType.ONLINE);// 业务类型，例如工作流，预警平台
            info.setPriority(MsgPriority.HIGH); // 优先级
            info.setStatus(MsgStatus.UNREADED); // 消息状态
            info.setReceiver(JSuser); // 接收人ID （User的ID，不是Person的ID）
            info.setSender(FSuser);// 消息发送人
            info.setTitle(MessageTitle); // 消息标题
            info.setBody(Messages);// 消息内容
            i.submit(info);
        } catch (Exception e) {
            alert2("发送即时消息出错","标题："+MessageTitle+"  内容:"+Messages);
        }
    }



    /**
     * 列转行
     * @param list 数据集合
     * @param delimiter 分隔符 例：","
     * @param bracketsLeft 左括号符号
     * @param bracketsRight 右括号符号
     * @return String
     */
    public static String CellToRow(ArrayList list,String delimiter,String bracketsLeft,String bracketsRight){
        String returnvalue = "";
        for (Iterator iter = list.iterator(); iter.hasNext();){
            if(!"".equals(bracketsLeft) && bracketsLeft != null && !"".equals(bracketsRight) && bracketsRight != null){
                returnvalue += bracketsLeft + (String)iter.next() + bracketsRight;
            }
            returnvalue+=delimiter;
        }
        return returnvalue.substring(0, returnvalue.length()-1);
    }

    /**
     * 打开窗口
     * @param URL UI地址
     * @param ctx 参数集合
     * @param openType 打开窗口类型 例:UIFactoryName.MODEL
     * @param billStatus 单据状态  例：OprtState.ADDNEW
     * 
     *  
        打开普通UI页面
        HashMap cix = new HashMap();
        String orderid = "asiofjlqkjwfklaslkasdf="
        cix.put("orderid", orderid);
        UtilClass.openUI("com.kingdee.eas.cmt.commission.client.GoodsUI", cix, UIFactoryName.MODEL, OprtState.ADDNEW);

        打开单据EditUI页面
         HashMap cix = new HashMap();
        cix.put("ID", fid);
        UtilClass.openUI("com.kingdee.eas.cmt.commission.client.CmtTranConsignEditUI", cix, UIFactoryName.NEWWIN, OprtState.VIEW);


        在打开的单据获取前面页面传来的参数
        this.getUIContext().get("orderid").toString()
     *
     *
     */
    public static void openUI(String URL,HashMap ctx,String openType,String billStatus){
        try {
            IUIWindow ui = UIFactory.createUIFactory(openType).create(URL, ctx, null, billStatus);
            ui.show();
        } catch (UIException e) {
            alert2("弹出UI程序出错:",URL);
        }
    }

    /**
     * 发送参数
     * @param ContextID 参数编号
     * @param values 参数值
     */
    public static void setSysContext(String ContextID,Object values){
        SysContext.getSysContext().setProperty(ContextID, values);
    }

    /**
     * 获取参数
     * @param ContextID 参数编号
     * @return 参数值(Object)
     */
    public static Object getSysContext(String ContextID){
        return SysContext.getSysContext().getProperty(ContextID);
    }

    /**
     * 获取UI参数
     * @param UI
     * @return
     */
    public static String getUIContext(CoreUIObject UI){
        String returnvalue = "";
        if(UI.getUIContext().get("UIClassParam")!=null){
            returnvalue = UI.getUIContext().get("UIClassParam").toString();
        }
        return returnvalue;
    }

    /**
     * 创建单据编号
     * @param Parameter 规则参数
     *        参数说明:
     *               logo          编号头标示
     *               date          日期时间
     *               Delimiter  分隔符
     *               digit         序号位数
     *               isTissueIsolation   是否组织隔离(0:不隔离1:隔离)
     *               table      表名
     * @return String 单据编号
     * 
     *  HashMap Parameter = new HashMap();
        //编号头标示符号
        Parameter.put("logo", "CMT");
        //日期
        Parameter.put("date", UtilClass.DateToString(UtilClass.getTime(), "yyyyMMdd"));
        //分隔符
        Parameter.put("Delimiter", "-");
        //序号位数
        Parameter.put("digit", "5");
        //是否隔离组织0为不隔离1为隔离
        Parameter.put("isTissueIsolation", "0");
        //单据表名
        Parameter.put("table", "T_BAS_VehicleType");
        String billNum = UtilClass.createrBillNumber(Parameter);
     * 
     */
    public static String createrBillNumber(HashMap Parameter){
        StringBuffer returnvalue = new StringBuffer();
        //编号头Logo
        if(Parameter.get("logo")!=null){
            returnvalue.append(Parameter.get("logo"));
        }
        //添加时间
        if(Parameter.get("date")!=null){
            if(Parameter.get("Delimiter")!=null){
                returnvalue.append(Parameter.get("Delimiter"));
            }
            returnvalue.append(Parameter.get("date"));
        }
        //创建序号位(digit:序号位数)
        if(Parameter.get("digit")!=null){
            StringBuffer getDigitSQL = new StringBuffer();
            Integer digit = new Integer(Parameter.get("digit").toString());
            StringBuffer digitValue = new StringBuffer();
            for(int i=0;i<digit.intValue();i++){
                digitValue.append("0");
            }
            getDigitSQL.append("select trim(to_char(count(*)+1,'"+digitValue+"')) from "+Parameter.get("table")+"  ");
            //是否组织隔离
            if("1".equals(Parameter.get("isTissueIsolation"))){
                getDigitSQL.append(" where FControlUnitID = '"+getCU().getId()+"'");
            }
            if(Parameter.get("Delimiter")!=null){
                returnvalue.append(Parameter.get("Delimiter"));
            }
            //获取数据库记录数
            returnvalue.append(executeQueryString(getDigitSQL.toString()));
        }
        return returnvalue.toString();
    }

    /**
     *  当前登录组织
     * @return
     */
    public static CtrlUnitInfo getCU(){
        return SysContext.getSysContext().getCurrentCtrlUnit();
    }

    /**
     * 判断当前组织是否为集团要目录
     * @return boolean
     */
    public static boolean isRootCU(){
        if("1".equals(executeQueryString("select tc.flevel from t_org_baseunit tc where tc.fid = '"+getCU().getId()+"'"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取集团组织ID
     * @return
     */
    public static CtrlUnitInfo getRootCU(){
        CtrlUnitInfo cinfo = new CtrlUnitInfo();
        try {
            CtrlUnitCollection cinfos = CtrlUnitFactory.getRemoteInstance().getCtrlUnitCollection("select * where level = 1 ");
            cinfo = cinfos.get(0);
        } catch (BOSException e) {
            alert2("获取集团组织ID出错",e.getMessage());
        }
        return cinfo;
    }
    /**
     * 当前登录用户
     * @return
     */
    public static UserInfo getUser(){
        return SysContext.getSysContext().getCurrentUserInfo();
    }
    /**
     * 当前登陆人员
     * @return
     */
    public static PersonInfo getPerson(){
        PersonInfo personinfo = null;
        try {
            PersonCollection Personcollection = PersonFactory.getRemoteInstance().getPersonCollection(" select * where name = '" + SysContext.getSysContext().getCurrentUserInfo().getName() +"'");
            personinfo=Personcollection.get(0);
        } catch (Exception e1) {
        }
        return personinfo;
    }
    /**
     * 当前登录人员部门
     * @return
     */
    public static AdminOrgUnitInfo getDepartment(){
        AdminOrgUnitInfo returnvalue = null;
        try {
            PersonPositionInfo PersonPosition = PersonPositionFactory.getRemoteInstance().getPersonPositionInfo("select primaryPosition.* where person = '" + getPerson().getId() + "'");
            PositionInfo Position = PersonPosition.getPrimaryPosition();
            AdminOrgUnitCollection collection = AdminOrgUnitFactory.getRemoteInstance().getAdminOrgUnitCollection(" select * where id= '" + Position.getAdminOrgUnit().getId() +"'");
            returnvalue = collection.get(0);
        } catch (Exception e2) {
        }
        return returnvalue;
    }

    /**
     * 通过fid获取表名
     * @param fid
     * @return 表名
     */
    public static String getDBTableName(String fid){
        String Tablename = "";
        com.kingdee.bos.util.BOSObjectType bosType = BOSUuid.read(fid).getType();
        try {
            Tablename = FMIsqlFacadeFactory.getRemoteInstance().getTableNameByBosType(bosType);
        } catch (BOSException e) {
            alert2("获取表名出错",fid);
        }
        return Tablename;
    }

    /**
     * 执行SQL（查询，返回集合）
     * @param sql
     * @return 
      IRowSet rs = UtilClass.executeQuery(sql.toString());
      while(rs.next()){
         rs.getObject(1).toString();
      }
     */
    public static IRowSet executeQuery(String sql){
        IRowSet returnvalue = null;
        try {
            IFMIsqlFacade db = com.kingdee.eas.fm.common.FMIsqlFacadeFactory.getRemoteInstance();
            returnvalue = db.executeQuery(" /*dialect*/ "+sql, null);
        } catch (Exception e) {
            alert2("执行SQL出错",sql);
        }
        return returnvalue;
    }
    /**
     * 执行SQL(查询，返回单看值)
     * @param sql
     * @return String
     */
    public static String executeQueryString(String sql){
        String returnvalue = null;
        try {
            IFMIsqlFacade db = com.kingdee.eas.fm.common.FMIsqlFacadeFactory.getRemoteInstance();
            IRowSet rs = db.executeQuery(" /*dialect*/ "+sql, null);
            while(rs.next()){
                if(rs.getObject(1)!=null){
                    returnvalue = rs.getObject(1).toString();
                }
            }
        } catch (Exception e) {
            alert2("执行SQL出错",sql);
        }
        return returnvalue;
    }
    /**
     * 执行SQL(新增，修改)
     * @param sql
     */
    public static void executeSql(String sql){
        try {
            IFMIsqlFacade db = com.kingdee.eas.fm.common.FMIsqlFacadeFactory.getRemoteInstance();
            db.executeSql(" /*dialect*/ "+sql);
        } catch (Exception e) {
            alert2("执行SQL出错",sql);
        }
    }

    /**
     * SQL数据导出到Excel文件
     * @param sql
     * @param FileName 文件名
     */
    public static String SQLExpot(String sql,String FileName){
        String returnvalue ="";
        try {
            if("".equals(sql)||sql==null){
                return  returnvalue;
            }
            if(FileName==null||"".equals(FileName)){
                FileName = "temp";
            }
            String Filepath = UtilClass.OpenPathSelect();
            String File = "";
            if("".equals(Filepath)){
                return returnvalue;
            }else{
                File = Filepath+"\\"+FileName+".xls";
            }
            File file = new File(File);
            //如果找到相同的文件，执行删除
            if(file.exists() && file.isFile()){
                file.delete();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(new File(File));
            //创建工作表
            wwb.createSheet("sheet1", 0);
            //获取工作表 
            WritableSheet ws = wwb.getSheet(0);

            //表头行样式
            WritableCellFormat TableHead = new WritableCellFormat();
            TableHead.setBorder(Border.ALL, BorderLineStyle.THIN);
            TableHead.setAlignment(Alignment.CENTRE);
            TableHead.setBackground(Colour.GRAY_25);

            //表体数据行样式
            WritableCellFormat TableRow = new WritableCellFormat();
            TableRow.setAlignment(Alignment.CENTRE);

            IRowSet rs = UtilClass.executeQuery(sql);
            //生成列名
            for(int i=0;i<rs.getRowSetMetaData().getColumnCount();i++){
                String columnName = rs.getRowSetMetaData().getColumnName(i+1);
                ws.addCell(new Label(i,0,columnName,TableHead));
            }
            int z=1;
            while(rs.next()){
                for(int j=1;j<=rs.getRowSetMetaData().getColumnCount();j++){
                    if(rs.getObject(j)!=null){
                        ws.addCell(new Label(j-1,z,rs.getObject(j).toString(),TableRow));
                    }
                }
                z++;
            }
            wwb.write();    
            wwb.close();
        } catch (Exception e) {
            alert2("导出数据生成Excel文件出错",sql);
        }
        return returnvalue;
    }


    /**
     * 集装箱箱号正确性验证
     * @param BoxNum 箱号
     * @return Boolean
       if(!UtilClass.BoxVerification(boxnum)){
            if(!UtilClass.alertReturn("箱号："+boxnum+" 不是国际标准箱号，是否保存？")){
                UtilClass.Stop();
            }
        }
     */
    public static boolean BoxVerification(String BoxNum){
        HashMap bj = new HashMap();
        bj.put("0", 0);
        bj.put("1", 1);
        bj.put("2", 2);
        bj.put("3", 3);
        bj.put("4", 4);
        bj.put("5", 5);
        bj.put("6", 6);
        bj.put("7", 7);
        bj.put("8", 8);
        bj.put("9", 9);
        bj.put("A", 10);
        bj.put("B", 12);
        bj.put("C", 13);
        bj.put("D", 14);
        bj.put("E", 15);
        bj.put("F", 16);
        bj.put("G", 17);
        bj.put("H", 18);
        bj.put("I", 19);
        bj.put("J", 20);
        bj.put("K", 21);
        bj.put("L", 23);
        bj.put("M", 24);
        bj.put("N", 25);
        bj.put("O", 26);
        bj.put("P", 27);
        bj.put("Q", 28);
        bj.put("R", 29);
        bj.put("S", 30);
        bj.put("T", 31);
        bj.put("U", 32);
        bj.put("V", 34);
        bj.put("W", 35);
        bj.put("X", 36);
        bj.put("Y", 37);
        bj.put("Z", 38);

        //去掉箱号两边空格
        String newBoxNum = BoxNum.trim();

        //判断箱号是否为国际标准11位
        if(newBoxNum.length() != 11){
            return false;
        }

        //判断前四位为字母  區別小寫
        for(int i=0;i<4;i++){
            String Nums = newBoxNum.substring(i, i+1);
            char chs[] = Nums.toCharArray();
            if((int)chs[0]<65 || (int)chs[0]>90){
                return false;
            }
        }

        //判断后7位为数字
        for(int i=4;i<11;i++){
            String Nums = newBoxNum.substring(i, i+1);
            char chs[] = Nums.toCharArray();
            if((int)chs[0]<48 || (int)chs[0]>57){
                return false;
            }
        }

        //判断第11数验证码是否正确
        double VerificationNumSum = 0;
        for(int i=0;i<10;i++){
            //获取当前位字母或数字对应的数值
            double bjdata = ((Integer)bj.get(newBoxNum.substring(i, i+1))).doubleValue();
            //获取当前位对应的2的i次方
            double jfdata = Math.pow(2,i);
            VerificationNumSum+=bjdata*jfdata;
        }
        int VerificationNum =(int)VerificationNumSum%11;
        int sub11 = new Integer(newBoxNum.substring(10)).intValue();
        //如果计算结果与第11位数是否相等
        if(VerificationNum!=sub11){
            return false;
        }
        return true;
    }

    /**
     * 从oracle数据读取图片字节码生成图片文件
     * @param img_num 图片编号(参数表编号)
     * @param file 文件地址 "c://bmw.png"

            系统参数表
            -- 创建表
            create table T_SYS_FUTVAN
            (
              FNUMBER             NVARCHAR2(55),
              FNAME               NVARCHAR2(256),
              PARAMETER_STRING    NVARCHAR2(256),
              PARAMETER_NUMBER    NUMBER,
              PARAMETER_DATE      DATE,
              PARAMETER_TIMESTAMP TIMESTAMP(6),
              PARAMETER_BLOB      BLOB
            )
            -- 创建字段说明 
            comment on column T_SYS_FUTVAN.FNUMBER
              is '参数编号';
            comment on column T_SYS_FUTVAN.FNAME
              is '参数名称';
            comment on column T_SYS_FUTVAN.PARAMETER_STRING
              is '文本型参数';
            comment on column T_SYS_FUTVAN.PARAMETER_NUMBER
              is '数据型参数';
            comment on column T_SYS_FUTVAN.PARAMETER_DATE
              is '日期型参数';
            comment on column T_SYS_FUTVAN.PARAMETER_TIMESTAMP
              is '时间型参数';
            comment on column T_SYS_FUTVAN.PARAMETER_BLOB
              is '字节型参数';
     * 
     */
    public static void getImgFromOracle(String img_num,String file){
        try {
            IRowSet rs = executeQuery("select parameter_blob from t_sys_futvan where fnumber = '"+img_num+"'"); 
            while(rs.next()){
                java.sql.Blob blob = rs.getBlob(1);
                InputStream ins = blob.getBinaryStream();
                File f = new File(file);
                FileOutputStream fout = new FileOutputStream(f);
                byte[]b = new byte[10244];
                int len = 0;
                while((len = ins.read(b)) != -1){
                    fout.write(b,0,len);
                }
                fout.close();
                ins.close();
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    /**
     * BOTP单据转换
     * @param botpNum 转换规则编号
     * @param BillInfo 原单据
     */
    public static void BOTP(String botpNum,CoreBillBaseInfo BillInfo){
        String error = "";
        try {
            // 取得BOPT的映射
            BOTMappingCollection botmapping = BOTMappingFactory.getRemoteInstance().getBOTMappingCollection("select * where    name = '"+botpNum+"'  ");
            BOTMappingInfo btpMappingInfo = null;
            if (botmapping !=null && botmapping.size() == 1) {
                btpMappingInfo = botmapping.get(0);
            } else {
                if(botmapping==null || botmapping.size()<1){
                    error = "未找到转换规则  规则编号:"+botpNum;
                }
                if(botmapping.size()>1){
                    error = "找到多条转换规则，请删除重复规则。   规则编号:"+botpNum;
                }
                throw new Exception();
            }
            //执行单据转换
            BTPTransformResult transformResult = BTPManagerFactory.getRemoteInstance().transform(BillInfo, btpMappingInfo);

            //取得目标单据列表
            IObjectCollection toBillList = transformResult.getBills();
            
            //保存目标单据
            for (int i = 0; i < toBillList.size(); i++) {   
                CoreBillBaseInfo destBillInfo = (CoreBillBaseInfo) toBillList.getObject(i);
                BTPManagerFactory.getRemoteInstance().saveRelations(destBillInfo, transformResult.getBOTRelationCollection());
            }
            
        } catch (Exception e) {
            if("".equals(error) || error == null){
                alert2("单据转换出错",e.getMessage());
            }else{
                alert2("单据转换出错",error);
            }
        }
    }
    
    /**
     * 分摊功能
     * @param Total 总数
     * @param Allocations 分摊明细
     * @param newScale 小数位数
     * @return 分摊分集合
        String [][]Allocations = new String[3][2];
        Allocations[0][0] = "001";
        Allocations[0][1] = "3";
        Allocations[1][0] = "002";
        Allocations[1][1] = "3";
        Allocations[2][0] = "003";
        Allocations[2][1] = "3";
        String [][] rv = UtilClass.mathAllocation(new BigDecimal("100"), Allocations,2);
     */
    public static String[][] mathAllocation(BigDecimal Total,String[][] Allocations,int newScale){
        String[][] returnvalue = new String[Allocations.length][2];
        BigDecimal sum = new BigDecimal("0");
        BigDecimal Allocationsum = new BigDecimal("0");
        try {
            //获取明细总数
            for(int i=0;i<Allocations.length;i++){
                if(Allocations[i][1]!=null&&!"".equals(Allocations[i][1])){
                    sum = sum.add(new BigDecimal(Allocations[i][1]));
                }
            }
            
            //按比例分摊
            for(int i=0;i<Allocations.length;i++){
                if(Allocations[i][1]!=null&&!"".equals(Allocations[i][1])){
                    BigDecimal thisValue = new BigDecimal(Allocations[i][1]);
                    BigDecimal AllocationValue = thisValue.divide(sum,16, BigDecimal.ROUND_HALF_UP).multiply(Total).setScale(newScale, BigDecimal.ROUND_HALF_UP);
                    returnvalue[i][0] = Allocations[i][0];
                    returnvalue[i][1] = AllocationValue.toString();
                }else{
                    returnvalue[i][0] = Allocations[i][0];
                    returnvalue[i][1] = "0";
                }
            }
            
            //判断分摊后每条记录数据是否与总数相同
            for(int i=0;i<returnvalue.length;i++){
                if(returnvalue[i][1]!=null&&!"".equals(returnvalue[i][1])){
                    Allocationsum = Allocationsum.add(new BigDecimal(returnvalue[i][1]));
                }
            }
            
            //如果分摊后结果与分摊前总数不一等。在分摊记录最后一条。加上。分摊后总数与分摊总数的差值
            if(Allocationsum.compareTo(Total)!=0){
                BigDecimal xz = new BigDecimal(returnvalue[returnvalue.length-1][1]).subtract(Allocationsum.subtract(Total));
                returnvalue[returnvalue.length-1][1] = xz.toString();
            }
        } catch (Exception e) {
            StringBuffer er = new StringBuffer();
            for(int i=0;i<Allocations.length;i++){
                er.append("| "+Allocations[i][0]+"   "+Allocations[i][1]+" |");
            }
            alert2("分摊出错","分摊总数:"+Total.toString()+"  明细:"+er.toString());
        }
        return returnvalue;
    }
    
}