package ru.netology.cloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.cloud.entity.FileEntity;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByLoginOrderByFilename(String login, Pageable topN);
    Optional<FileEntity> findByFilenameAndLogin(String filename, String login);
    Long deleteByLoginAndFilename(String login, String filename);
    @Modifying
    @Query(value = "UPDATE files SET filename = :filenameNew WHERE filename = :filenameOld and login = :login",
        nativeQuery = true)
    void updateFilename(@Param("filenameNew") String filenameNew,
                        @Param("login") String login,
                        @Param("filenameOld") String filenameOld);
}
