package org.wizer.library.repos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.wizer.library.entities.*;

import java.util.*;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);
    Optional<Category> findByIdAndBooksId(String categoryId, String bookId);
}
