package com.example.demo.board;

import com.example.demo.Item.ItemDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardRequestDto {
    private Long id;
    private String title;
    private String writer;
    private ItemDto item;
}
