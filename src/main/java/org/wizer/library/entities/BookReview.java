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
public class BookReview {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String bookId;
    private String userId;
    private String rating;
    private String comment;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;
}
