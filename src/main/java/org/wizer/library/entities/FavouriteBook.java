package org.wizer.library.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE favourite_book SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class FavouriteBook {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String userId;
    private String bookId;
    @JsonIgnore
    private boolean deleted = Boolean.FALSE;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public FavouriteBook(String userId, String bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
