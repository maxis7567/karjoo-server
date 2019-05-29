package com.hinext.maxis7567.karjoo.repository;

import com.hinext.maxis7567.karjoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepUser extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String number);
    boolean existsByTokenId(String tokenId);
    User findByPhoneNumber(String number);
    User findByTokenId(String tokenId);
}