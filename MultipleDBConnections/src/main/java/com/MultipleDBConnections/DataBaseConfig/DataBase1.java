package com.MultipleDBConnections.DataBaseConfig;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "studentEntityManagerFactory",
		basePackages= { "com.MultipleDBConnections.repo1"},
		transactionManagerRef = "studentTransactionManager"
		)
public class DataBase1 {
	
	@Autowired
	Environment env;
	
//	department.application.name=MultipleDBConnections
//			department.datasource.url=jdbc:postgresql://192.168.2.64:5432/sample18Feb
//			department.datasource.username=postgres
//			department.datasource.password=postgres
//			department.datasource.driver-class-name=org.postgresqul.Driver
//	
	@Primary
	@Bean(name = "studentDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setUrl(env.getProperty("student.datasource.url"));
		ds.setUsername(env.getProperty("student.datasource.username"));
		ds.setPassword(env.getProperty("student.datasource.password"));
		ds.setDriverClassName(env.getProperty("student.datasource.driver-class-name"));
		return ds;
	}
	
	@Primary
	@Bean(name = "studentEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManger() {
		LocalContainerEntityManagerFactoryBean em= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adapter);
		HashMap<String, Object> prop =new HashMap<>();
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		em.setJpaPropertyMap(prop);
		em.setPackagesToScan("com.MultipleDBConnections.model1");
		return em;
	}
	
	@Primary
	@Bean("studentTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("studentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
