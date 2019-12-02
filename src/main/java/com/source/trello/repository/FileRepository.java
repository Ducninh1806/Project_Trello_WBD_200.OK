package com.source.trello.repository;

import com.source.trello.model.FileUpload;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends PagingAndSortingRepository<FileUpload, Long> {

    Iterable<FileUpload> findAllByCard_CardId(Long cardId);

}
