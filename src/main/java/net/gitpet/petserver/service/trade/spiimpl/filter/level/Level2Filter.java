package net.gitpet.petserver.service.trade.spiimpl.filter.level;

import org.springframework.stereotype.Service;

@Service
public final class Level2Filter extends AbstractPetLevelFilter{
    @Override
    protected long getLevel() {
        return 2;
    }
}
