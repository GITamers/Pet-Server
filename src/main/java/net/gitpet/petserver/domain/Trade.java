package net.gitpet.petserver.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRADE")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TID")
    private Long tid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID")
    private User seller;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPID")
    private UserPet pet;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
