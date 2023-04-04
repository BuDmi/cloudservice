package ru.netology.cloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "black_list_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlackListTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
}
