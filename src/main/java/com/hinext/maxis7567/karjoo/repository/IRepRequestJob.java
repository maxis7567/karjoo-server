package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.RequestJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IRepRequestJob extends JpaRepository<RequestJob, Integer>{
}