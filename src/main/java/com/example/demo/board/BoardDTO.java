package com.example.demo.board;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private String id;
    private String title;
    private String writer;
}
