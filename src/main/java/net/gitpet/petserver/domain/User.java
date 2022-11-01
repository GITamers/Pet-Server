package net.gitpet.petserver.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private Long uid;

    @Column(name = "POINT", nullable = false)
    private Long point;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createAt;

    @Column(name = "COMMIT_UPDATED_AT")
    private LocalDateTime commitUpdatedAt;

    @Column(name = "COMMIT_CNT", nullable = false)
    private Long commitCnt;
}
