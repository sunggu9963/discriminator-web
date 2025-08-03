package com.example.demo.board;

import com.example.demo.Item.ItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface BoardMapper {
    BoardEntity toEntity(BoardDto boardDto);

    @Mapping(source = "user.username ", target = "writer")
    BoardDto toDto(BoardEntity boardEntity);

    @Mapping(source = "user.username ", target = "writer")
    BoardRequestDto toDtoRequest(BoardEntity boardEntity);

    @Mapping(source = "user.username ", target = "writer")
    BoardListDto toListDto(BoardEntity boardEntity);
}
