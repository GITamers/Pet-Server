package net.gitpet.petserver.auth;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthValidate{
    TokenType tokenType() default TokenType.JWT;
}
