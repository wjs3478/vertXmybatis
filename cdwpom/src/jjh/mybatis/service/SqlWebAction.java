package jjh.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;

public class SqlWebAction extends DbService {
	
	public void sqlAction(HttpServerRequest req, HttpServerResponse res)
	{
		
		List<Map> list=null;
		String sqlType=req.getParam("sqltype");
		String sqlName=req.getParam("sqlname");
		int suc=-1;
		System.out.println(req.params());
		Map<String,Object> params= new HashMap<String,Object>();
		getParamMap(req,params);
		
					
		switch(sqlType)
		{
			case "select":
				System.out.println("select");
				list=this.getSelectList(sqlName, params);
				suc=0;
				break;
			case "update":
				System.out.println("update");
				suc=this.doUpdate(sqlName, params);
				break;
			case "delete":
				System.out.println("delete");
				suc=this.doDelete(sqlName, params);
				break;
			case "insert":
				System.out.println("insert");
				suc=this.doInsert(sqlName, params);
				break;
		}
		
		
		//response시 utf-8에 json형태로 반환
		res.putHeader("content-type", "application/json; charset=utf-8");
		res.putHeader("success",String.valueOf(suc));
		
		//chunk응답, 요청 본문의 전체 크기를 알지 못하는 경우에 사용된다.
		res.setChunked(true);
				
		res.end(Json.encodePrettily(list));
	}
	
	
	
	
	 /**
	   * request param parameter vertx mulitiMap convert to Map parameter.
	   * @param reqs vertx request param 
	   * @param maps Map type parameter
	   * @return Map object(request param parameter)
	 */
	private void getParamMap(HttpServerRequest reqs, Map<String,Object> maps) {
		
		//파라미터
		MultiMap param=reqs.params();
		
		//정규식 설정 [] 안에 데이터 가져오기 위한 정규식
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m=null;
		
		for(String key:param.names())
		{
			m = p.matcher(key);
			if(m.find()){
					maps.put(m.group(1), param.get(key));
			}
		}
	}

}
