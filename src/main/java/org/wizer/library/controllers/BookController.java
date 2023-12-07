package org.wizer.library.controllers;

import io.swagger.annotations.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.wizer.library.models.*;
import org.wizer.library.services.*;
import org.wizer.library.utils.*;

import javax.validation.*;
import javax.validation.constraints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Api(tags = {"Book Management"}, description = "These endpoints manages books related operations")
@Validated
public class BookController {
    private final BookService service;

    @PostMapping("/category")
    public ResponseEntity<CustomResponse> createCategory(
            @RequestParam(required = true) @NotEmpty @NotBlank String name
    ) {
        return service.createCategory(name);
    }

    @GetMapping("/categories")
    public ResponseEntity<CustomResponse> fetchBookCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getCategories(page, size);
    }

    @PutMapping("/category")
    public ResponseEntity<CustomResponse> updateBookCategory(
            @RequestParam(required = true) @NotEmpty @NotBlank String newName,
            @RequestParam(required = true) @NotEmpty @NotBlank String id
    ) {
        return service.updateCategory(id, newName);
    }

    @DeleteMapping("/category")
    public ResponseEntity<CustomResponse> deleteBookCategory(
            @RequestParam(required = true) @NotEmpty @NotBlank String id) {
        return service.deleteCategory(id);
    }
    @PostMapping
    public ResponseEntity<CustomResponse> createNewBook(
            @RequestBody @Valid BookReq payload
            ) {
        return service.createBook(payload);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> fetchBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getBooks(page, size);
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateBook(
            @RequestBody @Valid BookUpdateReq payload,
            @RequestParam(required = true) @NotEmpty @NotBlank String id
    ) {
        return service.updateBook(id, payload);
    }

    @DeleteMapping
    public ResponseEntity<CustomResponse> deleteBook(
            @RequestParam(required = true) @NotEmpty @NotBlank String id) {
        return service.deleteBook(id);
    }

    @PostMapping("/category/add")
    public ResponseEntity<CustomResponse> addBookToCategory(
            @RequestParam(required = true) @NotEmpty @NotBlank String bookId,
            @RequestParam(required = true) @NotEmpty @NotBlank String categoryId
            ) {
        return service.addBookToCategory(bookId, categoryId);
    }

    @PostMapping("/favourite-book")
    public ResponseEntity<CustomResponse> addBookToFavouriteList(
            @RequestParam(required = true) @NotEmpty @NotBlank String userId,
            @RequestParam(required = true) @NotEmpty @NotBlank String bookId
            ) {
        return service.favouriteBook(userId, bookId);
    }

    @GetMapping("/favourite-books")
    public ResponseEntity<CustomResponse> fetchFavouriteBooks(
            @RequestParam @NotEmpty @NotBlank String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getFavouriteBooks(userId, page, size);
    }

}
