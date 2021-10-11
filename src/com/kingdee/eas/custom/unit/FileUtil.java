package com.kingdee.eas.custom.unit;



import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.attachment.AttachmentCollection;
import com.kingdee.eas.base.attachment.AttachmentFactory;
import com.kingdee.eas.base.attachment.AttachmentInfo;
import com.kingdee.eas.base.attachment.BoAttchAssoCollection;
import com.kingdee.eas.base.attachment.BoAttchAssoFactory;
import com.kingdee.eas.base.attachment.BoAttchAssoInfo;
import com.kingdee.eas.base.attachment.IAttachment;
import com.kingdee.eas.base.attachment.IBoAttchAsso;
import com.kingdee.eas.base.attachment.common.AttachmentClientManager;
import com.kingdee.eas.base.attachment.common.AttachmentManagerFactory;
import com.kingdee.eas.base.attachment.common.AttachmentServerManager;
import com.kingdee.eas.base.attachment.common.SimpleAttachmentInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.shr.attachment.AttachmentState;
import com.kingdee.shr.attachment.AttachmentTypeEnum;
import com.kingdee.shr.attachment.ISHRAttachmentExt;
import com.kingdee.shr.attachment.SHRAttachmentExtFactory;
import com.kingdee.shr.attachment.SHRAttachmentExtInfo;


public class FileUtil {
	/**
	 * ɾ�����ݶ�Ӧ�����и����������ɹ�ʱ����ture������ǰ��
	 * @param billId ��������
	 * 
	 */
	public static boolean delete(String billId) throws BOSException, EASBizException{
		
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //������ҵ���������ӿ�
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    	if( null == (coll)){
	    		return false;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
	    	IObjectPK[] pks = new ObjectUuidPK[coll.size()];
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //������������
	        	AttachmentInfo attachment = bo.getAttachment(); //��������
	    		pks[i] = new ObjectUuidPK(attachment.getId());
	    		
	    	}
	    	iAttachment.delete(pks); //ɾ������
	    	iBoAttchAsso.delete(filter); //ɾ����������
    	
		return true;
		
	}
	 
	/**
	 * ɾ�����ݶ�Ӧ�����и����������ɹ�ʱ����ture�����ں�̨
	 * @param ctx ������
	 * @param billId ��������
	 * 
	 */
	public static boolean delete(Context ctx, String billId) throws BOSException, EASBizException{
		
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //������ҵ���������ӿ�
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    	if( null ==  (coll)){
	    		return false;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
	    	IObjectPK[] pks = new ObjectUuidPK[coll.size()];
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //������������
	        	AttachmentInfo attachment = bo.getAttachment(); //��������
	    		pks[i] = new ObjectUuidPK(attachment.getId());
	    		
	    	}
	    	iAttachment.delete(pks); //ɾ������
	    	iBoAttchAsso.delete(filter); //ɾ����������
    	
		return true;
		
	}
	
	/**
	 * �����ϴ������EAS��׼��Ʒ���ܣ�����ǰ�ˣ�
	 * @param billId ��������
	 * @param filePath ����·���������ļ�����
	 * @param fileName �������ƣ�����׺��
	 * @return ����ID
	 * 
	 */
	public static String upload(String billId, String filePath, String fileName){
		
		String attachId = null;
		if(!filePath.endsWith("/")) filePath += "/";
		AttachmentClientManager manager = AttachmentManagerFactory.getClientManager();
		try {
			byte[] bytes = getBytes(filePath + fileName); //��ȡ�ֽ���
			SimpleAttachmentInfo simple = new SimpleAttachmentInfo();
			simple.setContent(bytes);
			simple.setMainName(fileName.substring(0, fileName.indexOf("."))); //�ļ���
			simple.setExtName(fileName.substring(fileName.indexOf(".") + 1, fileName.length())); //��׺
			attachId = manager.addNewAttachment(billId, simple);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return attachId;
		
	}
	
	/**
	 * �����ϴ������EAS��׼��Ʒ���ܣ����ں�̨��
	 * @param ctx ������
	 * @param billId ��������
	 * @param filePath ����·���������ļ�����
	 * @param fileName �������ƣ�����׺��
	 * @return ����ID
	 * 
	 */
	public static String upload(Context ctx, String billId, String filePath, String fileName){
		
		String attachId = null;
		if(!filePath.endsWith("/")) filePath += "/";
		AttachmentServerManager manager = AttachmentManagerFactory.getServerManager(ctx);
		try {
			byte[] bytes = getBytes(filePath + fileName); //��ȡ�ֽ���
			SimpleAttachmentInfo simple = new SimpleAttachmentInfo();
			simple.setContent(bytes);
			simple.setMainName(fileName.substring(0, fileName.indexOf("."))); //�ļ���
			simple.setExtName(fileName.substring(fileName.indexOf(".") + 1, fileName.length())); //��׺
			attachId = manager.addNewAttachment(ctx, billId, simple);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return attachId;
		
	}
	public static String upload(Context ctx , String billId, File file) throws Exception{
			
		IAttachment attachment =  AttachmentFactory.getLocalInstance(ctx);
		ISHRAttachmentExt shrAttchExt = SHRAttachmentExtFactory.getLocalInstance(ctx);
		IBoAttchAsso attchAsso = BoAttchAssoFactory.getLocalInstance(ctx);
		String boID = billId ; 
		
		String description = "";
		final ObjectUuidPK pk = new ObjectUuidPK(billId);
		String billType = pk.getObjectType().toString();
		
		String propertyName = "protocolne";
		String der = "";
		String uipk = "com.kingdee.eas.custom.luyongbaopi.app.Luyongbaopinew_zhegnxin.form";
		if("B65CCEF1".equals(billType)){//ת��
			uipk = "com.kingdee.eas.hr.affair.app.EmpHireBizBil_gerenzz.form";
			propertyName ="null0";
			der = "�ϴ��������ܽ�";
		}if("D74B9931".equals(billType)){//¼��
			uipk = "com.kingdee.eas.custom.luyongbaopi.app.Luyongbaopinew_zhegnxin.form";
			propertyName = "protocolne";
			der = "�����ļ�";
		}if("CC58A617".equals(billType)){//��ְ
			uipk = "com.kingdee.eas.hr.affair.app.ResignBizBill_cwsp.form";
			propertyName = "protocolng";
			der = "��������";
		}if("C0DAD00D".equals(billType)){//��˾��
			uipk = "com.kingdee.eas.hr.affair.app.FluctuationBizBill.formywbdAll";
			propertyName = "null0";
			der = "��������";
		}if("E41B9A4E".equals(billType)){//�繫˾
			uipk = "com.kingdee.eas.hr.affair.app.FlucInBizBillywbd.from";
			propertyName = "null0";
			der = "��������";
		}	 
		
		String userId = ContextUtil.getCurrentUserInfo(ctx).getId().toString();
		SHRAttachmentExtInfo attchExt = new SHRAttachmentExtInfo();
		String fullname = file.getName();//����ȫ��
		String mainname = fullname.substring(0, fullname.lastIndexOf('.'));//��������
		String extname = fullname.substring(fullname.lastIndexOf('.')+1, fullname.length());//������ʽ
		extname = extname.toLowerCase();
		byte[] content = getBytes(file);
		//����
		AttachmentInfo ai=new AttachmentInfo();
		ai.setName(mainname);
		ai.setSimpleName(extname);
		ai.setDescription(description);
		ai.setFile(content);
		ai.setIsShared(false);
		ai.setSharedDesc("��");
		int size = (int) file.length();
		if(size<1024){
			ai.setSize(size+"�ֽ�");
		}else{
			ai.setSize(size/1024+"KB");
		}
		ai.setSizeInByte(size);
		ai.setAttachID(""+System.currentTimeMillis());
		ai.setBeizhu(uipk);
		ai.setDescription(der);
		ai.setType(getFileType(fullname));
		//���������ڸ�����չ��
		attchExt.setAttachment(ai);
		attchExt.setName(fullname);
		attchExt.setPropertyName(propertyName);
		if(propertyName.startsWith("null")){
			attchExt.setType(AttachmentTypeEnum.FORM);	
		}else{
			attchExt.setType(AttachmentTypeEnum.PROPERTY);
		}
		attchExt.setState(AttachmentState.SAVE);
		attchExt.setBunding(userId + '#' + uipk);
		attchExt.setBoID(boID);
		//���渽��
		attachment.addnew(ai);	
		String id =  shrAttchExt.addnew(attchExt).toString();
		/*BoAttchAssoInfo boAttchAssoInfo = new BoAttchAssoInfo();
		boAttchAssoInfo.setBoID(boID);
		boAttchAssoInfo.setAssoBusObjType(BOSUuid.getBOSObjectType(boID, true) + "");
		boAttchAssoInfo.setAssoType("Added Accessories");
		boAttchAssoInfo.setAttachment(ai);*/
		//String ids = attchAsso.addnew(boAttchAssoInfo).toString();
		// bNsAAAAcG2XjZ7H6  bNsAAAAcG2YXLzpH  
		return id;
			
	}
	
	private static String getFileType(String fullname) {
		String extname = fullname.substring(fullname.lastIndexOf(".") + 1, fullname.length());
		if ("doc".equalsIgnoreCase(extname) || "docx".equalsIgnoreCase(extname)) {
			return "Microsoft Word �ĵ�";
		} else if ("xls".equalsIgnoreCase(extname) || "xlsx".equalsIgnoreCase(extname) || "xlsm".equalsIgnoreCase(extname) || "xlsb".equalsIgnoreCase(extname)) {
			return "Microsoft Excel ���";
		} else if ("ppt".equalsIgnoreCase(extname) || "pptx".equalsIgnoreCase(extname) || "pptm".equalsIgnoreCase(extname)) {
			return "Microsoft PowerPoint �õ�Ƭ";
		} else if ("txt".equalsIgnoreCase(extname)) {
			return "TEXT �ı��ļ�";
		}
		return "δ֪�ļ�����(." + extname + ")";
	}
	
	public static byte[] getBytes(File file) throws Exception {
		 
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            return null;
        }
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[(int) fileSize];
        int offset = 0;
        int read = 0;
        while (offset < bytes.length && (read = in.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += read;
        }
        //ȷ���������ݾ�����ȡ
        if (offset != bytes.length) {
        	throw new Exception("Could not completely read file " + file.getName());
        }
        in.close();
        
        return bytes;
    
	}
	
	/**
	 * �����ļ�·����ȡ�ֽ���
	 * @param filePath �ļ�·��
	 * @return byte[]
	 * 
	 */
	public static byte[] getBytes(String filePath) throws Exception {
		
	        File file = new File(filePath);
	        long fileSize = file.length();
	        if (fileSize > Integer.MAX_VALUE) {
	            return null;
	        }
	        FileInputStream in = new FileInputStream(file);
	        byte[] bytes = new byte[(int) fileSize];
	        int offset = 0;
	        int read = 0;
	        while (offset < bytes.length && (read = in.read(bytes, offset, bytes.length - offset)) >= 0) {
	            offset += read;
	        }
	        //ȷ���������ݾ�����ȡ
	        if (offset != bytes.length) {
	        	throw new Exception("Could not completely read file " + file.getName());
	        }
	        in.close();
	        
	        return bytes;
        
    }
	
	/**
	 * ��ȡ���ݶ�Ӧ�ĵ�һ����������ǰ��
	 * @param billId ��������
	 * @return ��������
	 * 
	 */
	public static AttachmentInfo getAttach(String billId) throws BOSException, EASBizException{
		
		AttachmentInfo attachment = null;
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //������ҵ���������ӿ�
	    EntityViewInfo view = new EntityViewInfo();
	    FilterInfo filter = new FilterInfo();
	    filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    view.setFilter(filter);
	    BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    if( null ==  (coll)){
	    	return attachment;
	    }
	    IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
		BoAttchAssoInfo bo = coll.get(0); //������������
    	attachment = bo.getAttachment(); //��������
		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
    	
		return attachment;
		
	}
	
	/**
	 * ��ȡ���ݶ�Ӧ�ĵ�һ���������ں�̨
	 * @param ctx ������
	 * @param billId ��������
	 * @return ��������
	 * 
	 */
	public static AttachmentInfo getAttach(Context ctx, String billId) throws BOSException, EASBizException{
		
		AttachmentInfo attachment = null;
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //������ҵ���������ӿ�
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    	if( null == (coll)){
	    		return attachment;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
		BoAttchAssoInfo bo = coll.get(0); //������������
		
	    	attachment = bo.getAttachment(); //��������
	    	SelectorItemCollection selectors = new SelectorItemCollection();
	    	selectors.add(new SelectorItemInfo("id"));
	    	selectors.add(new SelectorItemInfo("number"));
	    	selectors.add(new SelectorItemInfo("name"));
	    	selectors.add(new SelectorItemInfo("file"));
		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()), selectors);
    	
		return attachment;
		
	}
	
	/**
	 * ��ȡ�������ļ������������ƺͺ�׺
	 * @param attach ��������
	 * @return �ļ���
	 * 
	 */
	public static String getAttach(AttachmentInfo attach){
		
		String fileName = null;
		fileName = attach.getName() + "." + attach.getSimpleName();
		
		return fileName;
		
	}
	
	/**
	 * ��ȡ���ݶ�Ӧ�����и���������ǰ̨
	 * @param billId ��������
	 * @return �������󼯺�
	 * 
	 */
	public static AttachmentCollection getAttaches(String billId) throws BOSException, EASBizException{
		
		AttachmentCollection attaches = null; //��������
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //������ҵ���������ӿ�
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    	if( null == (coll)){
	    		return attaches;
	    	}
	    	attaches = new AttachmentCollection();
	    	IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //������������
	        	AttachmentInfo attachment = bo.getAttachment(); //��������
	    		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
	    		attaches.add(attachment);
	    		
	    	}
    	
		return attaches;
		
	}
	
	/**
	 * ��ȡ���ݶ�Ӧ�����и��������ں�̨
	 * @param ctx ������
	 * @param billId ��������
	 * @return �������󼯺�
	 * 
	 */
	public static AttachmentCollection getAttaches(Context ctx, String billId) throws BOSException, EASBizException{
		
		AttachmentCollection attaches = null; //��������
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //������ҵ���������ӿ�
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //��ѯ����������
	    	if( null == (coll)){
	    		return attaches;
	    	}
	    	attaches = new AttachmentCollection();
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //������������
	        	AttachmentInfo attachment = bo.getAttachment(); //��������
	    		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
	    		attaches.add(attachment);
	    		
	    	}
    	
		return attaches;
		
	} 
}
