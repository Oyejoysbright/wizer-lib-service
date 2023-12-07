package org.wizer.library.entities;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.*;
import java.time.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE book SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Book implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String isbn;
    private String genre;
    private int numberOfPages;
    private String rating;
    @OneToMany(fetch = FetchType.LAZY)
    private List<BookReview> reviews = new ArrayList<>();
    private boolean available;
    @JsonIgnore
    private boolean deleted = Boolean.FALSE;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
