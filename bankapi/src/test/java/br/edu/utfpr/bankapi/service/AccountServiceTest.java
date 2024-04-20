package br.edu.utfpr.bankapi.service;
import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Optional;
import br.edu.utfpr.bankapi.model.Account;
import br.edu.utfpr.bankapi.repository.AccountRepository;
import java.util.ArrayList;
import br.edu.utfpr.bankapi.service.AccountService;
import java.util.List;
import br.edu.utfpr.bankapi.service.AccountService;



public class AccountServiceTest {
    private AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    private AccountService accountService = new AccountService(accountRepository);

    public AccountServiceTest() {
        this.accountService = new AccountService(accountRepository);
    }

    @Test
    public void testGetAll() {
        Account account1 = new Account();
        Account account2 = new Account();
        Mockito.when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

        List<Account> result = accountService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testSave() {
        Account account = new Account();
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.save(account);
        assertEquals(account, result);
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(1L);
        account.setNumber("123456");
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = new Account();
        updatedAccount.setNumber("654321");
        Account result = accountService.update(1L, updatedAccount);
        assertEquals("654321", result.getNumber());
    }
}
