package com.example.demo.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    Optional<BoardEntity> findByTitle(String title);
}
