package com.source.trello.controller;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import com.source.trello.service.CardService;
import com.source.trello.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Color>> findAllColor() {
        List<Color> colors = (List<Color>) colorService.findAll();
        if (colors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getIdColor(@PathVariable Long id) {
        Optional<Color> color = colorService.findById(id);
        if (color.isPresent()) {
            return new ResponseEntity<>(color.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        colorService.save(color);
        return new ResponseEntity<>(color, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Long id, @RequestBody Color color) {
        Optional<Color> currentColor = colorService.findById(id);
        if (!currentColor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentColor.get().setColorId(color.getColorId());
        currentColor.get().setCardColorSet(color.getCardColorSet());
        currentColor.get().setColorType(color.getColorType());

        colorService.save(currentColor.get());
        return new ResponseEntity<>(currentColor.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Color> deleteColor(@PathVariable Long id) {
        Optional<Color> color = colorService.findById(id);
        if (color.isPresent()) {
            colorService.remove(id);
            return new ResponseEntity<>(color.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<List<Color>> findByCard(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()) {
            List<Color> colors = colorService.findAllByCardColorSetContaining(card.get());
            return new ResponseEntity<>(colors, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Color>> findColorByType(@RequestParam String colorType) {
        List<Color> colors = colorService.findAllByColorTypeContaining(colorType);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }
}
