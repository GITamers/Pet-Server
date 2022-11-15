package net.gitpet.petserver.configuration.vault;

import net.gitpet.petserver.service.trade.Accessor;
import net.gitpet.petserver.service.trade.repository.DefaultTradeRepository;
import net.gitpet.petserver.service.trade.repository.DefaultUserRepository;
import org.jvault.annotation.BeanWire;
import org.jvault.annotation.VaultConfiguration;

@VaultConfiguration(name = "tradeVault", vaultAccessClasses = "net.gitpet.petserver.service.trade.Accessor")
public final class TradeBuildInfo {

    @BeanWire
    private DefaultTradeRepository defaultTradeRepository;

    @BeanWire
    private DefaultUserRepository defaultUserRepository;

    @BeanWire
    private Accessor accessor;

}
