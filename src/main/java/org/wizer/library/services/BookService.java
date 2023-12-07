package org.wizer.library.services;

import lombok.*;
import org.springframework.beans.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.wizer.library.entities.*;
import org.wizer.library.interfaces.*;
import org.wizer.library.models.*;
import org.wizer.library.repos.*;
import org.wizer.library.utils.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService implements IBook {
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final FavouriteBookRepo favouriteBookRepo;

    @Override
    public ResponseEntity<CustomResponse> createCategory(String name) {
        Optional<Category> existing = categoryRepo.findByName(name);
        if (existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.CONFLICT,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.ALREADY_EXIST, null)
            );
        }
        categoryRepo.save(new Category(name));
        return CustomResponse.created(ResponseMessage.CREATED, null);
    }

    @Override
    public ResponseEntity<CustomResponse> getCategories(int page, int size) {
        return CustomResponse.ok(
                ResponseMessage.DATA_FETCHED,
                new Pagination(
                        categoryRepo.count(),
                        categoryRepo.findAll(PageRequest.of(page, size)).getContent()
                )
        );
    }

    @Override
    public ResponseEntity<CustomResponse> updateCategory(String id, String name) {
        Optional<Category> existing = categoryRepo.findById(id);
        if (!existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.NOT_FOUND, null)
            );
        }
        existing.get().setName(name);
        categoryRepo.save(existing.get());
        return CustomResponse.ok(ResponseMessage.UPDATED, existing.get());
    }

    @Override
    public ResponseEntity<CustomResponse> deleteCategory(String id) {
        Optional<Category> existing = categoryRepo.findById(id);
        if (!existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.NOT_FOUND, null)
            );
        }
        categoryRepo.deleteById(id);
        return CustomResponse.ok(ResponseMessage.DELETED, null);
    }

    @Override
    public ResponseEntity<CustomResponse> createBook(BookReq payload) {
        Optional<Book> existing = bookRepo.findByTitleAndAuthor(payload.getTitle(), payload.getPublisher());
        if (existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.CONFLICT,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.ALREADY_EXIST, null)
            );
        }
        Book ins = new Book();
        BeanUtils.copyProperties(payload, ins);
        bookRepo.save(ins);
        return CustomResponse.created(ResponseMessage.CREATED, null);
    }

    @Override
    public ResponseEntity<CustomResponse> getBooks(int page, int size) {
        return CustomResponse.ok(
                ResponseMessage.DATA_FETCHED,
                new Pagination(
                        bookRepo.count(),
                        bookRepo.findAll(PageRequest.of(page, size)).getContent()
                )
        );
    }

    @Override
    public ResponseEntity<CustomResponse> updateBook(String id, BookUpdateReq payload) {
        Optional<Book> existing = bookRepo.findById(id);
        if (!existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.NOT_FOUND, null)
            );
        }
        Book ins = existing.get();
        BeanUtils.copyProperties(payload, ins);
        bookRepo.save(ins);
        return CustomResponse.ok(ResponseMessage.UPDATED, ins);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteBook(String id) {
        Optional<Book> existing = bookRepo.findById(id);
        if (!existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.NOT_FOUND, null)
            );
        }
        bookRepo.deleteById(id);
        return CustomResponse.ok(ResponseMessage.DELETED, null);
    }

    @Override
    public ResponseEntity<CustomResponse> addBookToCategory(String bookId, String categoryId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.BOOK_NOT_FOUND, null)
            );
        }
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (!category.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.CATEGORY_NOT_FOUND, null)
            );
        }
        Optional<Category> existing = categoryRepo.findByIdAndBooksId(categoryId, bookId);
        if (existing.isPresent()) {
            throw new CustomException(
                    HttpStatus.CONFLICT,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.ALREADY_EXIST, null)
            );
        }
        category.get().getBooks().add(book.get());
        categoryRepo.save(category.get());
        return CustomResponse.created(ResponseMessage.CREATED, null);
    }

    @Override
    public ResponseEntity<CustomResponse> favouriteBook(String userId, String bookId) {
//        TODO: Check user existence (User entity isn't covered in this impl)
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new CustomException(
                    HttpStatus.NOT_FOUND,
                    new CustomResponse(Boolean.TRUE, ResponseMessage.BOOK_NOT_FOUND, null)
            );
        }
        favouriteBookRepo.save(new FavouriteBook(userId, bookId));
        return CustomResponse.ok(ResponseMessage.FAVOURITE_BOOK, null);
    }

    @Override
    public ResponseEntity<CustomResponse> getFavouriteBooks(String userId, int page, int size) {
//        TODO: Check user existence (User entity isn't covered in this impl)
        return CustomResponse.ok(
                ResponseMessage.DATA_FETCHED,
                new Pagination(
                        favouriteBookRepo.count(),
                        bookRepo.getFavouriteBooks(userId, PageRequest.of(page, size)).getContent()
                )
        );
    }
}
