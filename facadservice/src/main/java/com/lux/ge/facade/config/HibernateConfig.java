package com.lux.ge.facade.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan({ "com.lux.ge.facad.services", "com.lux.ge.facad.validator", "com.lux.ge.facad.config" })
public class HibernateConfig {

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.lux.ge.facad.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource restDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://postgres_ge:5432/eventdb");
		dataSource.setUsername("admin");
		dataSource.setPassword("admin");

		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties() {
		return new Properties() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.hbm2ddl.auto", "create");
				setProperty("hibernate.show_sql", "false");
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				setProperty("spring.jpa.properties.hibernate.default_schema", "public");
			}
		};
	}
}