package net.gitpet.petserver.service.exp.extensible;

import net.gitpet.petserver.service.exp.ExpUserDTO;

public interface ExpUserRepository {

    ExpUserDTO getUser(long id);

    ExpUserDTO getUser(String name);

    void updateUser(ExpUserDTO user);

}
