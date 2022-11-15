package net.gitpet.petserver.configuration;

import net.gitpet.petserver.configuration.vault.ExpBuildInfo;
import net.gitpet.petserver.configuration.vault.TradeBuildInfo;
import org.jvault.factory.TypeVaultFactory;
import org.jvault.factory.VaultFactory;
import org.jvault.factory.buildinfo.AnnotationVaultFactoryBuildInfo;
import org.jvault.vault.ClassVault;
import org.jvault.vault.VaultType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class JvaultConfiguration {

    @Bean("tradeVault")
    @DependsOn("beanProvider")
    public ClassVault getTradeVault(){
        VaultFactory<VaultType> vaultFactory = TypeVaultFactory.getInstance();
        AnnotationVaultFactoryBuildInfo annotationVaultFactoryBuildInfo = new AnnotationVaultFactoryBuildInfo(TradeBuildInfo.class);
        return vaultFactory.get(annotationVaultFactoryBuildInfo, VaultType.CLASS);
    }

    @Bean("expVault")
    @DependsOn("beanProvider")
    public ClassVault getExpVault(){
        VaultFactory<VaultType> vaultFactory = TypeVaultFactory.getInstance();
        AnnotationVaultFactoryBuildInfo annotationVaultFactoryBuildInfo = new AnnotationVaultFactoryBuildInfo(ExpBuildInfo.class);
        return vaultFactory.get(annotationVaultFactoryBuildInfo, VaultType.CLASS);
    }

}
