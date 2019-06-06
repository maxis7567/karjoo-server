package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepJobs extends JpaRepository<Jobs, Integer> {
    List<Jobs> findAllByNameContains(String q);
}