package com.example.demo.Item;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemEntity toEntity(ItemDto itemDto);
    ItemDto toDto(ItemEntity itemEntity);
}
