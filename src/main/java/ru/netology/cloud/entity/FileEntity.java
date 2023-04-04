package ru.netology.cloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filename;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;
    @ManyToOne(fetch = FetchType.LAZY)
    private Credential credential;
}
