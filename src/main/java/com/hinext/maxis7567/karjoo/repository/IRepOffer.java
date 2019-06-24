package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepOffer extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByUserId(int id);
}