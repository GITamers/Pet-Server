package net.gitpet.petserver.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BASIC_GOTCHA")
public class BasicGotcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GID")
    private Long gid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PID")
    private Pet pet;

    @Column(name = "POSSIBILITY_WEIGHT", nullable = false)
    private int possibilityWeight;

}
