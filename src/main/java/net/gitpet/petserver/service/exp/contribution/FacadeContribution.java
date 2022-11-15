package net.gitpet.petserver.service.exp.contribution;

import net.gitpet.petserver.service.exp.extensible.Contribution;
import org.jvault.annotation.Inject;
import org.jvault.annotation.InternalBean;

import java.util.ArrayList;
import java.util.List;

@InternalBean(accessClasses = "net.gitpet.petserver.service.exp.Accessor")
public final class FacadeContribution implements Contribution {

    private final List<Contribution> CONTRIBUTIONS;

    @Inject
    FacadeContribution(@Inject("githubContribution") Contribution githubContribution){
        this.CONTRIBUTIONS = new ArrayList<>();
        this.CONTRIBUTIONS.add(githubContribution);
    }

    @Override
    public int todayContributions(Request request) {
        int contributeCount = 0;
        for(Contribution contribution : CONTRIBUTIONS){
            if(contribution.getClass() == FacadeContribution.class) continue;
            contributeCount += contribution.todayContributions(request);
        }
        return contributeCount;
    }

}
