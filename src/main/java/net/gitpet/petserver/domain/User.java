package net.gitpet.petserver.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private Long uid;

    @Column(name = "POINT", nullable = false)
    private Long point;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "COMMIT_UPDATED_AT")
    private LocalDateTime commitUpdatedAt;

    @Column(name = "COMMIT_CNT", nullable = false)
    private Long commitCnt;

}
