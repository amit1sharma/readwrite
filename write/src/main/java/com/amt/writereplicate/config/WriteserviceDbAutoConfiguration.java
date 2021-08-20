//package com.amt.writereplicate.config;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableJpaRepositories(
//		basePackages={"com.amt.writereplicate.repository"},
//  entityManagerFactoryRef = "writeServiceEntityManagerFactory",
//  transactionManagerRef = "writeServiceTransactionManager")
//@EnableTransactionManagement
//public class WriteserviceDbAutoConfiguration {
//
//    @Bean("writeServiceEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean phubEntityManager(EntityManagerFactoryBuilder builder, @Qualifier ("writeServiceDataSource")DataSource dataSource) {
//		return builder
//				.dataSource(dataSource)
//				.persistenceUnit("writeService")
//				.packages("com.amt.writereplicate.entity")
//				.build();
//    }
//
//    @Bean
//    public PlatformTransactionManager writeServiceTransactionManager(@Qualifier("writeServiceEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//    	return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Bean("writeServiceDataSource")
//    @Profile({"dev", "test"})
//    @ConfigurationProperties(prefix="spring.writeservice-datasource")
//    public DataSource writeServiceDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//}