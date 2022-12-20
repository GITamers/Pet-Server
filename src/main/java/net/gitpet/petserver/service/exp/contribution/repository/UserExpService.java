package net.gitpet.petserver.service.exp.contribution.repository;

import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.repository.UserJpaRepository;
import net.gitpet.petserver.service.exp.contribution.dto.ExpUserDTO;
import net.gitpet.petserver.service.exp.contribution.spi.ExpUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserExpService implements ExpUserRepository {

    private final UserJpaRepository USER_REPOSITORY;

    @Autowired
    UserExpService(UserJpaRepository userJPARepository){
        USER_REPOSITORY = userJPARepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ExpUserDTO getUser(long id) {
        User user = USER_REPOSITORY.findById(id)
                .orElseThrow(()-> new IllegalStateException("Can not find user \"" + id + "\""));
        return new ExpUserDTO(user.getUid(), user.getPoint(), user.getCommitCnt());
    }

    @Override
    @Transactional
    public void updateUser(ExpUserDTO expUser) {
        User user = USER_REPOSITORY.findById(expUser.getID())
                .orElseThrow(()-> new IllegalStateException("Can not find user \"" + expUser.getID() + "\""));
        user.setUid(expUser.getID());
        user.setCommitCnt(expUser.getContributionToday());
        user.setCommitUpdatedAt(LocalDateTime.now());
        user.setPoint(expUser.getPoint());
    }

}
