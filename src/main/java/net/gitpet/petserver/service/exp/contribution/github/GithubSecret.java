package net.gitpet.petserver.service.exp.contribution.github;

enum GithubSecret {

    TOKEN("");

    final String VALUE;

    GithubSecret(String value){
        VALUE = value;
    }

}
