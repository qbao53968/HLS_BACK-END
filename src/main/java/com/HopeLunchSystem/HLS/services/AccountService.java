package com.HopeLunchSystem.HLS.services;

import com.HopeLunchSystem.HLS.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccount(String username);
}
