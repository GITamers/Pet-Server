package net.gitpet.petserver.service.exp.contribution;

import net.gitpet.petserver.service.exp.contribution.dto.ExpUserDTO;
import net.gitpet.petserver.service.exp.contribution.spi.Contribution;
import net.gitpet.petserver.service.exp.contribution.spi.ExpUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public final class ExpProvider{

    private final ExpUserRepository USER_SERVICE_EXP;

    private final Contribution FACADE_CONTRIBUTION;

    void provide(String name, long id, int expLimit){
        ExpUserDTO userDTO = USER_SERVICE_EXP.getUser(id);
        if(userDTO.getContributionToday() >= expLimit) return;

        long diffContributionCount = getContributionCount(name, userDTO);
        userDTO.setContributionToday(userDTO.getContributionToday() + diffContributionCount);
        int POINT_UNIT = 10;
        userDTO.setPoint(userDTO.getPoint() + (diffContributionCount * POINT_UNIT));

        USER_SERVICE_EXP.updateUser(userDTO);
    }

    private long getContributionCount(String name, ExpUserDTO userDTO){
        long todayContributions = FACADE_CONTRIBUTION.todayContributions(name);
        long ans = todayContributions - userDTO.getContributionToday();
        if(ans < 0) return 0;
        return ans;
    }

    @Autowired
    private ExpProvider(ExpUserRepository userServiceExp,
                        @Qualifier("facadeContribution") Contribution facadeContribution){
        USER_SERVICE_EXP = userServiceExp;
        FACADE_CONTRIBUTION = facadeContribution;
    }

}
