package mti.com.telegram.model.annotation;

import mti.com.telegram.model.NumberType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DATATYPE {
    NumberType type();

    int decimal();

    int sign_length() default 0;

    int point_length() default 0;
}
