package com.kingdee.eas.custom.unit;



import java.net.URLEncoder;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.IUser;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.FullOrgUnitFactory;
import com.kingdee.eas.basedata.org.FullOrgUnitInfo;
import com.kingdee.eas.basedata.org.HROrgUnitFactory;
import com.kingdee.eas.basedata.org.HROrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.ProfitCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.ProfitCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitFactory;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.QualityOrgUnitFactory;
import com.kingdee.eas.basedata.org.QualityOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitFactory;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitFactory;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.basedata.org.TransportOrgUnitFactory;
import com.kingdee.eas.basedata.org.TransportOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.cp.eip.sso.ltpa.LtpaToken;
import com.kingdee.eas.cp.eip.sso.ltpa.LtpaTokenManager;

public class CoreUtil {
	protected static final int DB_TYPE = 0;

	public static synchronized Context context(Context ctx, String number)
			throws EASBizException, BOSException {
		IUser iUser = UserFactory.getLocalInstance(ctx); 
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("number", number));
		boolean flag = iUser.exists(filter);
		UserInfo user = null;
		IObjectPK userPk = null;
		if (flag) {
			user = iUser.getUserInfo("where number = '" + number + "'");
			userPk = new ObjectUuidPK(user.getId());
		} else {
			userPk = ctx.getCaller();
		}

		SelectorItemCollection selectors = new SelectorItemCollection();
		selectors.add(new SelectorItemInfo("*"));
		selectors.add(new SelectorItemInfo("defOrgUnit.*"));
		user = UserFactory.getLocalInstance(ctx).getUserInfo(userPk, selectors);
		ctx.setCaller(userPk);
		ctx.setUserName(user.getName());

		FullOrgUnitInfo orgUnit = user.getDefOrgUnit();
		String orgUnitId = orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId);
		orgUnit = FullOrgUnitFactory.getLocalInstance(ctx).getFullOrgUnitInfo(
				orgUnitPk);

		ctx.put("UserInfo", user);
		ctx.put("SwitchToNewLoginFlow", "true");
		ctx.put("ClientName", "127.0.0.1");
		ctx.put("Password", user.getPassword());
		ctx.put("ClientIP", "127.0.0.1");
		ctx.put("dbTypeCode", Integer.valueOf(0));
		ctx.put("CurCompanyId", null);
		ctx.put("CurOU", orgUnit);
		ctx.put("CompanyInfo", null);
		ctx.put(OrgType.NONE, null);
		ctx.put("hint", "no");
		ctx.put("USBKEY_LOGIN", null);
		ctx = handleCurrentOrg(ctx, orgUnit);

		return ctx;
	}

	public static synchronized Context contextNoCtx( String number)
	throws EASBizException, BOSException {
		Context ctx = new Context();
		//IUser iUser = UserFactory.getRemoteInstance();  
		//
		IObjectPK userPk = null;
		 
		UserInfo user = new UserInfo();
		user.setId(BOSUuid.read("256c221a-0106-1000-e000-10d7c0a813f413B7DE7F"));
		userPk = new ObjectUuidPK(user.getId());
		/*
		user.setId*/ 
		ctx.setCaller(userPk);
		ctx.setUserName("user");
		
		FullOrgUnitInfo orgUnit = new FullOrgUnitInfo() ;
		orgUnit.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
		
		String orgUnitId = orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId); 
		
		orgUnit.setNumber("S");
		orgUnit.setName("S");
		ctx.put("UserInfo", user);
		ctx.put("SwitchToNewLoginFlow", "true");
		ctx.put("ClientName", "127.0.0.1"); 
		//LtpaToken token = LtpaTokenManager.generate("user",LtpaTokenManager.getDefaultLtpaConfig());
		String password = "FLELo3laAMpnuKN4f2ePJg==";
		//String password = "eDdvFrQHJoI3DC27n4ordA==";
		
		ctx.put("Password", password);
		ctx.put("ClientIP", "127.0.0.1");
		ctx.put("dbTypeCode", Integer.valueOf(0));
		ctx.put("CurCompanyId", null);
		ctx.put("CurOU", orgUnit);
		ctx.put("CompanyInfo", null);
		ctx.put(OrgType.NONE, null);
		ctx.put("hint", "no");
		ctx.put("USBKEY_LOGIN", null);
		ctx = handleCurrentOrgNoContext(ctx, orgUnit);
		
		return ctx;
	}
		
	public static synchronized Context copyContext(Context ctx) {
		Context newCtx = new Context();
		newCtx.setAIS(ctx.getAIS());
		newCtx.setCaller(ctx.getCaller());
		newCtx.setClientHostIP(ctx.getClientHostIP());
		newCtx.setClientHostName(ctx.getClientHostName());
		newCtx.setLocale(ctx.getLocale());
		newCtx.setReadAIS(ctx.getReadAIS());
		newCtx.setSolution(ctx.getSolution());
		newCtx.setUserName(ctx.getUserName());
		newCtx.put(OrgType.Admin, ctx.get(OrgType.Admin));
		CompanyOrgUnitInfo company = (CompanyOrgUnitInfo) ctx
				.get(OrgType.Company);
		newCtx.put(OrgType.Company, company);
		newCtx.put("CompanyInfo", company);
		newCtx.put("CurCompanyId", ctx.get(OrgType.Transport));
		newCtx.put(OrgType.CostCenter, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.HRO, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.ProfitCenter, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Purchase, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Quality, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Sale, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Storage, ctx.get(OrgType.Transport));
		newCtx.put(OrgType.Transport, ctx.get(OrgType.Transport));
		UserInfo user = null;
		try {
			user = UserFactory.getLocalInstance(ctx).getUserInfo(
					ctx.getCaller());
			newCtx.put("UserInfo", user);
			newCtx.put("Password", user.getPassword());
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		}
		newCtx.put("SwitchToNewLoginFlow", "true");
		newCtx.put("ClientName", ctx.get("ClientName"));
		newCtx.put("ClientIP", ctx.get("ClientIP"));
		newCtx.put("dbTypeCode", Integer.valueOf(0));
		newCtx.put("CurCompanyId", ctx.get("CurCompanyId"));
		newCtx.put("CurOU", ctx.get("CurOU"));
		newCtx.put(OrgType.NONE, null);
		newCtx.put("hint", "no");
		newCtx.put("USBKEY_LOGIN", ctx.get("USBKEY_LOGIN"));
		return newCtx;
	}

	protected static synchronized Context handleCurrentOrgNoContext(Context ctx,
			FullOrgUnitInfo orgUnit) throws EASBizException, BOSException {
		String orgUnitId = orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId);

		AdminOrgUnitInfo adminOrg = new AdminOrgUnitInfo();
		adminOrg.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
		ctx.put(OrgType.Admin, adminOrg);
		
		ctx.put(OrgType.Company, null);
		
		ctx.put(OrgType.CostCenter, null);
		
		orgUnit.setIsCU(true);

		HROrgUnitInfo hrOrg =  new HROrgUnitInfo();
		hrOrg.setId(BOSUuid.read("00000000-0000-0000-0000-000000000000CCE7AED4"));
		ctx.put(OrgType.HRO, hrOrg);
		
		ctx.put(OrgType.ProfitCenter, null); 

		ctx.put(OrgType.Purchase, null);

		ctx.put(OrgType.Quality, null);
		

		ctx.put(OrgType.Sale, null);
		
		
		ctx.put(OrgType.Storage, null);
		
		ctx.put(OrgType.Transport, null);

		return ctx;
	}
	
	
	protected static synchronized Context handleCurrentOrg(Context ctx,
			FullOrgUnitInfo orgUnit) throws EASBizException, BOSException {
		String orgUnitId = orgUnit.getId().toString();
		IObjectPK orgUnitPk = new ObjectStringPK(orgUnitId);
		if (orgUnit.isIsAdminOrgUnit()) {
			AdminOrgUnitInfo adminOrg = AdminOrgUnitFactory.getLocalInstance(
					ctx).getAdminOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Admin, adminOrg);
		} else {
			ctx.put(OrgType.Admin, null);
		}
		if (orgUnit.isIsCompanyOrgUnit()) {
			CompanyOrgUnitInfo company = CompanyOrgUnitFactory
					.getLocalInstance(ctx).getCompanyOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Company, company);
			ctx.put("CompanyInfo", company);
			ctx.put("CurCompanyId", orgUnitId);
		} else {
			ctx.put(OrgType.Company, null);
		}
		if (orgUnit.isIsCostOrgUnit()) {
			CostCenterOrgUnitInfo costCenter = CostCenterOrgUnitFactory
					.getLocalInstance(ctx).getCostCenterOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.CostCenter, costCenter);
		} else {
			ctx.put(OrgType.CostCenter, null);
		}
		orgUnit.isIsCU();

		if (orgUnit.isIsHROrgUnit()) {
			HROrgUnitInfo hrOrg = HROrgUnitFactory.getLocalInstance(ctx)
					.getHROrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.HRO, hrOrg);
		} else {
			ctx.put(OrgType.HRO, null);
		}
		if (orgUnit.isIsProfitOrgUnit()) {
			ProfitCenterOrgUnitInfo profitCenter = ProfitCenterOrgUnitFactory
					.getLocalInstance(ctx)
					.getProfitCenterOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.ProfitCenter, profitCenter);
		} else {
			ctx.put(OrgType.ProfitCenter, null);
		}
		if (orgUnit.isIsPurchaseOrgUnit()) {
			PurchaseOrgUnitInfo purchaseOrg = PurchaseOrgUnitFactory
					.getLocalInstance(ctx).getPurchaseOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Purchase, purchaseOrg);
		} else {
			ctx.put(OrgType.Purchase, null);
		}
		if (orgUnit.isIsQualityOrgUnit()) {
			QualityOrgUnitInfo qualityOrg = QualityOrgUnitFactory
					.getLocalInstance(ctx).getQualityOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Quality, qualityOrg);
		} else {
			ctx.put(OrgType.Quality, null);
		}
		if (orgUnit.isIsSaleOrgUnit()) {
			SaleOrgUnitInfo saleOrg = SaleOrgUnitFactory.getLocalInstance(ctx)
					.getSaleOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Sale, saleOrg);
		} else {
			ctx.put(OrgType.Sale, null);
		}
		if (orgUnit.isIsStorageOrgUnit()) {
			StorageOrgUnitInfo storageOrg = StorageOrgUnitFactory
					.getLocalInstance(ctx).getStorageOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Storage, storageOrg);
		} else {
			ctx.put(OrgType.Storage, null);
		}
		if (orgUnit.isIsTransportOrgUnit()) {
			TransportOrgUnitInfo transportOrg = TransportOrgUnitFactory
					.getLocalInstance(ctx).getTransportOrgUnitInfo(orgUnitPk);
			ctx.put(OrgType.Transport, transportOrg);
		} else {
			ctx.put(OrgType.Transport, null);
		}

		return ctx;
	}
}
