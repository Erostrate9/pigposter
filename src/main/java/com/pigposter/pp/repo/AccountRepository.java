package com.pigposter.pp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pigposter.pp.model.Account;
public interface AccountRepository extends JpaRepository<Account,String>{
    Account findAccountByUsernameAndPassword(String username,String password);
}
