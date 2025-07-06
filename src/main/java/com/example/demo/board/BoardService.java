package com.example.demo.board;

import com.example.demo.Item.ItemEntity;
import com.example.demo.Item.ItemMapper;
import com.example.demo.User.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final ItemMapper itemMapper;

    public void addItem(BoardRequestDto boardRequestDto, UserEntity userEntity) {
        //title 값이 같을 경우 (기존 게시글을 title로 검색)
        BoardEntity boardEntity = boardRepository.findByTitle(boardRequestDto.getTitle())
                .orElseGet(() -> {
                   BoardEntity newBoardEntity = new BoardEntity();
                   newBoardEntity.setTitle(boardRequestDto.getTitle());
                   newBoardEntity.setUser(userEntity);
                   return newBoardEntity;
                });
        ItemEntity itemEntity = itemMapper.toEntity(boardRequestDto.getItem());
        itemEntity.setBoard(boardEntity);
        boardEntity.getItems().add(itemEntity);

        boardRepository.save(boardEntity);


    }


    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 3;

        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.Direction.DESC, "id"));

        Page<BoardDto> boardDtos = boardEntities.map(board -> boardMapper.toDto(board));
        return boardDtos;
    }

}
