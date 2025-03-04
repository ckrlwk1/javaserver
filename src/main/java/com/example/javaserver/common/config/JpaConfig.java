package com.example.javaserver.common.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.javaserver.common.jpa.repository", // JPA Repository 경로
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JpaConfig {

    private final DataSource dataSource;

    public JpaConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.javaserver.common.jpa.entity"); // 엔티티 클래스가 위치한 패키지
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // Hibernate JPA Vendor Adapter 설정

        // Hibernate JPA 추가 설정
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // 스키마 자동 업데이트
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // MySQL8 방언
        jpaProperties.setProperty("hibernate.show_sql", "true"); // SQL 출력
        jpaProperties.setProperty("hibernate.format_sql", "true"); // SQL 포맷팅

        em.setJpaProperties(jpaProperties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
