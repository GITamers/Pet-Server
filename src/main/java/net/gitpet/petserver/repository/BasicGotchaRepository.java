package net.gitpet.petserver.repository;

import net.gitpet.petserver.domain.BasicGotcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicGotchaRepository extends JpaRepository<BasicGotcha, Long> {

}
