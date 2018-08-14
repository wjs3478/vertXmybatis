package jjh.mybatis.session;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * mybatis setting
*/
public class SqlConfig {

	static SqlSessionFactory sqlSessionFactory;
	static ApplicationContext appContext;
	
	static{
				
		try {
			appContext=new ClassPathXmlApplicationContext("main/resource/config/datasource-config.xml");
			sqlSessionFactory= (SqlSessionFactory) appContext.getBean("sqlSessionFactory");
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSession(){
		
		return sqlSessionFactory;
	}
}