package com.mycart.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@ComponentScan(value = { "com.mycart.controller.*", "com.mycart.service.*", "com.mycart.dto.*", "com.mycart.repository",
		"com.mycart.*" })
@EnableJpaRepositories("com.mycart.repository")
@EntityScan("com.mycart.entity")
@SpringBootApplication
@EnableWebMvc
/*public class MyCartApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MyCartApplication.class, args);
	}
	
	 @Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	      registry.addViewController("/").setViewName("index");
	   }
	 
	   @Bean
	   public ViewResolver viewResolver() {
	      InternalResourceViewResolver bean = new InternalResourceViewResolver();
	 
	      bean.setViewClass(JstlView.class);
	      bean.setPrefix("/WEB-INF/jsp/");
	      bean.setSuffix(".jsp");
	 
	      return bean;
	   }}

*/
public class MyCartApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MyCartApplication.class, args);
	}	 
	   @Bean
	   public ViewResolver viewResolver() {
	      InternalResourceViewResolver bean = new InternalResourceViewResolver();
	 
	      bean.setViewClass(JstlView.class);
	      bean.setPrefix("/WEB-INF/jsp/");
	      bean.setSuffix(".jsp");
	 
	      return bean;
	   }}