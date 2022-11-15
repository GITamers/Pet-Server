package net.gitpet.petserver.service.exp.contribution.github;

import net.gitpet.petserver.service.exp.extensible.Contribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GithubContributionTest {

    @Test
    public void GITHUB_CONTRIBUTION_TEST(){
        // given
        Contribution contribution = new GithubContribution();

        // when
        contribution.todayContributions(
                Contribution.Request.getBuilder()
                        .name("devxb").token("Bearer some_token").build());

        // then
        Assertions.assertTrue(true);
    }

}
