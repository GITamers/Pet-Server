package net.gitpet.petserver.configuration.vault;

import net.gitpet.petserver.service.exp.Accessor;
import net.gitpet.petserver.service.exp.contribution.FacadeContribution;
import net.gitpet.petserver.service.exp.contribution.github.GithubContribution;
import net.gitpet.petserver.service.exp.repository.DefaultExpUserRepository;
import org.jvault.annotation.BeanWire;
import org.jvault.annotation.VaultConfiguration;

@VaultConfiguration(name = "expVault", vaultAccessClasses = "net.gitpet.petserver.service.exp.Accessor")
public final class ExpBuildInfo {

    @BeanWire
    private GithubContribution githubContribution;

    @BeanWire
    private FacadeContribution facadeContribution;

    @BeanWire
    private DefaultExpUserRepository defaultExpUserRepository;

    @BeanWire
    private Accessor accessor;

}
