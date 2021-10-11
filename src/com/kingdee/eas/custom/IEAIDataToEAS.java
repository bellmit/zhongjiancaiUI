package com.kingdee.eas.custom;

import java.text.SimpleDateFormat;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;

public interface IEAIDataToEAS {
	public String CUID="00000000-0000-0000-0000-000000000000CCE7AED4";
	public SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 重新获取数据。一般由用户触发按钮执行。
	 * 用户选中同步的记录，可以重新从中间库获取。
	 * 如果中间库数据没有变化，就不需要更新。
	 * @param list  从中间库读出来的数据集合
	 * @throws BOSException 
	 */
	public void reload(Context ctx,LazyDBList list) ;
	/**
	 * 一般有系统定时调用。将中间库的数据同步到EAS系统中。
	 * 这里应该编写数据转换代码。
	 * @param list 从中间库读出来的数据集合
	 */
	public void syn(Context ctx,LazyDBList list);
	public void setDataBase(EAIDataBaseInfo database);
}
