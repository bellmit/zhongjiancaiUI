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
 * EAS����������
 * @author ���
 *
 */
public class UtilClass {
    /***
     * ��Ϣ��ʾ��
     * @param value ��ʾ����
     */
    public static void alert(String value){
        MsgBox.showInfo(value);
        SysUtil.abort();
    }

    /***
     * ��Ϣ��ʾ��(����ϸ��Ϣ)
     * @param value ��ʾ����
     */
    public static void alert2(String Title,String info){
        MsgBox.showDetailAndOK(null, Title, info, 0);
        SysUtil.abort();
    }

    /***
     * ��Ϣ��ʾ�򣨴���ʾ��
     * @return value ��ʾ����
     */
    public static boolean alertReturn(String value){
        return MsgBox.isYes(MsgBox.showConfirm2(value));
    }

    /***
     * ����ֹͣ����
     */
    public static void Stop(){
        SysUtil.abort();
    }

    /**
     * �������ݼ���
     * @param billId ����ID
     */
    public static void addBillDataLock(String BillFID){
        IMutexServiceControl mutex = MutexServiceControlFactory.getRemoteInstance();
        mutex.requestBizObjIDForUpdate(BillFID);
    }

    /**
     * �������ݽ���
     * @param billId ����ID
     */
    public static void removeBillDataLock(String BillFID){
        IMutexServiceControl mutex = MutexServiceControlFactory.getRemoteInstance();
        mutex.releaseObjIDForUpdate(BillFID);
    }

    /**
     * ����ID��ȡ�����Ƿ����
     * @param id ���ݱ��
     * @return true �Ѽ��� or false δ����
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
     * ����ȡѡ����ĳ��ֵ  
     * ����ArrayList����
     * @param tblMain �б����
     * @param CellName ����
     * @return ArrayList����
     * 
     * ���ر���
            try {
                ArrayList list = UtilClass.getTableCellsValue(kDTable1, "cell1");
                if(list.size()>0){
                    for (Iterator iter = list.iterator(); iter.hasNext();) { 
                        System.out.println((String)iter.next()); 
                    }
                }else{
                    UtilClass.alert("��ѡ��Ҫ�����ļ�¼");
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
     * ����ListĬ�ϲ�ѯ����
     * @param mainQuery List MainQuery
     * @param Filter ����
     * ����UtilClass.setListQueryFilter(mainQuery, "orderid.batchnum is not null");
     */
    public static void setListQueryFilter(EntityViewInfo mainQuery,String Filter){
        try {
            mainQuery.setFilter(Filter);
        } catch (ParserException e) {
            alert2("����ListĬ�ϲ�ѯ��������",e.getMessage());
        }
    }
    /**
     * ��ȡKDTableѡ���е��к�(ѡ���еĵ�һ��)
     * @param tblMain
     * @return �к�
     */
    public static int getRowNumFirst(KDTable tblMain){
        return tblMain.getSelectManager().get(0).getTop();
    }
    /**
     * ��ȡKDTableѡ���е��к�
     * @param tblMain
     * @return �к�
     */
    public static int[] getRowNum(KDTable tblMain){
        return KDTableUtil.getSelectedRows(tblMain);
    }
    /**
     * ����KDTable������ݵ�Excel�ļ�
     * @param table KDTable
     * @param RowNums �кż��� ���RowNums����Ϊ0������ȫ������
     * return �����ļ�Ŀ¼
     * ����UtilClass.TableExpot(kDTable1, new int[0], null);
     */
    public static String TableExpot(KDTable table,int[] RowNums,String FileName){
        String returnvaleu = "";
        String Filepath = "";
        //��Ŀ¼ѡ����
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
            //����ҵ���ͬ���ļ���ִ��ɾ��
            if(file.exists() && file.isFile()){
                file.delete();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(new File(File));
            //����������
            wwb.createSheet("sheet1", 0);
            //��ȡ������ 
            WritableSheet ws = wwb.getSheet(0);

            //��ͷ����ʽ
            WritableCellFormat TableHead = new WritableCellFormat();
            TableHead.setBorder(Border.ALL, BorderLineStyle.THIN);
            TableHead.setAlignment(Alignment.CENTRE);
            TableHead.setBackground(Colour.GRAY_25);

            //������������ʽ
            WritableCellFormat TableRow = new WritableCellFormat();
            TableRow.setAlignment(Alignment.CENTRE);

            if(RowNums==null){
                //���ɱ�ͷ
                for(int i=0;i<table.getColumnCount();i++){
                    if(table.getHeadRow(0).getCell(i).getValue()!=null){
                        ws.addCell(new Label(i,0,table.getHeadRow(0).getCell(i).getValue().toString(),TableHead));
                    }
                }
                //���ɱ�������
                for(int i=0;i<table.getRowCount();i++){
                    for(int j=0;j<table.getColumnCount();j++){
                        if(table.getRow(i).getCell(j).getValue()!=null){
                            ws.addCell(new Label(j,i+1,table.getRow(i).getCell(j).getValue().toString(),TableRow));
                        }
                    }
                }
            }else{
                //���ɱ�ͷ
                for(int i=0;i<table.getColumnCount();i++){
                    if(table.getHeadRow(0).getCell(i).getValue()!=null){
                        ws.addCell(new Label(i,0,table.getHeadRow(0).getCell(i).getValue().toString(),TableHead));
                    }
                }
                //���ɱ�������
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
            alert2("����Excel�ļ�����",Filepath);
        } 
        return returnvaleu;
    }
    /***
     * ���ñ������
     * @param Table Table����
     * @param Colunm ����
     * @param name ֵ
     * 
     */
    public static void setTableColumnName(KDTable Table,String ColunmID,String ColunmName){
        KDTable kt = new KDTable();
        kt = Table;
        kt.getHeadRow(0).getCell(ColunmID).setValue(ColunmName);
        Table = kt;
    }
    /**
     * ���ñ���ںϷ�ʽ
     * @param tblMain ���
     * @param type �ںϷ�ʽ 0 �����ں� 1�����ں� 2�������ں�
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
     * �����ѡ�������ӵ�R1��ѡ��R2��
     * @param tblMain ���
     * @param R1 ��ʼ�к�
     * @param R2 �����к�
     */
    public static void setTableSelectRows(KDTable tblMain,int R1,int R2){
        tblMain.getSelectManager().select(R1-1, 0, R2-1, 0);
    }


    /**
     * ���ð�ťͼƬ����
     * @param btnName ��ť����
     * @param imgName ͼƬ����
     * EASͼƬ���Ʊ���λ�ã�BOS������   ����\lib\client\eas\eas_resource_common_ico.jar
     */
    public static void setButtonImg(com.kingdee.bos.ctrl.swing.KDWorkButton ButtonName,String imgName){
        ButtonName.setIcon(EASResource.getIcon(imgName));
        ButtonName.setEnabled(true);
    }
    /**
     * ����F7
     * @param F7Name F7����
     * @param ConditionSQL ��������SQL(�������ֵ������""�����������)
     *        ��   " fbilltypestatr = '1' and (entrys.id is null or entrys.seq = 1)"
     * @param Query ���� ����"com.kingdee.eas.cmt.basedata.app.OperatorOrgQuery"
     * @param EditFrmat ���� ����"$number$"
     * @param DisplayFormat ���� ����"$name$"
     * @param CommitFormat ���� ����"$number$"
     * @throws BOSException 
     */
    public static void setF7(KDBizPromptBox F7Name,String ConditionSQL ,String Query,String EditFrmat,String DisplayFormat,String CommitFormat){
        //��ӷ�¼��������
        try {
            EntityViewInfo view = new EntityViewInfo();
            if(ConditionSQL != ""){
                view.setFilter(ConditionSQL);
            }
            //����F7����
            F7Name.setQueryInfo(Query);//����Query
            F7Name.setEditFormat(EditFrmat);//�༭��ʽ
            F7Name.setDisplayFormat(DisplayFormat);//չ����ʽ
            F7Name.setCommitFormat(CommitFormat);//�ύ��ʽ
            F7Name.setEntityViewInfo(view);
            F7Name.setEnabledMultiSelection(false);
        } catch (Exception e) {
            alert2("F7["+F7Name+"]��ʼ����������ϵ����Ա��",e.getMessage());
        }
    }
    /**
     * ���÷�¼F7
     * @param col ���� kdtEntrys.getColumn("boxType")
     * @param ConditionSQL ��������SQL(�������ֵ������""�����������)
     *        ��   " fbilltypestatr = '1' and (entrys.id is null or entrys.seq = 1)"
     * @param Query ���� ����"com.kingdee.eas.cmt.basedata.app.OperatorOrgQuery"
     * @param EditFrmat ���� ����"$number$"
     * @param DisplayFormat ���� ����"$name$"
     * @param CommitFormat ���� ����"$number$"
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
            alert2("��¼F7��ʼ����������ϵ����Ա��",e.getMessage());
        }

    }
    /**
     * ����ת���ַ���
     * @param date ����
     * @param type ��ʾ��ʽ yyyy-MM-dd yyyy-MM-dd HH:mm:ss
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
                alert("���ڸ�ʽת������");
            }
        }
        return returnvalue;
    }
    /**
     * �ַ���תΪ����
     * @param DateStr �ַ���
     * @param type ���� "yyyy-MM-dd HH:mm:ss"
     * @return Date  java.util.Date
     */
    public static Date StringToDate(String DateStr,String type){
        Date returnvalue = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            returnvalue = sdf.parse(DateStr);
        } catch (ParseException e) {
            alert2("����ת������",DateStr+"-->"+type);
        }
        return returnvalue;
    }
    /**
     * ����DKDatePicker�ؼ���ʾ��ʽ
     * @param date ���ڿ��� 
     * @param dateType ��ʽ��ʽ ����"yyyy-MM-dd HH:mm:ss"      "yyyy-MM-dd"
     */
    public static void setKDDatePicker(KDDatePicker date,String dateType){
        date.setDatePattern(dateType);
    }
    /**
     * ��ȡ��ǰʱ��(KDDatePicker�ؼ�)Ĭ��ֵ ���磺����ʱ�� �޸�ʱ�� ���ʱ��
     * @return java.sql.Timestamp ��ǰʱ��
     */
    public static java.sql.Timestamp getTime(){
        java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
        return time;
    }

    /**
     * ���ļ�
     * @param FilePath �ļ�·��
     */
    public static void OpenFile(String FilePath){
        try {
            Runtime.getRuntime().exec("cmd /c start \"\" \""+FilePath.replaceAll("\\\\", "\\\\\\\\")+"\"");
        } catch (IOException e) {
            alert2("���ļ�����",FilePath);
        }
    }
    /**
     * ���ļ�ѡ����
     * @return �ļ�·��
     */
    public static String OpenFilesSelect(){
        String returnvalue = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("��ѡ���ļ�");
        chooser.showDialog(null, "ȷ��");
        if(chooser.getSelectedFile()!=null){
            File file = chooser.getSelectedFile();
            returnvalue = file.getPath();
        }
        return returnvalue;
    }

    /**
     * ��Ŀ¼ѡ����
     * @return
     */
    public static String OpenPathSelect(){
        String returnvalue = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("��ѡ��Ŀ¼");
        chooser.showDialog(null,"����");
        if(chooser.getSelectedFile()!=null){
            File file = chooser.getSelectedFile();
            returnvalue = file.getPath();
        }
        return returnvalue;
    }
    /**
     * ��Excel�ļ���������
     * @param File 
     * @param sheetNum ���������
     * @param y ��
     * @param x ��
     * @param value ����
     */
    public static void setExcelValue(String File,int sheetNum,int x,int y,String value){
        try {
            File file = new File(File);
            //����ҵ���ͬ���ļ���ִ��ɾ��
            if(!file.exists() && !file.isFile()){
                return;
            }
            //Excel����ļ� 
            Workbook wb = Workbook.getWorkbook(new File(File)); 
            //��һ���ļ��ĸ���������ָ������д�ص�ԭ�ļ� 
            WritableWorkbook book = Workbook.createWorkbook(new File(File),wb); 
            //��ȡ������ 
            WritableSheet sheet=book.getSheet(sheetNum);
            sheet.addCell(new Label(y,x,value)); 
            book.write(); 
            book.close(); 
        } catch (Exception e) {
        }  
    }
    /**
     * ��ȡExcel�ļ�
     * @param File �ļ���
     * @param sheetNum ���������
     * @param y ��
     * @param x ��
     */
    public static String getExcelValue(String File,int sheetNum,int y,int x){
        String result = "";
        try {
            File file = new File(File);
            //����ҵ���ͬ���ļ���ִ��ɾ��
            if(!file.exists() && !file.isFile()){
                alert(File+"�ļ�û�ҵ���");
            }
            Workbook book= Workbook.getWorkbook(new File(File)); 
            //��õ�һ����������� 
            Sheet sheet=book.getSheet(sheetNum); 
            //�õ���һ�е�һ�еĵ�Ԫ�� 
            Cell cell1=sheet.getCell(x,y); 
            result=cell1.getContents().toString(); 
            book.close();
        } catch (FileNotFoundException e) {
            alert2("��ȡExcel�ļ�����","��رյ�ǰ�򿪵�Excel�ļ�");
        } catch (BiffException e) {
            alert2("��ȡExcel�ļ�����",e.toString());
        } catch (IOException e) {
            alert2("��ȡExcel�ļ�����",e.toString());
        }
        return result;
    }

    /**
     * ��ȡExcel�ļ�(��һsheetҳ�е�����)
     * @param File �ļ�·��
     * @param sheetNum sheetҳ��
     * @return ��ά����
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
            alert2("��ȡExcel�ļ�����","��رյ�ǰ�򿪵�Excel�ļ�");
        } catch (BiffException e) {
            alert2("��ȡExcel�ļ�����",e.toString());
        } catch (IOException e) {
            alert2("��ȡExcel�ļ�����",e.toString());
        }
        return returnvalue;
    }

    /***
     * ���ͼ�ʱ��Ϣ
     * @param FSuser ������ID
     * @param JSuser ������ID
     * @param MessageTitle ����
     * @param Messages ����
     */
    public static void addMessage(String FSuser,String JSuser,String MessageTitle,String Messages){
        try {
            IBMCMessage i = BMCMessageFactory.getRemoteInstance();
            BMCMessageInfo info = new BMCMessageInfo(); 
            info.setType(MsgType.ONLINE);// ��Ϣ���ͣ�����֪ͨ��Ϣ��������Ϣ��״̬������Ϣ
            info.setBizType(MsgBizType.ONLINE);// ҵ�����ͣ����繤������Ԥ��ƽ̨
            info.setPriority(MsgPriority.HIGH); // ���ȼ�
            info.setStatus(MsgStatus.UNREADED); // ��Ϣ״̬
            info.setReceiver(JSuser); // ������ID ��User��ID������Person��ID��
            info.setSender(FSuser);// ��Ϣ������
            info.setTitle(MessageTitle); // ��Ϣ����
            info.setBody(Messages);// ��Ϣ����
            i.submit(info);
        } catch (Exception e) {
            alert2("���ͼ�ʱ��Ϣ����","���⣺"+MessageTitle+"  ����:"+Messages);
        }
    }



    /**
     * ��ת��
     * @param list ���ݼ���
     * @param delimiter �ָ��� ����","
     * @param bracketsLeft �����ŷ���
     * @param bracketsRight �����ŷ���
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
     * �򿪴���
     * @param URL UI��ַ
     * @param ctx ��������
     * @param openType �򿪴������� ��:UIFactoryName.MODEL
     * @param billStatus ����״̬  ����OprtState.ADDNEW
     * 
     *  
        ����ͨUIҳ��
        HashMap cix = new HashMap();
        String orderid = "asiofjlqkjwfklaslkasdf="
        cix.put("orderid", orderid);
        UtilClass.openUI("com.kingdee.eas.cmt.commission.client.GoodsUI", cix, UIFactoryName.MODEL, OprtState.ADDNEW);

        �򿪵���EditUIҳ��
         HashMap cix = new HashMap();
        cix.put("ID", fid);
        UtilClass.openUI("com.kingdee.eas.cmt.commission.client.CmtTranConsignEditUI", cix, UIFactoryName.NEWWIN, OprtState.VIEW);


        �ڴ򿪵ĵ��ݻ�ȡǰ��ҳ�洫���Ĳ���
        this.getUIContext().get("orderid").toString()
     *
     *
     */
    public static void openUI(String URL,HashMap ctx,String openType,String billStatus){
        try {
            IUIWindow ui = UIFactory.createUIFactory(openType).create(URL, ctx, null, billStatus);
            ui.show();
        } catch (UIException e) {
            alert2("����UI�������:",URL);
        }
    }

    /**
     * ���Ͳ���
     * @param ContextID �������
     * @param values ����ֵ
     */
    public static void setSysContext(String ContextID,Object values){
        SysContext.getSysContext().setProperty(ContextID, values);
    }

    /**
     * ��ȡ����
     * @param ContextID �������
     * @return ����ֵ(Object)
     */
    public static Object getSysContext(String ContextID){
        return SysContext.getSysContext().getProperty(ContextID);
    }

    /**
     * ��ȡUI����
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
     * �������ݱ��
     * @param Parameter �������
     *        ����˵��:
     *               logo          ���ͷ��ʾ
     *               date          ����ʱ��
     *               Delimiter  �ָ���
     *               digit         ���λ��
     *               isTissueIsolation   �Ƿ���֯����(0:������1:����)
     *               table      ����
     * @return String ���ݱ��
     * 
     *  HashMap Parameter = new HashMap();
        //���ͷ��ʾ����
        Parameter.put("logo", "CMT");
        //����
        Parameter.put("date", UtilClass.DateToString(UtilClass.getTime(), "yyyyMMdd"));
        //�ָ���
        Parameter.put("Delimiter", "-");
        //���λ��
        Parameter.put("digit", "5");
        //�Ƿ������֯0Ϊ������1Ϊ����
        Parameter.put("isTissueIsolation", "0");
        //���ݱ���
        Parameter.put("table", "T_BAS_VehicleType");
        String billNum = UtilClass.createrBillNumber(Parameter);
     * 
     */
    public static String createrBillNumber(HashMap Parameter){
        StringBuffer returnvalue = new StringBuffer();
        //���ͷLogo
        if(Parameter.get("logo")!=null){
            returnvalue.append(Parameter.get("logo"));
        }
        //���ʱ��
        if(Parameter.get("date")!=null){
            if(Parameter.get("Delimiter")!=null){
                returnvalue.append(Parameter.get("Delimiter"));
            }
            returnvalue.append(Parameter.get("date"));
        }
        //�������λ(digit:���λ��)
        if(Parameter.get("digit")!=null){
            StringBuffer getDigitSQL = new StringBuffer();
            Integer digit = new Integer(Parameter.get("digit").toString());
            StringBuffer digitValue = new StringBuffer();
            for(int i=0;i<digit.intValue();i++){
                digitValue.append("0");
            }
            getDigitSQL.append("select trim(to_char(count(*)+1,'"+digitValue+"')) from "+Parameter.get("table")+"  ");
            //�Ƿ���֯����
            if("1".equals(Parameter.get("isTissueIsolation"))){
                getDigitSQL.append(" where FControlUnitID = '"+getCU().getId()+"'");
            }
            if(Parameter.get("Delimiter")!=null){
                returnvalue.append(Parameter.get("Delimiter"));
            }
            //��ȡ���ݿ��¼��
            returnvalue.append(executeQueryString(getDigitSQL.toString()));
        }
        return returnvalue.toString();
    }

    /**
     *  ��ǰ��¼��֯
     * @return
     */
    public static CtrlUnitInfo getCU(){
        return SysContext.getSysContext().getCurrentCtrlUnit();
    }

    /**
     * �жϵ�ǰ��֯�Ƿ�Ϊ����ҪĿ¼
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
     * ��ȡ������֯ID
     * @return
     */
    public static CtrlUnitInfo getRootCU(){
        CtrlUnitInfo cinfo = new CtrlUnitInfo();
        try {
            CtrlUnitCollection cinfos = CtrlUnitFactory.getRemoteInstance().getCtrlUnitCollection("select * where level = 1 ");
            cinfo = cinfos.get(0);
        } catch (BOSException e) {
            alert2("��ȡ������֯ID����",e.getMessage());
        }
        return cinfo;
    }
    /**
     * ��ǰ��¼�û�
     * @return
     */
    public static UserInfo getUser(){
        return SysContext.getSysContext().getCurrentUserInfo();
    }
    /**
     * ��ǰ��½��Ա
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
     * ��ǰ��¼��Ա����
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
     * ͨ��fid��ȡ����
     * @param fid
     * @return ����
     */
    public static String getDBTableName(String fid){
        String Tablename = "";
        com.kingdee.bos.util.BOSObjectType bosType = BOSUuid.read(fid).getType();
        try {
            Tablename = FMIsqlFacadeFactory.getRemoteInstance().getTableNameByBosType(bosType);
        } catch (BOSException e) {
            alert2("��ȡ��������",fid);
        }
        return Tablename;
    }

    /**
     * ִ��SQL����ѯ�����ؼ��ϣ�
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
            alert2("ִ��SQL����",sql);
        }
        return returnvalue;
    }
    /**
     * ִ��SQL(��ѯ�����ص���ֵ)
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
            alert2("ִ��SQL����",sql);
        }
        return returnvalue;
    }
    /**
     * ִ��SQL(�������޸�)
     * @param sql
     */
    public static void executeSql(String sql){
        try {
            IFMIsqlFacade db = com.kingdee.eas.fm.common.FMIsqlFacadeFactory.getRemoteInstance();
            db.executeSql(" /*dialect*/ "+sql);
        } catch (Exception e) {
            alert2("ִ��SQL����",sql);
        }
    }

    /**
     * SQL���ݵ�����Excel�ļ�
     * @param sql
     * @param FileName �ļ���
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
            //����ҵ���ͬ���ļ���ִ��ɾ��
            if(file.exists() && file.isFile()){
                file.delete();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(new File(File));
            //����������
            wwb.createSheet("sheet1", 0);
            //��ȡ������ 
            WritableSheet ws = wwb.getSheet(0);

            //��ͷ����ʽ
            WritableCellFormat TableHead = new WritableCellFormat();
            TableHead.setBorder(Border.ALL, BorderLineStyle.THIN);
            TableHead.setAlignment(Alignment.CENTRE);
            TableHead.setBackground(Colour.GRAY_25);

            //������������ʽ
            WritableCellFormat TableRow = new WritableCellFormat();
            TableRow.setAlignment(Alignment.CENTRE);

            IRowSet rs = UtilClass.executeQuery(sql);
            //��������
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
            alert2("������������Excel�ļ�����",sql);
        }
        return returnvalue;
    }


    /**
     * ��װ�������ȷ����֤
     * @param BoxNum ���
     * @return Boolean
       if(!UtilClass.BoxVerification(boxnum)){
            if(!UtilClass.alertReturn("��ţ�"+boxnum+" ���ǹ��ʱ�׼��ţ��Ƿ񱣴棿")){
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

        //ȥ��������߿ո�
        String newBoxNum = BoxNum.trim();

        //�ж�����Ƿ�Ϊ���ʱ�׼11λ
        if(newBoxNum.length() != 11){
            return false;
        }

        //�ж�ǰ��λΪ��ĸ  �^�eС��
        for(int i=0;i<4;i++){
            String Nums = newBoxNum.substring(i, i+1);
            char chs[] = Nums.toCharArray();
            if((int)chs[0]<65 || (int)chs[0]>90){
                return false;
            }
        }

        //�жϺ�7λΪ����
        for(int i=4;i<11;i++){
            String Nums = newBoxNum.substring(i, i+1);
            char chs[] = Nums.toCharArray();
            if((int)chs[0]<48 || (int)chs[0]>57){
                return false;
            }
        }

        //�жϵ�11����֤���Ƿ���ȷ
        double VerificationNumSum = 0;
        for(int i=0;i<10;i++){
            //��ȡ��ǰλ��ĸ�����ֶ�Ӧ����ֵ
            double bjdata = ((Integer)bj.get(newBoxNum.substring(i, i+1))).doubleValue();
            //��ȡ��ǰλ��Ӧ��2��i�η�
            double jfdata = Math.pow(2,i);
            VerificationNumSum+=bjdata*jfdata;
        }
        int VerificationNum =(int)VerificationNumSum%11;
        int sub11 = new Integer(newBoxNum.substring(10)).intValue();
        //������������11λ���Ƿ����
        if(VerificationNum!=sub11){
            return false;
        }
        return true;
    }

    /**
     * ��oracle���ݶ�ȡͼƬ�ֽ�������ͼƬ�ļ�
     * @param img_num ͼƬ���(��������)
     * @param file �ļ���ַ "c://bmw.png"

            ϵͳ������
            -- ������
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
            -- �����ֶ�˵�� 
            comment on column T_SYS_FUTVAN.FNUMBER
              is '�������';
            comment on column T_SYS_FUTVAN.FNAME
              is '��������';
            comment on column T_SYS_FUTVAN.PARAMETER_STRING
              is '�ı��Ͳ���';
            comment on column T_SYS_FUTVAN.PARAMETER_NUMBER
              is '�����Ͳ���';
            comment on column T_SYS_FUTVAN.PARAMETER_DATE
              is '�����Ͳ���';
            comment on column T_SYS_FUTVAN.PARAMETER_TIMESTAMP
              is 'ʱ���Ͳ���';
            comment on column T_SYS_FUTVAN.PARAMETER_BLOB
              is '�ֽ��Ͳ���';
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
     * BOTP����ת��
     * @param botpNum ת��������
     * @param BillInfo ԭ����
     */
    public static void BOTP(String botpNum,CoreBillBaseInfo BillInfo){
        String error = "";
        try {
            // ȡ��BOPT��ӳ��
            BOTMappingCollection botmapping = BOTMappingFactory.getRemoteInstance().getBOTMappingCollection("select * where    name = '"+botpNum+"'  ");
            BOTMappingInfo btpMappingInfo = null;
            if (botmapping !=null && botmapping.size() == 1) {
                btpMappingInfo = botmapping.get(0);
            } else {
                if(botmapping==null || botmapping.size()<1){
                    error = "δ�ҵ�ת������  ������:"+botpNum;
                }
                if(botmapping.size()>1){
                    error = "�ҵ�����ת��������ɾ���ظ�����   ������:"+botpNum;
                }
                throw new Exception();
            }
            //ִ�е���ת��
            BTPTransformResult transformResult = BTPManagerFactory.getRemoteInstance().transform(BillInfo, btpMappingInfo);

            //ȡ��Ŀ�굥���б�
            IObjectCollection toBillList = transformResult.getBills();
            
            //����Ŀ�굥��
            for (int i = 0; i < toBillList.size(); i++) {   
                CoreBillBaseInfo destBillInfo = (CoreBillBaseInfo) toBillList.getObject(i);
                BTPManagerFactory.getRemoteInstance().saveRelations(destBillInfo, transformResult.getBOTRelationCollection());
            }
            
        } catch (Exception e) {
            if("".equals(error) || error == null){
                alert2("����ת������",e.getMessage());
            }else{
                alert2("����ת������",error);
            }
        }
    }
    
    /**
     * ��̯����
     * @param Total ����
     * @param Allocations ��̯��ϸ
     * @param newScale С��λ��
     * @return ��̯�ּ���
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
            //��ȡ��ϸ����
            for(int i=0;i<Allocations.length;i++){
                if(Allocations[i][1]!=null&&!"".equals(Allocations[i][1])){
                    sum = sum.add(new BigDecimal(Allocations[i][1]));
                }
            }
            
            //��������̯
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
            
            //�жϷ�̯��ÿ����¼�����Ƿ���������ͬ
            for(int i=0;i<returnvalue.length;i++){
                if(returnvalue[i][1]!=null&&!"".equals(returnvalue[i][1])){
                    Allocationsum = Allocationsum.add(new BigDecimal(returnvalue[i][1]));
                }
            }
            
            //�����̯�������̯ǰ������һ�ȡ��ڷ�̯��¼���һ�������ϡ���̯���������̯�����Ĳ�ֵ
            if(Allocationsum.compareTo(Total)!=0){
                BigDecimal xz = new BigDecimal(returnvalue[returnvalue.length-1][1]).subtract(Allocationsum.subtract(Total));
                returnvalue[returnvalue.length-1][1] = xz.toString();
            }
        } catch (Exception e) {
            StringBuffer er = new StringBuffer();
            for(int i=0;i<Allocations.length;i++){
                er.append("| "+Allocations[i][0]+"   "+Allocations[i][1]+" |");
            }
            alert2("��̯����","��̯����:"+Total.toString()+"  ��ϸ:"+er.toString());
        }
        return returnvalue;
    }
    
}