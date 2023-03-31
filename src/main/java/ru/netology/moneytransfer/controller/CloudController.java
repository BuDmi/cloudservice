package ru.netology.moneytransfer.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransfer.model.FileData;
import ru.netology.moneytransfer.model.FileInfo;
import ru.netology.moneytransfer.model.FileName;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CloudController {
    private static final String FILE_ENDPOINT = "file";
    private static final String LIST_ENDPOINT = "list";

    @PostMapping(path = FILE_ENDPOINT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> upload(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filename, @RequestBody FileData fileData
    ) {
        // TODO
        return ResponseEntity.ok("Success upload");
    }

    @DeleteMapping(path = FILE_ENDPOINT)
    public ResponseEntity<String> delete(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filename
    ) {
        // TODO
        return ResponseEntity.ok("Success deleted");
    }

    @GetMapping(path = FILE_ENDPOINT)
    public ResponseEntity<FileData> download(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filename
    ) {
        // TODO
        String hash = "hash";
        return ResponseEntity.ok(new FileData(hash));
    }

    @PutMapping(path = FILE_ENDPOINT)
    public ResponseEntity<String> edit(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam String filenameOld, @RequestBody FileName filenameNew
    ) {
        // TODO
        return ResponseEntity.ok("Success upload");
    }

    @GetMapping(path = LIST_ENDPOINT)
    public ResponseEntity<List<FileInfo>> getAll(
        @RequestHeader(name = "auth-token") String authToken, @RequestParam long limit
    ) {
        // TODO
        List<FileInfo> list = new ArrayList<>();
        return ResponseEntity.ok(list);
    }
}