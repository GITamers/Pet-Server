package net.gitpet.petserver.service.util;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Service
public final class AnnotationExtractor {

    public <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> required){
        String name = joinPoint.getSignature().getName();
        Class<?> cls = joinPoint.getTarget().getClass();
        Method[] methods = cls.getDeclaredMethods();
        for(Method method : methods){
            if(method.getName().equals(name))
                return method.getDeclaredAnnotation(required);
        }
        throw new IllegalStateException("Can not find annotation \"" + required.getSimpleName() + "\"");
    }

}
