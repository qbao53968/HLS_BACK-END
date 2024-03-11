package com.HopeLunchSystem.HLS.services.impl;

import com.HopeLunchSystem.HLS.constants.AppConstants;
import com.HopeLunchSystem.HLS.dto.AccountDTO;
import com.HopeLunchSystem.HLS.entity.Account;
import com.HopeLunchSystem.HLS.exceptions.ErrorsException;
import com.HopeLunchSystem.HLS.repositories.AccountRepository;
import com.HopeLunchSystem.HLS.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<AccountDTO> getAccount(String username) {
        if(username != null){
            try{
                Optional<Account> accountOptional = accountRepository.findByUsername(username);
                if (accountOptional.isPresent()) {
                    return Collections.singletonList(mapper.map(accountOptional.get(), AccountDTO.class));
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e){
                log.info(e.getMessage());
            }
        } else {
            List<Account> accounts = accountRepository.getAllAccounts();
            return accounts.stream()
                    .map(account -> mapper.map(account, AccountDTO.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();

    }
}
