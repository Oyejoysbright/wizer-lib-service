package org.wizer.library.repos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.wizer.library.entities.*;

@Repository
public interface FavouriteBookRepo extends JpaRepository<FavouriteBook, String> {
}
