package net.gitpet.petserver.service.exp.spiimpl.contribution;

import net.gitpet.petserver.service.exp.contribution.spi.Contribution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class FacadeContribution implements Contribution {

    private final List<Contribution> CONTRIBUTIONS;

    @Autowired
    FacadeContribution(@Qualifier("githubContribution") Contribution githubContribution){
        this.CONTRIBUTIONS = new ArrayList<>();
        this.CONTRIBUTIONS.add(githubContribution);
    }

    @Override
    public int todayContributions(String name) {
        int contributeCount = 0;
        for(Contribution contribution : CONTRIBUTIONS){
            if(contribution.getClass() == FacadeContribution.class) continue;
            contributeCount += contribution.todayContributions(name);
        }
        return contributeCount;
    }

}
