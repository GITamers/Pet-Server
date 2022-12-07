package net.gitpet.petserver.service.trade.crud.filter.type;

import org.springframework.stereotype.Service;

@Service
public final class TypeDinoFilter extends AbstractTypeFilter{
    @Override
    protected long getPetTypeId() {
        return 1;
    }
}
