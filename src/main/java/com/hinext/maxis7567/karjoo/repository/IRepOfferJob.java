package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.OfferJob;
import com.hinext.maxis7567.karjoo.entity.RequestSkills;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepOfferJob extends JpaRepository<OfferJob, Integer> {
    List<OfferJob> findAllByOfferId(Integer id, Pageable pageable);
    List<OfferJob> findAllByJobsId(Integer id, Pageable pageable);
    List<OfferJob> findAllByOfferId(Integer id);
}