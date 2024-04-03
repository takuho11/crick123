package cn.topcheer.pms2.biz.utils;

import dm.jdbc.driver.DmdbBlob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.*;

public abstract class DBHelperAffix {




	private static Logger logger = LoggerFactory.getLogger(DBHelperAffix.class);
	public  abstract Connection getConnection() ;



	public boolean autoCloseConneciton=true;


	private static  ThreadLocal<Connection> connectionThreadLocal=new ThreadLocal<Connection>();


	public   String getCountSql(String querySql) {
		String countSql = "select count(0) from (";
		countSql = countSql + " " + querySql+" )";
		System.out.println("countSql=========>"+countSql);
		return countSql;
	}



	/**
	 * 根据查询语句返回Map列表
	 * 注意：Map中列名全改成小写（不管你sql语句里是大写还是小写）,类型全转成String了,如果要自定义
	 * <br>请调用有更多参数的getRows方法
	 * @param sql sql语句
	 * @param params 参数值
	 * @return

	 */

	public   List<Map> getRows(String sql, Object[] params) {
		return getRows(sql,params,true,true);
	}

	/**
	 * 根据查询语句返回Map列表
	 * 注意：Map中列名全改成小写（不管你sql语句里是大写还是小写）,如果要自定义
	 * <br>请调用有更多参数的getRows方法
	 * @param sql sql语句
	 * @param params 参数值
	 * @param needToString 是否需要将值全转成String类型
	 * @return

	 */

	public   List<Map> getRows(String sql, Object[] params,boolean needToString) {
		return getRows(sql,params,needToString,true);
	}

	/**
	 * 根据查询语句返回Map列表
	 * @param sql sql语句
	 * @param params 参数值
	 * @param needToString 是否需要将值全转成String类型
	 * @param needToLowerCase 是否将列表全改成小写
	 * @return

	 */

	public   List<Map> getRows(String sql, Object[] params,boolean needToString,boolean needToLowerCase) {
		List<Map> list = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Connection currentConn = connectionThreadLocal.get();

		if(currentConn ==null){
			currentConn = getConnection();
			connectionThreadLocal.set(currentConn);
		}
		try {
			st = currentConn.prepareStatement(sql);
			setParams(params, st);

			rs = st.executeQuery();
			if (rs != null) {
				ResultSetMetaData meta = rs.getMetaData();
				int colCount = rs.getMetaData().getColumnCount();
				list = new ArrayList<Map>();
				HashMap<String,Object> pro = null;
				while (rs.next()) {
					pro = new HashMap<String,Object>();
					for (int k = 1; k < colCount + 1; k++) {

						String colName = meta.getColumnLabel(k);//getColumnName.(k);
						if(needToString) {
							Object objectValue = rs.getObject(colName);
							if(!Util.isEoN(objectValue)){
								if(meta.getColumnType(k)== Types.CLOB)
									pro.put(changeCase(colName,needToLowerCase),rs.getString(colName));
								else
									pro.put(changeCase(colName,needToLowerCase),objectValue.toString());
							}else{
								pro.put(changeCase(colName,needToLowerCase),"");
							}
						}else{
							pro.put(changeCase(colName,needToLowerCase),
									rs.getObject(colName));
						}
					}
					list.add(pro);
				}
			}

		} catch (SQLException e) {
			logger.error("ERROR,sql="+sql,e);
		} finally {
			closeStatement( st, rs);
		}
		return list;
	}
	/**
	 * 根据查询语句返回Map列表
	 * 待分页
	 * @param sql sql语句
	 * @param params 参数值
	 * @param startPage 是否需要将值全转成String类型
	 * @param pageSize 是否将列表全改成小写
	 * @return

	 */
	public List<Map> getRows(String sql, Object[] params,int startPage,int pageSize){
		return getRows(sql,params,true,true,startPage,pageSize);
	}
	public   List<Map> getRows(String sql, Object[] params,boolean needToString,boolean needToLowerCase,int startPage,int pageSize) {
		List<Map> list = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Connection currentConn = connectionThreadLocal.get();

		if(currentConn ==null){
			currentConn = getConnection();
			connectionThreadLocal.set(currentConn);
		}
		try {
			st = currentConn.prepareStatement(sql+" limit ?,?");
			setParams(params, st);
			st.setInt(1, (startPage-1)*pageSize);
			st.setInt(2, startPage*pageSize);
			rs = st.executeQuery();
			if (rs != null) {
				ResultSetMetaData meta = rs.getMetaData();
				int colCount = rs.getMetaData().getColumnCount();
				list = new ArrayList<Map>();
				HashMap<String,Object> pro = null;
				while (rs.next()) {
					pro = new HashMap<String,Object>();
					for (int k = 1; k < colCount + 1; k++) {
						String colName = meta.getColumnLabel(k);//getColumnName.(k);
						if(needToString) {
							pro.put(changeCase(colName,needToLowerCase),
									parseStringN(rs.getString(colName)));
						}else{
							pro.put(changeCase(colName,needToLowerCase),
									rs.getObject(colName));
						}
					}
					list.add(pro);
				}
			}

		} catch (SQLException e) {
			logger.error("ERROR,sql="+sql,e);
		} finally {
			closeStatement( st, rs);
		}
		return list;
	}
	private String changeCase(String colName,boolean needToLowerCase){
		if(needToLowerCase) return colName.toLowerCase();
		return colName;
	}


	private  String parseStringN(Object data)
	{
		if(data!=null)
		{
			if(data instanceof String){
				return (String)data;
			}
			return data.toString();
		}
		else
		{
			return "";
		}
	}


	private void setParams(Object[] params, PreparedStatement st)
			throws SQLException {
		if (params != null) {
			int len=params.length;
			for (int pi = 0; pi < len; pi++) {
				if(params[pi] instanceof Date){
					st.setTimestamp(pi+1,new Timestamp(((Date)params[pi]).getTime()));
				}else{
					st.setObject(pi+1, params[pi]);
				}
			}
		}
	}

	public   String[] getARowStringArray(String sql, Object[] params) {
		String[] s = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			Connection currentConn = connectionThreadLocal.get();
			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}

			st = currentConn.prepareStatement(sql);
			setParams(params, st);
			rs = st.executeQuery();
			if (rs != null) {
				int colCount = rs.getMetaData().getColumnCount();
				if (rs.next()) {
					s = new String[colCount];
					for (int k = 0; k < colCount; k++) {
						s[k] = rs.getString(k + 1);
					}
				}
			}

		} catch (SQLException e) {
			logger.error("ERROR,sql="+sql,e);
		} finally {
			closeStatement( st, rs);
		}
		return s;
	}

	public   List<String[]> getArrayRows(String sql, Object[] params) throws SQLException {
		List<String[]> list = null;



		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			Connection currentConn = connectionThreadLocal.get();
			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}

			st = currentConn.prepareStatement(sql);
			setParams(params, st);
			rs = st.executeQuery();
			if (rs != null) {
				int colCount = rs.getMetaData().getColumnCount();
				String[] s = null;
				list = new ArrayList<String[]>();
				while (rs.next()) {
					s = new String[colCount];
					for (int k = 0; k < colCount; k++) {
						s[k] = rs.getString(k + 1);



					}
					list.add(s);
				}
			}

		} catch (SQLException e) {
			logger.error("ERROR,sql="+sql,e);
			throw e;
		} finally {
			closeStatement( st, rs);
		}
		return list;
	}

	/**
	 * 根据sql，取得一个字符串的数组列表
	 * @param sql
	 * @return
	 */
	public  String getOnlyStringValue(String sql, Object[] params){
		String[] s = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			Connection currentConn = connectionThreadLocal.get();
			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}

			st = currentConn.prepareStatement(sql);
			setParams(params, st);
			rs = st.executeQuery();
			if (rs != null) {
				int colCount = rs.getMetaData().getColumnCount();
				if (rs.next()) {
					s = new String[colCount];
					for (int k = 0; k < colCount; k++) {
						s[k] = rs.getString(k + 1);
					}
				}
			}

		} catch (SQLException e) {
			logger.error("ERROR,sql="+sql,e);
		} finally {
			closeStatement( st, rs);
		}
		if(s==null){
			return null;

		}

		return s[0];
	}
	public  boolean runSql(String sql) throws SQLException{
		return runSql(sql,null);
	}
	public   boolean runSql(String sql, Object[] params) throws SQLException {
		boolean ok=false;

		PreparedStatement st = null;

		try {

			Connection currentConn = connectionThreadLocal.get();
			if(currentConn ==null){
				currentConn = getConnection();

				connectionThreadLocal.set(currentConn);
			}

			st = currentConn.prepareStatement(sql);
			setParams(params, st);
			st.execute();
			ok=true;

		}

		catch (SQLException ex) {
			logger.error("ERROR,sql="+sql,ex);
			throw ex;
		}finally{
			closeStatement(st,null);

		}

		return ok;
	}
	/**
	 * 参数只接受问号传值
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean insertClobSql(String sql, Object[] params) throws SQLException {
		PreparedStatement pstate = null;
		try {
			if(params==null||params.length<1)
				return false;
			Connection currentConn = connectionThreadLocal.get();

			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}
			if(currentConn!=null){
				pstate = currentConn.prepareStatement(sql);
				for(int i=0;i<params.length;i++){
					if(params[i]!=null){
						StringReader reader = new StringReader(params[i].toString());
						pstate.setCharacterStream(i+1, reader, params[i].toString().length());
					}else{
						StringReader reader = new StringReader("");
						pstate.setCharacterStream(i+1, reader,"".length());
					}
				}
				pstate.executeUpdate();
				currentConn.commit();
			}else{
				Exception exception = new Exception("CurrentConnection is null!");

				logger.error("ERROR,currentConn is null"+sql,exception);
			}
		}catch (SQLException ex) {
			logger.error("ERROR,sql="+sql,ex);
			throw ex;
		}finally{
			closeStatement(pstate,null);
		}
		return true;
	}
	/**
	 * 插入Blob 字段
	 * 包括插入和更新两步
	 * @param
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public boolean insertBlobSql(String insertsql,String updateSql,Object[] insertparams,Object[] updateparams,InputStream in) throws Exception {
		PreparedStatement pstate = null;
		ResultSet rs = null;
		boolean hasParams = true;
		boolean hasupdateParams = true;
		try {
			if(insertparams==null||insertparams.length<1)
				hasParams = false;
			if(updateparams==null||updateparams.length<1)
				hasupdateParams = false;
			Connection currentConn = connectionThreadLocal.get();

			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}
			if(currentConn!=null){
				pstate = currentConn.prepareStatement(insertsql);
				if(hasParams){
					for(int i=0;i<insertparams.length;i++){
						if(insertparams[i]!=null){
							StringReader reader = new StringReader(insertparams[i].toString());
							pstate.setCharacterStream(i+1, reader, insertparams[i].toString().length());
						}else{
							StringReader reader = new StringReader("");
							pstate.setCharacterStream(i+1, reader,"".length());
						}
					}
				}
				pstate.executeUpdate();
				// 从这里更新blob 字段
				pstate = currentConn.prepareStatement(updateSql);
				// 更新语句的参数传值

				if(hasupdateParams){
					for(int i=0;i<updateparams.length;i++){
						if(updateparams[i]!=null){
							StringReader reader = new StringReader(updateparams[i].toString());
							pstate.setCharacterStream(i+1, reader, updateparams[i].toString().length());
						}else{
							StringReader reader = new StringReader("");
							pstate.setCharacterStream(i+1, reader,"".length());
						}
					}
				}
				rs = pstate.executeQuery();
				DmdbBlob blob = null;
				if(rs.next()){
					blob = (DmdbBlob) rs.getBlob(1);
				}
				OutputStream out = blob.setBinaryStream(1L);
				byte[] buffer = new byte[1024];
				int length = -1;
				while ((length = in.read(buffer)) != -1){
					out.write(buffer, 0, length);
				}
				in.close();
				out.close();
				currentConn.commit();
			}else{
				Exception exception = new Exception("CurrentConnection is null!");
				logger.error("ERROR,currentConn is null"+insertsql+";"+updateSql,exception);
			}
		}catch (Exception ex) {
			logger.error("ERROR,sql="+insertsql+";"+updateSql,ex);
			throw ex;
		}finally{
			closeStatement(pstate,rs);
		}
		return true;
	}
	/**
	 * 插入Blob 字段
	 * 包括插入和更新两步
	 * @param
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean insertBlobSql(String insertsql,String updateSql,Object[] params,InputStream in) throws Exception {
		PreparedStatement pstate = null;
		ResultSet rs = null;
		boolean hasParams = true;
		try {
			if(params==null||params.length<1)
				hasParams = false;
			Connection currentConn = connectionThreadLocal.get();

			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}
			if(currentConn!=null){
				pstate = currentConn.prepareStatement(insertsql);
				if(hasParams){
					for(int i=0;i<params.length;i++){
						if(params[i]!=null){
							StringReader reader = new StringReader(params[i].toString());
							pstate.setCharacterStream(i+1, reader, params[i].toString().length());
						}else{
							StringReader reader = new StringReader("");
							pstate.setCharacterStream(i+1, reader,"".length());
						}
					}
				}
				pstate.executeUpdate();
				// 从这里更新blob 字段
				pstate = currentConn.prepareStatement(updateSql);

				rs = pstate.executeQuery();
				DmdbBlob blob = null;
				if(rs.next()){
					blob = (DmdbBlob) rs.getBlob(1);
				}
				OutputStream out = blob.setBinaryStream(1L);
				byte[] buffer = new byte[1024];
				int length = -1;
	            while ((length = in.read(buffer)) != -1){
	                out.write(buffer, 0, length);
	            }
	            in.close();
	            out.close();
				currentConn.commit();
			}else{
				Exception exception = new Exception("CurrentConnection is null!");
				logger.error("ERROR,currentConn is null"+insertsql+";"+updateSql,exception);
			}
		}catch (Exception ex) {
			logger.error("ERROR,sql="+insertsql+";"+updateSql,ex);
			throw ex;
		}finally{
			closeStatement(pstate,rs);
		}
		return true;
	}
	public void   closeStatement(Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (st != null) {
				st.close();
				st = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isAutoCloseConneciton()){



			closeConn();
		}

	}

	public  void closeConn(){

		try {

			Connection currentConn = connectionThreadLocal.get();
			if (currentConn != null) {
				currentConn.close();

				connectionThreadLocal.remove();

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public boolean isAutoCloseConneciton() {
		return autoCloseConneciton;
	}


	public void setAutoCloseConneciton(boolean autoCloseConneciton) {
		this.autoCloseConneciton = autoCloseConneciton;
	}



	/**
	 * 单个结果返回值
	 * @param sql
	 * @param col
	 * @return
	 */
	public String getValue(String sql ,String col) {
		// TODO Auto-generated method stub
		List<Map> maps = this.getRows(sql, null);
		if(maps!=null&&maps.size()>0){
			return maps.get(0).get(col)+"";
		}
		return "";
	}
	/**
	 * 查出Blob 字段
	 * @param selsql
	 * @return
	 * @throws IOException
	 */
	public DmdbBlob getBlobValue(String selsql,Object[] params) throws IOException{
		PreparedStatement pstate = null;
		DmdbBlob blob = null;
		ResultSet rs = null;
		boolean hasParams = true;
		if(params==null||params.length<1)
			hasParams = false;
		try {
			Connection currentConn = connectionThreadLocal.get();
			if(currentConn ==null){
				currentConn = getConnection();
				connectionThreadLocal.set(currentConn);
			}
			if(currentConn!=null){
				// 从这里更新blob 字段
				pstate = currentConn.prepareStatement(selsql);
				if(hasParams){
					for(int i=0;i<params.length;i++){
						if(params[i]!=null){
							StringReader reader = new StringReader(params[i].toString());
							pstate.setCharacterStream(i+1, reader, params[i].toString().length());
						}else{
							StringReader reader = new StringReader("");
							pstate.setCharacterStream(i+1, reader,"".length());
						}
					}
				}
				rs = pstate.executeQuery();
				if(rs.next()){
					blob = (DmdbBlob) rs.getBlob(1);
				}
				currentConn.commit();
			}else{
				Exception exception = new Exception("CurrentConnection is null!");
				logger.error("ERROR,currentConn is null"+selsql+";",exception);
			}
		}catch (Exception ex) {
			logger.error("ERROR,sql="+selsql+";",ex);
		}finally{
			closeStatement(pstate,rs);
		}
		return blob;
	}

    public Boolean readBlob(String selsql,Object[] params,OutputStream fos) throws IOException{
        PreparedStatement pstate = null;
		DmdbBlob blob = null;
        ResultSet rs = null;
        Boolean haveBlob=false;
        boolean hasParams = true;
        if(params==null||params.length<1)
            hasParams = false;
        try {
            Connection currentConn = connectionThreadLocal.get();
            if(currentConn ==null){
                currentConn = getConnection();
                connectionThreadLocal.set(currentConn);
            }
            if(currentConn!=null){
                // 从这里更新blob 字段
                pstate = currentConn.prepareStatement(selsql);
                if(hasParams){
                    for(int i=0;i<params.length;i++){
                        if(params[i]!=null){
                            StringReader reader = new StringReader(params[i].toString());
                            pstate.setCharacterStream(i+1, reader, params[i].toString().length());
                        }else{
                            StringReader reader = new StringReader("");
                            pstate.setCharacterStream(i+1, reader,"".length());
                        }
                    }
                }
                rs = pstate.executeQuery();
                if(rs.next()){
                    blob = (DmdbBlob) rs.getBlob(1);
                    //BufferedInputStream sbs = new BufferedInputStream(blob.getBinaryStream());
                    InputStream sbs=blob.getBinaryStream();
                    BufferedOutputStream bos = new BufferedOutputStream(fos);

                    int len = 0;
                    int totallen=0;
                    int i=0;
                    long crr1 = System.currentTimeMillis();
                    byte[] buffer = new byte[1024];
                    while((len = sbs.read(buffer))!=-1) {
                        bos.write(buffer,0,len);
                        totallen=totallen+len;
                        i=i+1;
                        /*if(i%10==0){*/
                            //Thread.sleep(100);
                        //}
                        /*System.out.println("--->"+(System.currentTimeMillis()-crr1));
                        System.out.println("--->totallen="+totallen);
                        System.out.println("--->i="+i);*/
                    }
                    bos.close();
                    haveBlob=true;

                }else{
                    haveBlob=false;
                }
                currentConn.commit();
            }else{
                Exception exception = new Exception("CurrentConnection is null!");
                logger.error("ERROR,currentConn is null"+selsql+";",exception);
            }
        }catch (Exception ex) {
            logger.error("ERROR,sql="+selsql+";",ex);
        }finally{
            //传入的outputstream是否要在这里关，由你们自己决定，也可以调用完这个方法后，在外面关
            fos.close();
            closeStatement(pstate,rs);
        }
        return  haveBlob;

    }
}
