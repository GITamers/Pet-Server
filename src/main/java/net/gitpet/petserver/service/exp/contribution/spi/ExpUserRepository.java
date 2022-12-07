package net.gitpet.petserver.service.exp.contribution.spi;

import net.gitpet.petserver.service.exp.contribution.dto.ExpUserDTO;

public interface ExpUserRepository {

    ExpUserDTO getUser(long id);

    void updateUser(ExpUserDTO user);

}
