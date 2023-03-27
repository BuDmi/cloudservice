package ru.netology.moneytransfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class FileData {
    private String hash;
//    private MultipartFile file; TODO
}
