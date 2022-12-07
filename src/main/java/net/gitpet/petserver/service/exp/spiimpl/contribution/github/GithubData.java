package net.gitpet.petserver.service.exp.spiimpl.contribution.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
final class GithubData {

    private Data data;

    @JsonIgnoreProperties({"rateLimit"})
    @Getter
    private static final class Data {

        private User user;

        @Getter
        private static final class User{
            private String name;
            private ContributionsCollection contributionsCollection;

            @Getter
            private static final class ContributionsCollection{
                private int totalCommitContributions;

            }
        }

    }

    public int getCommitCount(){
        return data.user.contributionsCollection.totalCommitContributions;
    }

}
