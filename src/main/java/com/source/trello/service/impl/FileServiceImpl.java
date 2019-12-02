package com.source.trello.service.impl;

import com.source.trello.model.FileUpload;
import com.source.trello.repository.FileRepository;
import com.source.trello.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public Iterable<FileUpload> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<FileUpload> findById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public void save(FileUpload fileUpload) {
        fileRepository.save(fileUpload);
    }

    @Override
    public void remove(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public Iterable<FileUpload> findAllByCard_CardId(Long cardId) {
        return fileRepository.findAllByCard_CardId(cardId);
    }
}
