package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepFile extends JpaRepository<Files, Integer> {
    List<Files> findAllByRequestId(int requestId);
    List<Files> findAllByOfferId(int offerId);
}