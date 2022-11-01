package net.gitpet.petserver.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER_PET")
public class UserPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPID")
    private Long upid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UID")
    private User owner;

    @Column(name = "LEVEL", nullable = false)
    private Long level;

    @CreatedDate
    @Column(name = "ADOPTED_AT")
    private LocalDateTime adoptedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PID")
    private Pet type;

    @Column(name = "MAIN_PET", nullable = false)
    private boolean mainPet;

    @Column(name = "COMMIT_CNT", nullable = false)
    private Long commitCnt;

}
