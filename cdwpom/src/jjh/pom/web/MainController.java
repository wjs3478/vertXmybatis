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
		
		System.out.println("��������");
		//SqlSetting sqlManager=new SqlSetting();
		//sqlManager.init();
		
		//http webserver ��ü ����
		HttpServer server = vertx.createHttpServer();
		
		//����� ����
		Router router = Router.router(vertx);
		
		//����� �н� ���� �� ������ ���� �������� �ø���(url���, ���ҽ����)
		router.route("/admin/*").handler(StaticHandler.create("main/web"));
		
		//CORS ���, ����� ���϶��� ���, �� ���� ����� �ּ� ó�� ���
		router.route().handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST).allowedHeader("Authorization").allowedHeader("Content-Type"));
		
		//post������ ���� ��, �� ������ �߰����־����
		router.route().handler(BodyHandler.create());
		
		//controller ����
		Route route1=router.route().pathRegex(".*.sql.do").handler(routingCon->{
			
			SqlWebAction swa=new SqlWebAction();
			
			System.out.println("¯�̴�");
		
			HttpServerRequest request= routingCon.request();
		
			//response ��ü ����
			HttpServerResponse response = routingCon.response();
			
			swa.sqlAction(request, response);
			
		});
		
		
		//controller ����
		Route route2=router.route().pathRegex(".*.do").handler(routingCon->{
			
			System.out.println("¯�̴�23");
			System.out.println(routingCon.request().uri());
			//response ��ü ����
			HttpServerResponse response = routingCon.response();
			
			//response�� utf-8�� json���·� ��ȯ
			response.putHeader("content-type", "application/json; charset=utf-8");
			
			//chunk����, ��û ������ ��ü ũ�⸦ ���� ���ϴ� ��쿡 ���ȴ�.
			response.setChunked(true);
			
			//response.end(Json.encodePrettily(sqlManager.testSql()));
			
		});
		
		//���� 8080��Ʈ�� ����
		server.requestHandler(router::accept).listen(8080);

	}
	
	@Override
	public void stop() throws Exception {
		
		System.out.println("�������2");
	}
}
