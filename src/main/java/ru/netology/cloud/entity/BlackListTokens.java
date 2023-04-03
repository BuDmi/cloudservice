package ru.netology.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "black_list_tokens")
@AllArgsConstructor
@NoArgsConstructor
public class BlackListTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
}
