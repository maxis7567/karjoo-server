package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepRequest extends JpaRepository<Request, Integer> {

}