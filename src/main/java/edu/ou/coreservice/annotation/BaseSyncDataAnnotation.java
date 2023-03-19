package edu.ou.coreservice.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@ComponentScan("edu.ou")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseSyncDataAnnotation {
}
