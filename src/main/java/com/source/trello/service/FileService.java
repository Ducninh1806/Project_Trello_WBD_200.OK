package com.source.trello.service;

import com.source.trello.model.FileUpload;

import java.util.Optional;

public interface FileService {

    Iterable<FileUpload> findAll();

    Optional<FileUpload> findById(Long id);

    void save(FileUpload fileUpload);

    void remove(Long id);

    Iterable<FileUpload> findAllByCard_CardId(Long cardId);

}
