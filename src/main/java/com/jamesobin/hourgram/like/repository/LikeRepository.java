package com.jamesobin.hourgram.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hourgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {

}
