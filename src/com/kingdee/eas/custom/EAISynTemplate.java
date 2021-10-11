package com.kingdee.eas.custom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
public class EAISynTemplate {

	public static boolean existsoa(Context ctx, String database,
			String tableName, String fid) {
		boolean isExists = false;
		String sql = "select ID from " + tableName + " where id = '" + fid
				+ "'";
		try {
			Map<String, Object> ret = queryRowOne(ctx, database, sql);
			if (ret != null && ret.size() > 0) {
				if (ret.get("ID") != null
						&& !"".equals(ret.get("ID").toString())) {
					isExists = true;
				}
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExists;
	}

	public static boolean exists(Context ctx, String database,
			String tableName, String fid) {
		boolean isExists = false;
		String sql = "select FID from " + tableName + " where fid = '" + fid
				+ "'";
		try {
			Map<String, Object> ret = queryRowOne(ctx, database, sql);
			if (ret != null && ret.size() > 0) {
				if (ret.get("FID") != null
						&& !"".equals(ret.get("FID").toString())) {
					isExists = true;
				}
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExists;
	}

	public static boolean existsByWhere(Context ctx, String database,
			String tableName, String where) {
		boolean isExists = false;
		String sql = "select FID from " + tableName + " where 1=1 " + where;
		try {
			Map<String, Object> ret = queryRowOne(ctx, database, sql);
			if (ret != null && ret.size() > 0) {
				if (ret.get("FID") != null
						&& !"".equals(ret.get("FID").toString())) {
					isExists = true;
				}
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExists;
	}

	public static List<Map<String, Object>> query(Context ctx, String database,
			String sql) throws BOSException {
		EAIDataBaseInfo dc = getDataBaseByNumber(ctx, database);
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection(dc);
			ps = conn.createStatement();
			System.out.println("执行sql语句：" + sql);
			rs = ps.executeQuery(sql);
			ResultSetMetaData met = rs.getMetaData();
			int columnCount = met.getColumnCount();
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				list.add(map);
				for (int i = 1; i <= columnCount; i++) {
					map.put(met.getColumnName(i).toUpperCase(),
							rs.getObject(i) == null ? "" : rs.getObject(i));
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BOSException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BOSException(e);
				}
			}
		}
	}

	public static Map<String, Object> queryRowOne(Context ctx, String database,
			String sql) throws BOSException {
		List<Map<String, Object>> list = query(ctx, database, sql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public static void execute(Context ctx, String database, String sql)
			throws BOSException {
		EAIDataBaseInfo dc = getDataBaseByNumber(ctx, database);
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection(dc);
			st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BOSException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BOSException(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BOSException(e);
				}
			}
		}
	}

	/**
	 * 批量查询SQL语句
	 * @param ctx
	 * @param database
	 * @param sqls
	 * @throws BOSException
	 */
	public static void executeBatch(Context ctx, String database,
			List<String> sqls) throws BOSException {
		EAIDataBaseInfo dc = getDataBaseByNumber(ctx, database);
		Connection conn = null;
		Statement st = null;
		if (sqls != null && sqls.size() > 0) {
			try {
				conn = getConnection(dc);
				conn.setAutoCommit(false);
				st = conn.createStatement();
				for(String sql:sqls){
					if(sql != null && !sql.equals("")){
						st.addBatch(sql); 
					}
				}
				st.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw new BOSException(e);
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new BOSException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new BOSException(e);
					}
				}
			}
		}

	}

	public static void syn(Context ctx, String database, String sql,
			IEAIDataToEAS idte) throws BOSException {
		EAIDataBaseInfo dc = getDataBaseByNumber(ctx, database);
		Connection conn = null;
		try {
			conn = getConnection(dc);
			// 从中间库查询今天的组织数据
			LazyDBList datalist = new LazyDBList(conn, sql);
			idte.setDataBase(dc);
			idte.syn(ctx, datalist);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BOSException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BOSException(e);
				}
			}
		}
	}

	private static EAIDataBaseInfo getDataBaseByNumber(Context ctx,
			String number) throws BOSException {
		EAIDataBaseCollection dc = EAIDataBaseFactory
				.getLocalInstance(ctx)
				.getEAIDataBaseCollection(
						"select dataBaseType.*,* where number='" + number + "'");
		System.out.println("-------------" + dc.get(0).getName()
				+ dc.get(0).getDataSourceUser()
				+ dc.get(0).getDataSourcePassword());
		if (dc == null || dc.size() == 0) {
			throw new BOSException("找不到编码为" + number + "的中间库定义");
		}
		return dc.get(0);
	}

	public static Connection getConnection(Context ctx, String dataBase)
			throws BOSException, SQLException {
		EAIDataBaseInfo dc = getDataBaseByNumber(ctx, dataBase);
		Connection conn = getConnection(dc);
		return conn;
	}

	private static Connection getConnection(EAIDataBaseInfo db)
			throws SQLException {
		EAIDataBaseTypeInfo type = db.getDataBaseType();
		String dirver = type.getDriver();
		try {
			Class.forName(dirver).newInstance();
			//DriverManager.registerDriver(dr);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库驱动加载错误ClassNotFoundException" + e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库驱动加载错误" + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库驱动加载错误" + e.getMessage());
		}
		return DriverManager.getConnection(db.getAddress(), db
				.getDataSourceUser(), db.getDataSourcePassword());
	}

}