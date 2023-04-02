package ru.netology.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloud.entity.FileEntity;
import ru.netology.cloud.model.FileInfo;
import ru.netology.cloud.model.FileName;
import ru.netology.cloud.service.FileService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class CloudController {
    private static final String FILE_ENDPOINT = "file";
    private static final String LIST_ENDPOINT = "list";
    private FileService fileService;

    @PostMapping(path = FILE_ENDPOINT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> upload(
        @RequestHeader(name = "auth-token") String authToken,
        @RequestParam String filename,
        @RequestBody MultipartFile file
    ) throws IOException {
        fileService.saveFile(authToken, filename, file);
        return ResponseEntity.ok("Success upload");
    }

    @DeleteMapping(path = FILE_ENDPOINT)
    public ResponseEntity<String> delete(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filename
    ) {
        fileService.deleteFile(authToken, filename);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = FILE_ENDPOINT)
    public ResponseEntity<byte[]> download(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filename
    ) {
        FileEntity fileEntity = fileService.getFile(authToken, filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFilename() + "\"")
            .contentType(MediaType.valueOf(fileEntity.getContentType()))
            .body(fileEntity.getData());
    }

    @PutMapping(path = FILE_ENDPOINT)
    public ResponseEntity<String> edit(
        @RequestHeader(name = "auth-token") String authToken,
        @RequestParam (name = "filename") String filenameOld,
        @RequestBody FileName filenameNew
    ) {
        fileService.updateFilename(authToken, filenameOld, filenameNew.getName());
        return ResponseEntity.ok("Success upload");
    }

    @GetMapping(path = LIST_ENDPOINT)
    public ResponseEntity<List<FileInfo>> getAll(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam int limit
    ) {
        return ResponseEntity.ok(fileService.getFileList(authToken, limit));
    }
}