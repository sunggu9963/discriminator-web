package com.example.demo.board;

import lombok.*;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class BoardListDto {
        private Long id;
        private String title;
        private String writer;
    }

