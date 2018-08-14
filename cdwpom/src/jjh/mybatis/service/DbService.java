package jjh.mybatis.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import io.vertx.core.MultiMap;
import jjh.mybatis.session.SqlConfig;

public class DbService implements DbServiceDAO {
	
	SqlSession session;
	
	 /**
	   * Retrieve a list of mapped objects from the statement key and parameter.
	   * @param sql String type, Xml(mapper file) in sql name 
	   * @param param Map type parameter
	   * @return List of mapped object
	 */
	@Override
	public List<Map> getSelectList(String sql, Map param) {
		// TODO Auto-generated method stub
		try{
			
			session=SqlConfig.getSession().openSession();
			return session.selectList(sql, param);
			
		}finally
		{
			session.close();
		}
	}

	@Override
	public List<Map> getSelectList(String sql, MultiMap param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();
			return session.selectList(sql, param);
			
		}finally
		{
			session.close();
		}
	}
	
	@Override
	public List<Map> getSelectList(String sql, String param) {
		// TODO Auto-generated method stub
		try{
			
			session=SqlConfig.getSession().openSession();	
			return session.selectList(sql, param);
			
		}finally
		{
			session.close();
		}
	}
	
	@Override
	public Map getSelectMap(String sql, String param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();
			return session.selectMap(sql, param);
		
		}finally
		{
			session.close();
		}
	}

	
	 /**
	   * Retrieve a list of mapped objects from the statement key and parameter.
	   * @param sql String type, Xml(mapper file) in sql name 
	   * @param param Map type parameter
	   * @return List of mapped object
	 */
	@Override
	public int doUpdate(String sql, Map param) {
		// TODO Auto-generated method stub
		try{
			
			session=SqlConfig.getSession().openSession();
			return session.update(sql,param);
		
		}finally
		{
			session.close();
		}
	}

	@Override
	public int doUpdate(String sql, String param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();	
			return session.update(sql,param);
		
		}finally
		{
			session.close();
		}
	}

	
	 /**
	   * Retrieve a list of mapped objects from the statement key and parameter.
	   * @param sql String type, Xml(mapper file) in sql name 
	   * @param param Map type parameter
	   * @return List of mapped object
	 */
	@Override
	public int doInsert(String sql, Map param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();	
			return session.insert(sql, param);
		
		}finally
		{
			session.close();
		}
	}

	@Override
	public int doInsert(String sql, String param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();
			return session.insert(sql, param);
		
		}finally
		{
			session.close();
		}
	}

	 /**
	   * Retrieve a list of mapped objects from the statement key and parameter.
	   * @param sql String type, Xml(mapper file) in sql name 
	   * @param param Map type parameter
	   * @return List of mapped object
	 */
	@Override
	public int doDelete(String sql, Map param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();
			return session.delete(sql, param);
		}finally
		{
			session.close();
		}
	}

	@Override
	public int doDelete(String sql, String param) {
		// TODO Auto-generated method stub
		try{
			session=SqlConfig.getSession().openSession();
			return session.delete(sql, param);
		}finally
		{
			session.close();
		}
	}

}
