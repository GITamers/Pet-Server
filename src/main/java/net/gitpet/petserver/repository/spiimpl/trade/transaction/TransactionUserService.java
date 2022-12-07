package net.gitpet.petserver.repository.spiimpl.trade.transaction;

import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.repository.UserRepository;
import net.gitpet.petserver.service.trade.transaction.dto.TradeUserDTO;
import net.gitpet.petserver.service.trade.transaction.spi.TradeUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionUserService implements TradeUserRepository {

    private final UserRepository USER_REPOSITORY;

    @Override
    @Transactional(readOnly = true)
    public TradeUserDTO getUser(long id) {
        User user = USER_REPOSITORY.findById(id).orElseThrow(()-> new IllegalStateException("Can not find user id \"" + id + "\""));
        return new TradeUserDTO(user.getUid(), user.getPoint());
    }

    @Override
    @Transactional
    public void updateUser(TradeUserDTO userDTO) {
        User user = USER_REPOSITORY.findById(userDTO.getID()).orElseThrow(()-> new IllegalStateException("Can not find user id \"" + userDTO.getID() + "\""));
        user.setPoint(userDTO.getMoney());
    }

    @Autowired
    TransactionUserService(UserRepository userRepository){
        USER_REPOSITORY = userRepository;
    }

}
