package net.gitpet.petserver.service.exp.contribution.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
