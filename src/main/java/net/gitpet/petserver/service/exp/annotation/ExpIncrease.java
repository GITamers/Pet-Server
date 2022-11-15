package net.gitpet.petserver.service.exp.annotation;

import net.gitpet.petserver.service.exp.DayOfExp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExpIncrease {

    int parameter() default 0;
    DayOfExp expLimit() default DayOfExp.LOW;

}
