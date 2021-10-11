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
	 * 删除单据对应的所有附件，操作成功时返回ture，用于前端
	 * @param billId 单据主键
	 * 
	 */
	public static boolean delete(String billId) throws BOSException, EASBizException{
		
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //附件与业务对象关联接口
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    	if( null == (coll)){
	    		return false;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
	    	IObjectPK[] pks = new ObjectUuidPK[coll.size()];
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //附件关联对象
	        	AttachmentInfo attachment = bo.getAttachment(); //附件对象
	    		pks[i] = new ObjectUuidPK(attachment.getId());
	    		
	    	}
	    	iAttachment.delete(pks); //删除附件
	    	iBoAttchAsso.delete(filter); //删除附件关联
    	
		return true;
		
	}
	 
	/**
	 * 删除单据对应的所有附件，操作成功时返回ture，用于后台
	 * @param ctx 上下文
	 * @param billId 单据主键
	 * 
	 */
	public static boolean delete(Context ctx, String billId) throws BOSException, EASBizException{
		
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //附件与业务对象关联接口
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    	if( null ==  (coll)){
	    		return false;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
	    	IObjectPK[] pks = new ObjectUuidPK[coll.size()];
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //附件关联对象
	        	AttachmentInfo attachment = bo.getAttachment(); //附件对象
	    		pks[i] = new ObjectUuidPK(attachment.getId());
	    		
	    	}
	    	iAttachment.delete(pks); //删除附件
	    	iBoAttchAsso.delete(filter); //删除附件关联
    	
		return true;
		
	}
	
	/**
	 * 附件上传，金蝶EAS标准产品功能（用于前端）
	 * @param billId 单据主键
	 * @param filePath 附件路径（不含文件名）
	 * @param fileName 附件名称（含后缀）
	 * @return 附件ID
	 * 
	 */
	public static String upload(String billId, String filePath, String fileName){
		
		String attachId = null;
		if(!filePath.endsWith("/")) filePath += "/";
		AttachmentClientManager manager = AttachmentManagerFactory.getClientManager();
		try {
			byte[] bytes = getBytes(filePath + fileName); //获取字节流
			SimpleAttachmentInfo simple = new SimpleAttachmentInfo();
			simple.setContent(bytes);
			simple.setMainName(fileName.substring(0, fileName.indexOf("."))); //文件名
			simple.setExtName(fileName.substring(fileName.indexOf(".") + 1, fileName.length())); //后缀
			attachId = manager.addNewAttachment(billId, simple);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return attachId;
		
	}
	
	/**
	 * 附件上传，金蝶EAS标准产品功能（用于后台）
	 * @param ctx 上下文
	 * @param billId 单据主键
	 * @param filePath 附件路径（不含文件名）
	 * @param fileName 附件名称（含后缀）
	 * @return 附件ID
	 * 
	 */
	public static String upload(Context ctx, String billId, String filePath, String fileName){
		
		String attachId = null;
		if(!filePath.endsWith("/")) filePath += "/";
		AttachmentServerManager manager = AttachmentManagerFactory.getServerManager(ctx);
		try {
			byte[] bytes = getBytes(filePath + fileName); //获取字节流
			SimpleAttachmentInfo simple = new SimpleAttachmentInfo();
			simple.setContent(bytes);
			simple.setMainName(fileName.substring(0, fileName.indexOf("."))); //文件名
			simple.setExtName(fileName.substring(fileName.indexOf(".") + 1, fileName.length())); //后缀
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
		if("B65CCEF1".equals(billType)){//转正
			uipk = "com.kingdee.eas.hr.affair.app.EmpHireBizBil_gerenzz.form";
			propertyName ="null0";
			der = "上传试用期总结";
		}if("D74B9931".equals(billType)){//录用
			uipk = "com.kingdee.eas.custom.luyongbaopi.app.Luyongbaopinew_zhegnxin.form";
			propertyName = "protocolne";
			der = "征信文件";
		}if("CC58A617".equals(billType)){//离职
			uipk = "com.kingdee.eas.hr.affair.app.ResignBizBill_cwsp.form";
			propertyName = "protocolng";
			der = "其他附件";
		}if("C0DAD00D".equals(billType)){//公司内
			uipk = "com.kingdee.eas.hr.affair.app.FluctuationBizBill.formywbdAll";
			propertyName = "null0";
			der = "其他附件";
		}if("E41B9A4E".equals(billType)){//跨公司
			uipk = "com.kingdee.eas.hr.affair.app.FlucInBizBillywbd.from";
			propertyName = "null0";
			der = "其他附件";
		}	 
		
		String userId = ContextUtil.getCurrentUserInfo(ctx).getId().toString();
		SHRAttachmentExtInfo attchExt = new SHRAttachmentExtInfo();
		String fullname = file.getName();//附件全称
		String mainname = fullname.substring(0, fullname.lastIndexOf('.'));//附件名称
		String extname = fullname.substring(fullname.lastIndexOf('.')+1, fullname.length());//附件格式
		extname = extname.toLowerCase();
		byte[] content = getBytes(file);
		//附件
		AttachmentInfo ai=new AttachmentInfo();
		ai.setName(mainname);
		ai.setSimpleName(extname);
		ai.setDescription(description);
		ai.setFile(content);
		ai.setIsShared(false);
		ai.setSharedDesc("否");
		int size = (int) file.length();
		if(size<1024){
			ai.setSize(size+"字节");
		}else{
			ai.setSize(size/1024+"KB");
		}
		ai.setSizeInByte(size);
		ai.setAttachID(""+System.currentTimeMillis());
		ai.setBeizhu(uipk);
		ai.setDescription(der);
		ai.setType(getFileType(fullname));
		//附件保存在附件拓展中
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
		//保存附件
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
			return "Microsoft Word 文档";
		} else if ("xls".equalsIgnoreCase(extname) || "xlsx".equalsIgnoreCase(extname) || "xlsm".equalsIgnoreCase(extname) || "xlsb".equalsIgnoreCase(extname)) {
			return "Microsoft Excel 表格";
		} else if ("ppt".equalsIgnoreCase(extname) || "pptx".equalsIgnoreCase(extname) || "pptm".equalsIgnoreCase(extname)) {
			return "Microsoft PowerPoint 幻灯片";
		} else if ("txt".equalsIgnoreCase(extname)) {
			return "TEXT 文本文件";
		}
		return "未知文件类型(." + extname + ")";
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
        //确保所有数据均被读取
        if (offset != bytes.length) {
        	throw new Exception("Could not completely read file " + file.getName());
        }
        in.close();
        
        return bytes;
    
	}
	
	/**
	 * 根据文件路径读取字节流
	 * @param filePath 文件路径
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
	        //确保所有数据均被读取
	        if (offset != bytes.length) {
	        	throw new Exception("Could not completely read file " + file.getName());
	        }
	        in.close();
	        
	        return bytes;
        
    }
	
	/**
	 * 获取单据对应的单一附件，用于前端
	 * @param billId 单据主键
	 * @return 附件对象
	 * 
	 */
	public static AttachmentInfo getAttach(String billId) throws BOSException, EASBizException{
		
		AttachmentInfo attachment = null;
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //附件与业务对象关联接口
	    EntityViewInfo view = new EntityViewInfo();
	    FilterInfo filter = new FilterInfo();
	    filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    view.setFilter(filter);
	    BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    if( null ==  (coll)){
	    	return attachment;
	    }
	    IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
		BoAttchAssoInfo bo = coll.get(0); //附件关联对象
    	attachment = bo.getAttachment(); //附件对象
		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
    	
		return attachment;
		
	}
	
	/**
	 * 获取单据对应的单一附件，用于后台
	 * @param ctx 上下文
	 * @param billId 单据主键
	 * @return 附件对象
	 * 
	 */
	public static AttachmentInfo getAttach(Context ctx, String billId) throws BOSException, EASBizException{
		
		AttachmentInfo attachment = null;
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //附件与业务对象关联接口
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    	if( null == (coll)){
	    		return attachment;
	    	}
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
		BoAttchAssoInfo bo = coll.get(0); //附件关联对象
		
	    	attachment = bo.getAttachment(); //附件对象
	    	SelectorItemCollection selectors = new SelectorItemCollection();
	    	selectors.add(new SelectorItemInfo("id"));
	    	selectors.add(new SelectorItemInfo("number"));
	    	selectors.add(new SelectorItemInfo("name"));
	    	selectors.add(new SelectorItemInfo("file"));
		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()), selectors);
    	
		return attachment;
		
	}
	
	/**
	 * 获取附件的文件名，包含名称和后缀
	 * @param attach 附件对象
	 * @return 文件名
	 * 
	 */
	public static String getAttach(AttachmentInfo attach){
		
		String fileName = null;
		fileName = attach.getName() + "." + attach.getSimpleName();
		
		return fileName;
		
	}
	
	/**
	 * 获取单据对应的所有附件，用于前台
	 * @param billId 单据主键
	 * @return 附件对象集合
	 * 
	 */
	public static AttachmentCollection getAttaches(String billId) throws BOSException, EASBizException{
		
		AttachmentCollection attaches = null; //附件集合
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getRemoteInstance(); //附件与业务对象关联接口
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    	if( null == (coll)){
	    		return attaches;
	    	}
	    	attaches = new AttachmentCollection();
	    	IAttachment iAttachment = AttachmentFactory.getRemoteInstance();
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //附件关联对象
	        	AttachmentInfo attachment = bo.getAttachment(); //附件对象
	    		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
	    		attaches.add(attachment);
	    		
	    	}
    	
		return attaches;
		
	}
	
	/**
	 * 获取单据对应的所有附件，用于后台
	 * @param ctx 上下文
	 * @param billId 单据主键
	 * @return 附件对象集合
	 * 
	 */
	public static AttachmentCollection getAttaches(Context ctx, String billId) throws BOSException, EASBizException{
		
		AttachmentCollection attaches = null; //附件集合
		IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx); //附件与业务对象关联接口
	    	EntityViewInfo view = new EntityViewInfo();
	    	FilterInfo filter = new FilterInfo();
	    	filter.getFilterItems().add(new FilterItemInfo("boID", billId));
	    	view.setFilter(filter);
	    	BoAttchAssoCollection coll = iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	    	if( null == (coll)){
	    		return attaches;
	    	}
	    	attaches = new AttachmentCollection();
	    	IAttachment iAttachment = AttachmentFactory.getLocalInstance(ctx);
	    	for(int i = 0; i < coll.size(); i++){
	    		
	    		BoAttchAssoInfo bo = coll.get(i); //附件关联对象
	        	AttachmentInfo attachment = bo.getAttachment(); //附件对象
	    		attachment = iAttachment.getAttachmentInfo(new ObjectUuidPK(attachment.getId()));
	    		attaches.add(attachment);
	    		
	    	}
    	
		return attaches;
		
	} 
}
