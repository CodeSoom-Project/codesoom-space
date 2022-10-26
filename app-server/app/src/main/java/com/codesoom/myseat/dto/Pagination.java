package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이지네이션 정보
 */
@NoArgsConstructor
@Getter
public class Pagination {
    private int page;
    private int size;
    private int totalPages;

    public Pagination(int page, int size, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
    }
}
