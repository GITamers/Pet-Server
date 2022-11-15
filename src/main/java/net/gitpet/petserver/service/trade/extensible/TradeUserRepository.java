package net.gitpet.petserver.service.trade.extensible;

import net.gitpet.petserver.service.trade.TradeUserDTO;

public interface TradeUserRepository {

    TradeUserDTO getUser(long id);

    void updateUser(TradeUserDTO userDTO);

}
