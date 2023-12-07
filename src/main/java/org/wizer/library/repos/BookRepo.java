package org.wizer.library.repos;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.wizer.library.entities.*;

import java.util.*;

@Repository
public interface BookRepo extends JpaRepository<Book, String> {
    @Query(value = "SELECT * FROM book AS b WHERE b.id IN (SELECT favourite.book_id FROM favourite_book AS favourite WHERE favourite.user_id = ?)", nativeQuery = true)
    Page<Book> getFavouriteBooks(String userId, Pageable pageable);

    Optional<Book> findByTitleAndAuthor(String title, String author);
}
