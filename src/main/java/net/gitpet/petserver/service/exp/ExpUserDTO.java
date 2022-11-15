package net.gitpet.petserver.service.exp;

public final class ExpUserDTO {

    final String NAME;
    final long ID;
    int contributionToday;

    public ExpUserDTO(String name, long id, int contributionToday){
        NAME = name;
        ID = id;
        this.contributionToday = contributionToday;
    }

}
