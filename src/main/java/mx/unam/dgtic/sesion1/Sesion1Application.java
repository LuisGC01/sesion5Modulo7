package mx.unam.dgtic.sesion1;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class Sesion1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sesion1Application.class, args);
	}

	@Bean
	public DataSource dataSource() {

		// conectividad con el servidor de base de datos
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver"); // com.mysql.jdbc.Driver
		dataSource.setUrl("jdbc:mariadb://192.168.0.5:3307/modulo7");
		dataSource.setUsername("root");
		dataSource.setPassword("Holacomo2");
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		// la parte de JPA que permite seleccionar o persistir información en la base de
		// datos

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("mx.unam.dgtic.sesion1");

		Properties jpaProperties = new Properties();

		// jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect");

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;

	}

	@Bean
	public PlatformTransactionManager transactionManager() {

		// Permite realizar las operaciondes CRUD en la base de datos

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory(dataSource()).getObject());
		return txManager;

	}

}
