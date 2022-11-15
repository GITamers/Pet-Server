package net.gitpet.petserver.service.exp;

import net.gitpet.petserver.service.exp.extensible.Contribution;
import net.gitpet.petserver.service.exp.extensible.ExpUserRepository;
import org.jvault.annotation.Inject;
import org.jvault.annotation.InternalBean;

@InternalBean(accessClasses = "net.gitpet.petserver.service.exp.Accessor")
public final class Accessor {

    final Contribution FACADE_CONTRIBUTION;
    final ExpUserRepository EXP_USER_REPOSITORY;

    @Inject
    private Accessor(@Inject("facadeContribution") Contribution facadeContribution,
                     @Inject("defaultExpUserRepository") ExpUserRepository defaultExpUserRepository){
        FACADE_CONTRIBUTION = facadeContribution;
        EXP_USER_REPOSITORY = defaultExpUserRepository;
    }

}
