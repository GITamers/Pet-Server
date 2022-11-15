package net.gitpet.petserver.service.exp;

import net.gitpet.petserver.service.exp.annotation.ExpIncrease;
import net.gitpet.petserver.service.exp.extensible.Contribution;
import net.gitpet.petserver.service.exp.extensible.ExpUserRepository;
import net.gitpet.petserver.service.util.AnnotationExtractor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jvault.vault.ClassVault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Aspect
@Service
public final class ExpProvider{

    private final ExpUserRepository EXP_USER_REPOSITORY;
    private final Contribution FACADE_CONTRIBUTION;
    private final AnnotationExtractor ANNOTATION_EXTRACTOR;

    @Before("@annotation(net.gitpet.petserver.service.exp.annotation.ExpIncrease)")
    public void provide(JoinPoint joinPoint){
        Object[] objs = joinPoint.getArgs();
        ExpIncrease expIncrease = ANNOTATION_EXTRACTOR.getAnnotation(joinPoint, ExpIncrease.class);
        DayOfExp expLimit = expIncrease.expLimit();

        String name = (String)objs[expIncrease.parameter()];
        ExpUserDTO userDTO = null;
        if(objs[expIncrease.parameter()] instanceof Long) EXP_USER_REPOSITORY.getUser((long)objs[expIncrease.parameter()]);
        if(objs[expIncrease.parameter()] instanceof String) EXP_USER_REPOSITORY.getUser((String)objs[expIncrease.parameter()]);

        int contribution = FACADE_CONTRIBUTION.todayContributions(Contribution.Request.getBuilder().name(name).build());
        if(contribution-userDTO.contributionToday > expLimit.getExpLimit()) return;
        userDTO.contributionToday += contribution-userDTO.contributionToday;
        EXP_USER_REPOSITORY.updateUser(userDTO);
    }

    @Autowired
    private ExpProvider(@Qualifier("expVault") ClassVault vault,
                        AnnotationExtractor annotationExtractor){
        Accessor accessor = vault.inject(Accessor.class);
        EXP_USER_REPOSITORY = accessor.EXP_USER_REPOSITORY;
        FACADE_CONTRIBUTION = accessor.FACADE_CONTRIBUTION;
        ANNOTATION_EXTRACTOR = annotationExtractor;
    }

}
