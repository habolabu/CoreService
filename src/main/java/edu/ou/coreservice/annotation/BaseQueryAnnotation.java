package edu.ou.coreservice.annotation;

import edu.ou.coreservice.config.MongoTransactionManagerConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@ComponentScan("edu.ou")
@SpringBootApplication
@Retention(RetentionPolicy.RUNTIME)
@Import(MongoTransactionManagerConfig.class)
public @interface BaseQueryAnnotation {
}
