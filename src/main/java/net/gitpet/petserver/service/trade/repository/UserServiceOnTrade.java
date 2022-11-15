package net.gitpet.petserver.service.trade.repository;

import net.gitpet.petserver.service.trade.TradeUserDTO;
import net.gitpet.petserver.service.trade.extensible.TradeUserRepository;

public final class UserServiceOnTrade implements TradeUserRepository {

    // use jpa repository

    @Override
    public TradeUserDTO getUser(long id) {
        return null;
    }

    @Override
    public void updateUser(TradeUserDTO userDTO) {

    }

}
