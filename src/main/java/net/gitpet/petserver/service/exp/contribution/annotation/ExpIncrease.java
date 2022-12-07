package net.gitpet.petserver.service.exp.contribution.annotation;

import net.gitpet.petserver.service.exp.contribution.DayOfExp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExpIncrease {

    int nameIdx() default 0;
    int idIdx() default 1;
    DayOfExp expLimit() default DayOfExp.LOW;

}
