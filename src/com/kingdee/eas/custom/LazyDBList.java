package com.kingdee.eas.custom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LazyDBList implements Iterator<Map<String,Object>> {
	private Iterator<Map<String,Object>> list;
	private long pageSize=100;
	private int totalCount;//所有数量
	private int currentCount;//当前从数据库中读出的数量
	private String sql;
	private Connection conn;
	
	public LazyDBList(Connection conn,String sql){
		this.sql=sql;
		this.conn=conn;
		try {
			this.totalCount=this.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	public LazyDBList(Connection conn,String sql,boolean isLazy){
		this(conn,sql,999999999999999999l);
		if(this.list==null){
			ArrayList<Map<String, Object>> l=null;
			try {
				l = getNextPageData();
				list=l.iterator();
				this.currentCount=this.currentCount+l.size();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}finally{
				close();
			}
			
		}
		
	}
	public void close(){
		try {
			if(this.conn!=null){
				this.conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	public LazyDBList(Connection conn,String sql,long pageSize){
		this.sql=sql;
		this.conn=conn;
		this.pageSize=pageSize;
		try {
			this.totalCount=this.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	public boolean hasNext() {
		if(list==null && totalCount>0){
			return true;
		}
		if(list==null && totalCount==0){
			return false;
		}
		if(list.hasNext()){
			return true;
		}else{
			return currentCount<totalCount;
		}
	}

	public Map<String,Object> next() {
		if(this.hasNext()){
			if(this.list==null){
				ArrayList<Map<String, Object>> l=null;
				try {
					l = getNextPageData();
					list=l.iterator();
					this.currentCount=this.currentCount+l.size();
					return list.next();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
				
			}else{
				if(!list.hasNext()){
					try {
						ArrayList<Map<String, Object>> l= getNextPageData();
						list=l.iterator();
						this.currentCount=this.currentCount+l.size();
						return list.next();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e.getMessage());
					}
				}else{
					return list.next();
				}
				
			}
			
		}else{
			throw new RuntimeException("没有更多的记录");
		}
	}

	public void remove() {
		throw new RuntimeException("本集合不支持删除");
	}
	private ArrayList<Map<String,Object>> getNextPageData() throws SQLException{
		Statement ps=null;
		ResultSet rs=null;
		try{
			
			ps= conn.createStatement();
			ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			String runSql=this.getNextPageSql(sql);
			rs=ps.executeQuery(runSql);
			ResultSetMetaData met= rs.getMetaData();
			int columnCount=met.getColumnCount();
			while(rs.next()){
				Map<String,Object> map=new HashMap<String,Object>();
				list.add(map);
				for(int i=1;i<=columnCount;i++){
					String columnName=met.getColumnName(i).toUpperCase();
					map.put(columnName,rs.getObject(i)==null?"":rs.getObject(i));
				}
			}
			
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
				
		}
	}
	private int getTotalCount() throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			ps= conn.prepareStatement(this.getCountSql(sql));
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt("countNum");
			}else{
				return 0;
			}
		}catch(SQLException e){
			throw e;
		}finally{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
				
		}
	}
	private String getCountSql(String sql){
		return "select count(*) countNum from ("+sql+")";
	}
	private String getNextPageSql(String sql){
		String thisSql=
		"select * from (SELECT tt.*, ROWNUM rowno		"+
		"          FROM ( "+sql+") tt						"+
		"         WHERE ROWNUM <= "+(this.currentCount+this.pageSize)+
		"		) mm where mm.rowno>="+(this.currentCount+1);
		System.out.println(thisSql);
		return thisSql;
	}
}
