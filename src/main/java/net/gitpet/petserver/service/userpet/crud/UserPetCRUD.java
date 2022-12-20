package net.gitpet.petserver.service.userpet.crud;

import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;

import java.util.List;

public interface UserPetCRUD {
    List<UserPetDTO> getUserPetList(Long userId);

    UserPetDTO getUserPetDetail(Long petId);

    void updateMainPet(Long userId, Long petId);
}
