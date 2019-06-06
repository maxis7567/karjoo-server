package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepSkills extends JpaRepository<Skills, Integer> {
    List<Skills> findAllByNameContains(String q);
}