package com.example.demo.board;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardEntity toEntity(BoardDto boardDto);
    BoardDto toDto(BoardEntity boardEntity);
    BoardRequestDto toDtoRequest(BoardEntity boardEntity);
}
