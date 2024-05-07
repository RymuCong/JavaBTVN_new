package com.t3h.demo.repository;

import com.t3h.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account,Integer> {
    Account findAccountByUsername (String userName);
}
