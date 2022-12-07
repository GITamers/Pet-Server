package net.gitpet.petserver.service.exp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExpUserDTO {

    private final long ID;
    private long contributionToday;
    private long point;

    public ExpUserDTO(long id, long point, long contributionToday){
        ID = id;
        this.point = point;
        this.contributionToday = contributionToday;
    }

}
