package jjh.pom.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import jjh.mybatis.service.SqlWebAction;

public class MainController extends AbstractVerticle {


	@Override
    public void start() throws Exception {
		
		System.out.println("서버시작");
		//SqlSetting sqlManager=new SqlSetting();
		//sqlManager.init();
		
		//http webserver 객체 생성
		HttpServer server = vertx.createHttpServer();
		
		//라우터 생성
		Router router = Router.router(vertx);
		
		//라우터 패스 설정 및 정적인 파일 웹서버에 올리기(url경로, 리소스경로)
		router.route("/admin/*").handler(StaticHandler.create("main/web"));
		
		//CORS 허용, 디버깅 중일때만 허용, 실 서버 적용시 주석 처리 요망
		router.route().handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST).allowedHeader("Authorization").allowedHeader("Content-Type"));
		
		//post데이터 받을 시, 이 구문을 추가해주어야함
		router.route().handler(BodyHandler.create());
		
		//controller 설정
		Route route1=router.route().pathRegex(".*.sql.do").handler(routingCon->{
			
			SqlWebAction swa=new SqlWebAction();
			
			System.out.println("짱이다");
		
			HttpServerRequest request= routingCon.request();
		
			//response 객체 생성
			HttpServerResponse response = routingCon.response();
			
			swa.sqlAction(request, response);
			
		});
		
		
		//controller 설정
		Route route2=router.route().pathRegex(".*.do").handler(routingCon->{
			
			System.out.println("짱이다23");
			System.out.println(routingCon.request().uri());
			//response 객체 생성
			HttpServerResponse response = routingCon.response();
			
			//response시 utf-8에 json형태로 반환
			response.putHeader("content-type", "application/json; charset=utf-8");
			
			//chunk응답, 요청 본문의 전체 크기를 알지 못하는 경우에 사용된다.
			response.setChunked(true);
			
			//response.end(Json.encodePrettily(sqlManager.testSql()));
			
		});
		
		//서버 8080포트로 오픈
		server.requestHandler(router::accept).listen(8080);

	}
	
	@Override
	public void stop() throws Exception {
		
		System.out.println("배고프다2");
	}
}
