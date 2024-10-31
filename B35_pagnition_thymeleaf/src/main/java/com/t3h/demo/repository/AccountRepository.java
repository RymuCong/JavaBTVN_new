package com.t3h.demo.repository;

import com.t3h.demo.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account,Integer> {
    Account findAccountByUsername (String userName);

    Account findAccountByEmail (String email);

    Page <Account> findAll(Pageable pageable);
}
