package com.source.trello.controller;

import com.source.trello.model.FileUpload;
import com.source.trello.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileUpload>> findAllFileUpload(){
        List<FileUpload> FileUploads =(List<FileUpload>) fileService.findAll();
        if (FileUploads.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(FileUploads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileUpload> findByIdFileUpload(@PathVariable Long id){
        Optional<FileUpload> fileUpload = fileService.findById(id);
        if (fileUpload.isPresent()){
            return new ResponseEntity<>(fileUpload.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<FileUpload> createFileUpload(@RequestBody FileUpload fileUpload){
        fileService.save(fileUpload);
        return new ResponseEntity<>(fileUpload, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileUpload> updateFileUpload(@PathVariable Long id, @RequestBody FileUpload fileUpload){
        Optional<FileUpload> currentFileUpload = fileService.findById(id);
        if (currentFileUpload.isPresent()){

            currentFileUpload.get().setId(fileUpload.getId());
            currentFileUpload.get().setUrl(fileUpload.getUrl());
            currentFileUpload.get().setCard(fileUpload.getCard());
            currentFileUpload.get().setType(fileUpload.getType());
            currentFileUpload.get().setFileName(fileUpload.getFileName());

            fileService.save(currentFileUpload.get());
            return new ResponseEntity<>(currentFileUpload.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FileUpload> deleteFileUpload(@PathVariable Long id){
        Optional<FileUpload> fileUpload = fileService.findById(id);
        if (fileUpload.isPresent()){
            fileService.remove(id);
            return new ResponseEntity<>(fileUpload.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<List<FileUpload>> findAllByCard(@PathVariable Long id){
        List<FileUpload> fileUploads = (List<FileUpload>) fileService.findAllByCard_CardId(id);
        return new ResponseEntity<>(fileUploads, HttpStatus.OK);
    }
}
