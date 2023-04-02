package ru.netology.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Credential {
    @Id
    private String login;
    private String password;
}
