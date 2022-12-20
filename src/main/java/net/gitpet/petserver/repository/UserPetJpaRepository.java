package net.gitpet.petserver.repository;

import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPetJpaRepository extends JpaRepository<UserPet, Long> {

    List<UserPet> findAllByOwner(User user);
    UserPet findUserPetsByOwnerAndMainPetIsTrue(User user);
}
