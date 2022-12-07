package net.gitpet.petserver.service.trade.transaction.extensible;

import net.gitpet.petserver.service.trade.transaction.dto.TradeUserDTO;

public interface TradeUserRepository {

    TradeUserDTO getUser(long id);

    void updateUser(TradeUserDTO userDTO);

}
