package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IRepCity extends JpaRepository<City, Integer> {
}