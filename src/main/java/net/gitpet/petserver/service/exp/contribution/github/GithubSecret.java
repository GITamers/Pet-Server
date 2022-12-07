package net.gitpet.petserver.service.exp.contribution.github;

enum GithubSecret {

    TOKEN("bearer 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");

    final String VALUE;

    GithubSecret(String value){
        VALUE = value;
    }

}
