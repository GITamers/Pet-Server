package net.gitpet.petserver.service.exp.contribution;

import net.gitpet.petserver.service.exp.extensible.Contribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class FacadeContributionTest {

    @Test
    public void GET_TODAY_CONTRIBUTION_TEST(){
        // given
        Contribution contribution = new FacadeContribution((r) -> (int)(Math.random()*5));

        // when
        int contributionCount = contribution.todayContributions(Contribution.Request.getBuilder().name("mock user").build());

        // then
        Assertions.assertTrue(contributionCount > 0);
    }

}
