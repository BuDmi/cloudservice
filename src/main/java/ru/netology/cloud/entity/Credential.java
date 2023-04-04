package ru.netology.cloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "credential")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Credential implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<FileEntity> fileEntities;
    private String token;
}
