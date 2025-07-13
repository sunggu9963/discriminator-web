package com.example.demo.board;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardEntity toEntity(BoardDto boardDto);

    @Mapping(source = "user.username ", target = "writer")
    BoardDto toDto(BoardEntity boardEntity);

    BoardRequestDto toDtoRequest(BoardEntity boardEntity);
}
