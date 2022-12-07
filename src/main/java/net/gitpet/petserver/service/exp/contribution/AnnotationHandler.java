package net.gitpet.petserver.service.exp.contribution;

import net.gitpet.petserver.service.exp.contribution.spi.annotation.ExpIncrease;
import net.gitpet.petserver.service.util.AnnotationExtractor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Aspect
@Service
public final class AnnotationHandler {

    private final ExpProvider EXP_PROVIDER;
    private final AnnotationExtractor ANNOTATION_EXTRACTOR;

    @Before("@annotation(net.gitpet.petserver.service.exp.contribution.annotation.ExpIncrease)")
    public void provide(JoinPoint joinPoint){
        ExpIncrease expIncrease = ANNOTATION_EXTRACTOR.getAnnotation(joinPoint, ExpIncrease.class);
        String name = (String) joinPoint.getArgs()[expIncrease.nameIdx()];
        long id = (long) joinPoint.getArgs()[expIncrease.idIdx()];
        EXP_PROVIDER.provide(name, id, expIncrease.expLimit().getExpLimit());
    }

    @Autowired
    private AnnotationHandler(@Qualifier("annotationExtractor") AnnotationExtractor annotationExtractor,
                             @Qualifier("expProvider") ExpProvider expProvider){
        EXP_PROVIDER = expProvider;
        ANNOTATION_EXTRACTOR = annotationExtractor;
    }

}
