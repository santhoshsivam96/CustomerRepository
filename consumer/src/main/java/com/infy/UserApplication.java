package com.infy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="com.infy")
@EnableJpaRepositories("com.infy.repository")

public class UserApplication {
	public static final Log LOGGER=LogFactory.getLog(UserApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);//
	}

	/*
	 * @Override public void run(String... args) throws Exception { UserServiceImpl
	 * user=new UserServiceImpl(); Integer result=user.findRatingByUserId("151");
	 * LOGGER.info(result); }
	 */

}
