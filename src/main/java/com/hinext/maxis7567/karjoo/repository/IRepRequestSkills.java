package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.RequestSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IRepRequestSkills extends JpaRepository<RequestSkills, Integer> {
    List<RequestSkills> findAllBySkillsId(Integer id,Pageable pageable);
    List<RequestSkills> findAllByRequestId(Integer id);
}