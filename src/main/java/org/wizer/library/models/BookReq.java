package org.wizer.library.models;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class BookReq {
    @NotNull @NotEmpty @NotBlank
    private String title;
    @NotNull @NotEmpty @NotBlank
    private String author;
    @NotNull @NotEmpty @NotBlank
    private String publisher;
    @NotNull @NotEmpty @NotBlank
    private String publicationDate;
    @NotNull @NotEmpty @NotBlank
    private String isbn;
    @NotNull @NotEmpty @NotBlank
    private String genre;
    @Min(1)
    private int numberOfPages;
}
