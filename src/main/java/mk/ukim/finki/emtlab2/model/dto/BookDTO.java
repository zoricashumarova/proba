package mk.ukim.finki.emtlab2.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlab2.model.enumarations.Category;

@Data
public class BookDTO {

    private String name;
    private Long author;
    private Category category;
    private Integer availableCopies;

    public BookDTO() {
    }


    public BookDTO(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
