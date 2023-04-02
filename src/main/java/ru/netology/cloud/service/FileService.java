package ru.netology.cloud.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloud.entity.FileEntity;
import ru.netology.cloud.exception.ErrorDeleteFile;
import ru.netology.cloud.exception.ErrorInputData;
import ru.netology.cloud.model.FileInfo;
import ru.netology.cloud.repository.FileRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;

    @Transactional
    public void saveFile(String token, String filename, MultipartFile file) throws IOException {
//        String login = UserService.getUserName(token);
        String login = "dee";
        if (fileRepository.findByFilenameAndLogin(filename, login).isPresent()) {
            throw new ErrorInputData(
                ("Please delete firstly the existed file with the name " + filename +
                    " before loading your new file with the same name"));
        }
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(filename);
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setLogin(login);
        fileRepository.save(fileEntity);
    }

    @Transactional
    public FileEntity getFile(String token, String filename) {
//        String userName = UserService.getUserName(token);
        String login = "dee";
        Optional<FileEntity> fileEntityOptional = fileRepository.findByFilenameAndLogin(filename, login);
        if (!fileEntityOptional.isPresent()) {
            throw new ErrorInputData("the file " + filename + " does not exist");
        }
        return fileEntityOptional.get();
    }

    @Transactional
    public void deleteFile(String token, String filename) {
//        String userName = UserService.getUserName(token);
        String login = "dee";
        if (!fileRepository.findByFilenameAndLogin(filename, login).isPresent()) {
            throw new ErrorInputData("the file " + filename + " does not exist");
        }
        Long res = fileRepository.deleteByLoginAndFilename(login, filename);
        if (res <= 0) {
            throw new ErrorDeleteFile("Error on deleting of" + filename);
        }
    }

    @Transactional
    public void updateFilename(String token, String filenameOld, String filenameNew) {
//        String userName = UserService.getUserName(token);
        String login = "dee";
        fileRepository.updateFilename(filenameNew, login, filenameOld);
    }

    @Transactional
    public List<FileInfo> getFileList(String token, int limit) {
//        String userName = UserService.getUserName(token);
        String login = "dee";
        Pageable topN = PageRequest.of(0, limit);
        return fileRepository.findByLoginOrderByFilename(login, topN)
            .stream().map(it -> new FileInfo(it.getFilename(), it.getSize().intValue())).collect(Collectors.toList());
    }
}
