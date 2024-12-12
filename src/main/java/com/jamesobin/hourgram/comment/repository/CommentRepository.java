package com.jamesobin.hourgram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hourgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
