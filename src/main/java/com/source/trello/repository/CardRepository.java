package com.source.trello.repository;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import com.source.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {

    List<Card> findAllByListSet_ListIdOrderByOrderNumberAsc(Long listId);

    List<Card> findAllByTitleContainingOrDescriptionContaining(String title, String description);

    List<Card> findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(String title, String description, Long id);

    List<Card> findAllByUserSetCardContainingAndListSet_ListId(User user, Long id);

    List<Card> findAllByColors(String[] colors);

    List<Card> findAllByOrderByOrderNumberAsc();

    List<Card> findAllByColorSetContainsAndListSet_ListId(Color colors, Long id);
}
