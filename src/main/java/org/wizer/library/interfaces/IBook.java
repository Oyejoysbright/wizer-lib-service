package org.wizer.library.interfaces;

import org.springframework.http.*;
import org.wizer.library.models.*;
import org.wizer.library.utils.*;

public interface IBook {
    public ResponseEntity<CustomResponse> createCategory(String name);
    public ResponseEntity<CustomResponse> getCategories(int page, int size);
    public ResponseEntity<CustomResponse> updateCategory(String id, String name);
    public ResponseEntity<CustomResponse> deleteCategory(String id);
    public ResponseEntity<CustomResponse> createBook(BookReq payload);
    public ResponseEntity<CustomResponse> getBooks(int page, int size);
    public ResponseEntity<CustomResponse> updateBook(String id, BookUpdateReq payload);
    public ResponseEntity<CustomResponse> deleteBook(String id);
    public ResponseEntity<CustomResponse> addBookToCategory(String bookId, String categoryId);
    public ResponseEntity<CustomResponse> favouriteBook(String userId, String bookId);
    public ResponseEntity<CustomResponse> getFavouriteBooks(String userId, int page, int size);
}
