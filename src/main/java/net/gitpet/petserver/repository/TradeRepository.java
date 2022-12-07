package net.gitpet.petserver.repository;

import net.gitpet.petserver.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query(value = "select * from TRADE t where t.UID = :sellerId", nativeQuery = true)
    List<Trade> findBySellerId(@Param("sellerId") long id);

    @Query(value = "delete from TRADE t where t.UPID = :petId", nativeQuery = true)
    @Modifying
    void deleteByPetId(@Param("petId") long petId);

}
