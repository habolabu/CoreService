package edu.ou.coreservice.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@ComponentScan("edu.ou")
@SpringBootApplication
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseCommandAnnotation {
}
