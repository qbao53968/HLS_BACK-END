package com.HopeLunchSystem.HLS.repositories;

import com.HopeLunchSystem.HLS.dto.AccountDTO;
import com.HopeLunchSystem.HLS.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    @Query("SELECT a FROM Account a WHERE a.deleteFlag = 0")
    List<Account> getAllAccounts();
}
