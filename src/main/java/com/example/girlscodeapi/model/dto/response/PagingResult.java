package com.example.girlscodeapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResult<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int pageNumber;
    private boolean isEmpty;
}
