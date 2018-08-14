package jjh.mybatis.service;

import java.util.List;
import java.util.Map;

import io.vertx.core.MultiMap;

public abstract interface DbServiceDAO {
	
	//select¹®
	public abstract List<Map> getSelectList(String sql, Map paramMap);
	public abstract List<Map> getSelectList(String sql, MultiMap paramMap);
	public abstract List<Map> getSelectList(String sql, String param);
	public abstract Map getSelectMap(String sql, String param);

	//update
	public abstract int doUpdate(String sql, Map paramMap);
	public abstract int doUpdate(String sql, String param);
	
	//insert
	public abstract int doInsert(String sql, Map paramMap);
	public abstract int doInsert(String sql, String param);
	
	//delete
	public abstract int doDelete(String sql, Map paramMap);
	public abstract int doDelete(String sql, String param);
}