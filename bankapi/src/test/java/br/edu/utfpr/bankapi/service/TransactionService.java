package br.edu.utfpr.bankapi.service;

import java.util.Optional;

import br.edu.utfpr.bankapi.model.Account;
import br.edu.utfpr.bankapi.repository.AccountRepository;

public class TransactionService {

    private AccountRepository accountRepository;



   public TransactionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

public String withdraw(Long accountId, Double amount) {
    Optional<Account> optionalAccount = accountRepository.findById(accountId);
    if (optionalAccount.isEmpty()) {
        return "Conta não encontrada";
    }
    Account account = optionalAccount.get();
    if (account.getBalance() < amount) {
        return "Saldo induficiente";
    }
    account.setBalance(account.getBalance() - amount);
    accountRepository.save(account);
    return "Retirada bem sucedida";
}

public String transfer(Long fromAccountId, Long toAccountId, Double amount) {
    Optional<Account> fromAccount = accountRepository.findById(fromAccountId);
    Optional<Account> toAccount = accountRepository.findById(toAccountId);
    if (fromAccount.isEmpty() || toAccount.isEmpty()) {
        return "Conta não encontrada";
    }
    if (fromAccount.get().getBalance() < amount) {
        return "Saldo insuficiente";
    }
    fromAccount.get().setBalance(fromAccount.get().getBalance() - amount);
    toAccount.get().setBalance(toAccount.get().getBalance() + amount);
    accountRepository.save(fromAccount.get());
    accountRepository.save(toAccount.get());
    return "Transferência bem sucedida";
}
}
