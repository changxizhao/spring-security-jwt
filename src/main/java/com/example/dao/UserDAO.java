package com.example.dao;

import com.example.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author changxizhao
 * @Date 2020/6/15 14:02
 * @Description
 */
@Repository
public interface UserDAO extends JpaRepository<Users,Long> {
}
