package cn.topcheer.pms2.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@EntityScan(basePackages = {"cn.topcheer.halberd.app","cn.topcheer.pms2"})
@EnableJpaRepositories(basePackages = {"cn.topcheer.**.jpa"})
@Configuration
public class JpaConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
//        return txManager;
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory.unwrap(SessionFactory.class));

        JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }


}
