package net.gitpet.petserver.service.trade.spiimpl.filter.type;

import org.springframework.stereotype.Service;

@Service
public final class TypeDinoFilter extends AbstractTypeFilter{
    @Override
    protected long getPetTypeId() {
        return 1;
    }
}
