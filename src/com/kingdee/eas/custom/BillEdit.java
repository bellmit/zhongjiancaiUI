package com.kingdee.eas.custom;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.common.EASBizException; 
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.hr.affair.EmpHireBizBillEntryFactory;
import com.kingdee.eas.hr.affair.EmpHireBizBillEntryInfo;
import com.kingdee.eas.hr.affair.FluctuationBizBillEntryFactory;
import com.kingdee.eas.hr.affair.FluctuationBizBillEntryInfo;
import com.kingdee.eas.hr.affair.ResignBizBillEntryFactory;
import com.kingdee.eas.hr.affair.ResignBizBillEntryInfo;
import com.kingdee.eas.hr.contract.ContractRenewBizBillEntryFactory;
import com.kingdee.eas.hr.contract.ContractRenewBizBillEntryInfo;
import com.kingdee.shr.cmpdesign.BatchAdjustBillEntryFactory;
import com.kingdee.shr.cmpdesign.BatchAdjustBillFactory;
import com.kingdee.shr.cmpdesign.BatchAdjustBillInfo;

public class BillEdit {


	
    public static Boolean setEditData(final Context ctx, final String billid, final Map<String, String> editmap)   {
    
	    try { 
	    	String  billType = editmap.get("billType");
	    	
	    	UIToolInfo uiInfo = UIToolFactory.getLocalInstance(ctx).getUIToolInfo("select id  where bostype = '" + billType + "'");
	          

	    	UIToolEntryCollection  billCollection  = UIToolEntryFactory.getLocalInstance(ctx).
	    	 	getUIToolEntryCollection(" where  parent = '"+uiInfo.getId().toString()+"' and editNode is not null ");
	    	CoreBaseCollection billcollection = new CoreBaseCollection();
	    	if(billType.equals("3C50B4E6")){
	    		billcollection = BatchAdjustBillFactory.getLocalInstance(ctx).getCollection( " where id = '"+billid+"' " );
	    		System.out.print("------------------------1111111==="+billcollection.size());
	    	}
	    	 
	    	for(int i=0 ;i< billCollection.size() ; i++){
	    		final String title = billCollection.get(i).getEntityAttributes();
	    		final String fieldType = billCollection.get(i).getFieldType();
	    		final String classname = billCollection.get(i).getDbName();
	    		
	    		final int lang = billCollection.get(i).getCrossColumn();
	    		final int hang2  = billCollection.get(i).getBelongToRow();
	    		
	            CoreBaseInfo  cloneEntry = null;
	            cloneEntry = newEntryByType(cloneEntry , billType);
	            String bills = editmap.get("billid")==null?"":editmap.get("billid");
	 			CoreBaseInfo  entry= (CoreBaseInfo) cloneEntry.clone();
				if(billcollection.size() > 0 ){
					entry = billcollection.get(0);
				}else{
					entry.setId(BOSUuid.read(bills));
				}
				String arrStr = editmap.get(title+lang+"."+hang2)==null?"":editmap.get(title+lang+"."+hang2);
				System.out.print("------------------------4444==="+arrStr);
				entry =  BillInfoUtil.putValue(1,title,fieldType, classname,entry, arrStr);
				System.out.print("------------------------entry==="+entry);
				billcollection.add(entry);
	    	}
	    	if(billcollection.size() > 0 ){
	    		ICoreBase iBillCoreBase = newFactoryByType(ctx,billType);
	        	String idd = iBillCoreBase.save(billcollection).toString();
	        	System.out.print("------------------------222222222==="+idd);
	    	}
	    	  
	    	 
	    	UIToolTabTableEntryCollection  entryCollection  = UIToolTabTableEntryFactory.getLocalInstance(ctx).
	    	 	getUIToolTabTableEntryCollection(" where  parent = '"+uiInfo.getId().toString()+"' and editNode is not null ");
	    	 
	    	CoreBaseCollection collection = new CoreBaseCollection();
	    	 
	    	for(int i=0 ;i< entryCollection.size() ; i++){
	    		final String title = entryCollection.get(i).getEntityAttributes();
	    		final int seq = entryCollection.get(i).getSeq();
	    		final String fieldType = entryCollection.get(i).getFieldType();
	            final String classname = entryCollection.get(i).getDbName();
	            CoreBaseInfo  cloneEntry = null;
	            cloneEntry = newEntryByType(cloneEntry , billType);
	             
	            String entryIds = editmap.get("entryid")==null?"":editmap.get("entryid");
	 			String[] entryidArr = entryIds.split("\\|");
	 			
	 			/*String idss= "";
	 			for(int p = 0 ; p < entryidArr.length ; p++){
	 				if(p == 0){
	 					idss = "'"+ entryidArr[i]+"'";
	 				}else{
	 					idss =   idss+",'"+ idss+"'";
	 				} 
	 			} */
	 			
	 			/*if("CC58A617".equals(billType)){//离职单
	 				collection =  getEntryCollect(ctx , entryIds , billType);  
	 			}*/
	 			
	 			 
	 			Map<String, CoreBaseInfo>  collMap = new HashMap<String, CoreBaseInfo>();
	 			for(int q = 0 ; q < collection.size() ; q++){
	 				collMap.put(collection.get(q).getId().toString(), collection.get(q));
	 			}
	 			for(int j = 0 ; j < entryidArr.length ; j ++){
	 				String arrStr = editmap.get(title+j+"."+seq)==null?"":editmap.get(title+j+"."+seq);
	 				String[] arr = arrStr.split("\\|");
	    			
	 				String value = arr[j];
	 				if(!"".equals(value)){  
	 					CoreBaseInfo  entry= (CoreBaseInfo) cloneEntry.clone();
		    			if(collection.size() > 0 ){
		    				entry = collection.get(j);
		    			}else{
		    				entry.setId(BOSUuid.read(entryidArr[j]));
		    			}
	 					
	 					
	 					/*if(collMap.get(entryidArr[j]) != null){
		    				entry = collMap.get(entryidArr[j]);
		    			}else{
		    				entry.setId(BOSUuid.read(entryidArr[j]));
		    			}*/
	 					
		    			System.out.println("----------classname:"+classname);
		    			if(arr[j]!= null && !arr[j].equals("") && !arr[j].equals("undefined")){
		    				entry =  BillInfoUtil.putValue(1,title,fieldType, classname,entry, arr[j]);
		    				System.out.print("------------------------4440000004==="+entry);
			    			collection.add(entry);
			    			//collMap.put(entryidArr[j], entry);
		    			}  
	 				} 
	 			 }  
	    	 }
	    	 if(collection.size() > 0 ){ 
	    		 ICoreBase iCoreBase;
				 iCoreBase = newFactoryByType(ctx,billType);
	        	 iCoreBase.save(collection); 
	    	 }
	    	 
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	/*if(null != billType && !"".equals(billType)){
    		if("B65CCEF1".equals(billType)){//转正
    			String zhixis = editmap.get("entrys.zslddf")==null?"":editmap.get("entrys.zslddf");
    			String[] zhixisArr = zhixis.split("\\|");
    			
    			String xuxis = editmap.get("entrys.xxlddf")==null?"":editmap.get("entrys.xxlddf");
    			String[] xuxisArr = xuxis.split("\\|");
    			
    			String summarys = editmap.get("entrys.summary")==null?"":editmap.get("entrys.summary");
    			String[] summarysArr = summarys.split("\\|");
    			
    			String entryIds = editmap.get("entryid")==null?"":editmap.get("entryid");
    			String[] entryidArr = entryIds.split("\\|");
    			for(int i = 0 ; i < entryidArr.length ; i ++){
    				String entryId = entryidArr[i];
    				//EmpHireBizBillEntryInfo entry = EmpHireBizBillEntryFactory.getLocalInstance(ctx).getEmpHireBizBillEntryInfo( new ObjectUuidPK(BOSUuid.read(entryId)));
    				
    				//EmpHireBizBillEntryInfo entry = new EmpHireBizBillEntryInfo();
    				
    				CoreBaseInfo entry = new EmpHireBizBillEntryInfo();
    				entry.setId(BOSUuid.read(entryId));
    				if(zhixisArr.length > i && !"".equals(zhixisArr[i]) ){
    					entry.put("zslddf", new BigDecimal(zhixisArr[i]));
    				}
					if(xuxisArr.length > i && !"".equals(xuxisArr[i]) ){
						entry.put("xxlddf", new BigDecimal(xuxisArr[i]) );				
					}
					if(summarysArr.length > i && !"".equals(summarysArr[i]) ){
						entry.put("summary",summarysArr[i]);
					}
					
					EmpHireBizBillEntryFactory.getLocalInstance(ctx).save(entry);
    			}
    		}
    	}
    	*/
    	return true;
    }

	private static CoreBaseCollection getEntryCollect(Context ctx,String entryId,
			String billType) throws BOSException {
		// TODO Auto-generated method stub
		if("B65CCEF1".equals(billType)){//转正 
			return  EmpHireBizBillEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}
		if("CC58A617".equals(billType)){//离职单
			return  ResignBizBillEntryFactory.getLocalInstance(ctx).getCollection("where id = '"+entryId+"'");
		}
		
		if("C0DAD00D".equals(billType)){//公司内调动流程
			return  FluctuationBizBillEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}
		/*if("8859948B".equals(billType)){//人员需求流程
			return  PersonapprovalnEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}*/
		if("2A2632A9".equals(billType)){//合同续签
			return  ContractRenewBizBillEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}
		if("3C50B4E6".equals(billType)){//调薪流程
			return  BatchAdjustBillEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}
		/*if("27E62FB8".equals(billType)){//灵活用工流程	
			return  LhyglyEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		}
		if("D74B9931".equals(billType)){//录用流程	
			return  LuyongbaopinewEntryFactory.getLocalInstance(ctx).getCollection("where id  = '"+entryId+"' ");
		} */
		
		return null;
		
		
		
	}

	private static CoreBaseInfo newEntryByType(CoreBaseInfo entry ,String billType) {
		// TODO Auto-generated method stub
		if("B65CCEF1".equals(billType)){//转正
			entry = new EmpHireBizBillEntryInfo();
		}
		if("CC58A617".equals(billType)){//离职单
			entry = new ResignBizBillEntryInfo();
		}
		
		if("C0DAD00D".equals(billType)){//公司内调动流程
			entry = new FluctuationBizBillEntryInfo();
		}
		/*if("8859948B".equals(billType)){//人员需求流程
			entry = new PersonapprovalnEntryInfo();
		}*/
		if("2A2632A9".equals(billType)){//合同续签
			entry = new ContractRenewBizBillEntryInfo();
		}
		if("3C50B4E6".equals(billType)){//调薪流程
			entry = new BatchAdjustBillInfo();
		}
		/*if("27E62FB8".equals(billType)){//灵活用工流程	
			entry = new LhyglyEntryInfo();
		}
		if("D74B9931".equals(billType)){//录用流程	
			entry = new LuyongbaopinewEntryInfo();
		} */
		
		return entry;
	}
	
	private static ICoreBase newFactoryByType(Context ctx,String billType) throws BOSException {
		// TODO Auto-generated method stub
		if("B65CCEF1".equals(billType)){//转正 
			return  EmpHireBizBillEntryFactory.getLocalInstance(ctx);
		}
		if("CC58A617".equals(billType)){//离职单
			return  ResignBizBillEntryFactory.getLocalInstance(ctx);
		}
		
		if("C0DAD00D".equals(billType)){//公司内调动流程
			return  FluctuationBizBillEntryFactory.getLocalInstance(ctx);
		}
		/*if("8859948B".equals(billType)){//人员需求流程
			return  PersonapprovalnEntryFactory.getLocalInstance(ctx);
		}*/
		if("2A2632A9".equals(billType)){//合同续签
			return  ContractRenewBizBillEntryFactory.getLocalInstance(ctx);
		}
		if("3C50B4E6".equals(billType)){//调薪流程
			return  BatchAdjustBillFactory.getLocalInstance(ctx);
		}
		/*if("27E62FB8".equals(billType)){//灵活用工流程	
			return  LhyglyEntryFactory.getLocalInstance(ctx);
		}
		if("D74B9931".equals(billType)){//录用流程	
			return  LuyongbaopinewEntryFactory.getLocalInstance(ctx);
		} */
		
		return null;
	}
}
