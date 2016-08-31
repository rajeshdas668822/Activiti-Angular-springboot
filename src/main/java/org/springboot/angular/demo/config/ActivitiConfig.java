package org.springboot.angular.demo.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.h2.Driver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;


/**
 * Created by rdas on 8/25/2016.
 */

@Configuration
@ImportResource("classpath:spring-activiti.cfg.xml")
public class ActivitiConfig {

}
