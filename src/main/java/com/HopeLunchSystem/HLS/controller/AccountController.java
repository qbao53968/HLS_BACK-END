package com.HopeLunchSystem.HLS.controller;


import com.HopeLunchSystem.HLS.constants.AppConstants;
import com.HopeLunchSystem.HLS.dto.AccountDTO;
import com.HopeLunchSystem.HLS.exceptions.ResponseMessage;
import com.HopeLunchSystem.HLS.repositories.AccountRepository;
import com.HopeLunchSystem.HLS.services.AccountService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/admin/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @GetMapping("getAccounts")
    public ResponseEntity<ResponseMessage<List<AccountDTO>>> getAccount(
            @RequestParam(required = false,value = "username")String username
    ) {

            try{
                List<AccountDTO> accounts = accountService.getAccount(username);
                ResponseMessage<List<AccountDTO>> responseMessage =
                        new ResponseMessage<>(AppConstants.GET_ACCOUNT_SUCCESS, AppConstants.SUCCESS_CODE, accounts);
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            } catch (Exception e){
                 return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseMessage<>(
                                AppConstants.ACCOUNT_NOT_FOUND,
                                AppConstants.NOT_FOUND_CODE));
            }

    }
}
