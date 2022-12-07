package net.gitpet.petserver.service.exp.spiimpl.contribution.github;

enum GithubSecret {

    TOKEN("");

    final String VALUE;

    GithubSecret(String value){
        VALUE = value;
    }

}
