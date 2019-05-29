package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IRepActive extends JpaRepository<Active, String> {
    boolean existsByNumber(String number);
    Active getByNumber(String number);
}