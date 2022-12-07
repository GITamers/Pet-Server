package net.gitpet.petserver.service.exp.extensible;

import net.gitpet.petserver.service.exp.dto.ExpUserDTO;

public interface ExpUserRepository {

    ExpUserDTO getUser(long id);

    void updateUser(ExpUserDTO user);

}
