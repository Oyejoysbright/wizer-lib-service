package org.wizer.library.models;

import lombok.*;

@Getter
@AllArgsConstructor
public class Pagination {
    private long totalElement;
    private Object elements;
}
