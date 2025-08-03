package com.example.demo.board;

import com.example.demo.Item.ItemDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
    private Long id;
    private String title;
    private String writer;
    private List<ItemDto> items;
}
