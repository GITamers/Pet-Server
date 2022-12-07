package net.gitpet.petserver.service.trade.crud.filter.level;

import org.springframework.stereotype.Service;

@Service
public final class Level5Filter extends AbstractPetLevelFilter{
    @Override
    protected long getLevel() {
        return 5;
    }
}
