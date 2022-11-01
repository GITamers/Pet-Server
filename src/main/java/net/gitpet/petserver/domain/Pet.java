package net.gitpet.petserver.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PID")
    private Long pid;

    @Column(name = "IMG_SRC", nullable = false)
    private String imgSrc;
}
