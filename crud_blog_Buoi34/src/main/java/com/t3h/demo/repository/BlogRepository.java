package com.t3h.demo.repository;

import com.t3h.demo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository <Blog, Integer> {

    List <Blog> findByauthorId(Integer theId);

    List<Blog> findByBlogNameContaining(String data);
}
