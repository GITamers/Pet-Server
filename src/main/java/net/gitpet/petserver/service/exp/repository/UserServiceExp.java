package net.gitpet.petserver.service.exp.repository;

import net.gitpet.petserver.service.exp.ExpUserDTO;
import net.gitpet.petserver.service.exp.extensible.ExpUserRepository;

public final class UserServiceExp implements ExpUserRepository {

    // user jpa repository

    @Override
    public ExpUserDTO getUser(long id) {
        return null;
    }

    @Override
    public ExpUserDTO getUser(String name) {
        return null;
    }

    @Override
    public void updateUser(ExpUserDTO user) {

    }

}
