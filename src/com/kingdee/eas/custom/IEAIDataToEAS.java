package com.kingdee.eas.custom;

import java.text.SimpleDateFormat;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;

public interface IEAIDataToEAS {
	public String CUID="00000000-0000-0000-0000-000000000000CCE7AED4";
	public SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * ���»�ȡ���ݡ�һ�����û�������ťִ�С�
	 * �û�ѡ��ͬ���ļ�¼���������´��м���ȡ��
	 * ����м������û�б仯���Ͳ���Ҫ���¡�
	 * @param list  ���м������������ݼ���
	 * @throws BOSException 
	 */
	public void reload(Context ctx,LazyDBList list) ;
	/**
	 * һ����ϵͳ��ʱ���á����м�������ͬ����EASϵͳ�С�
	 * ����Ӧ�ñ�д����ת�����롣
	 * @param list ���м������������ݼ���
	 */
	public void syn(Context ctx,LazyDBList list);
	public void setDataBase(EAIDataBaseInfo database);
}
