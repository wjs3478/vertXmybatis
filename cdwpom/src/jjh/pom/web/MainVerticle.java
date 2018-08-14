package jjh.pom.web;

import io.vertx.core.Vertx;

public class MainVerticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Vertx vertx = Vertx.vertx();
		 
	 	vertx.deployVerticle(new MainController());
	}

}
