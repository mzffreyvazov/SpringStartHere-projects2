package org.example.service;

import org.example.exception.InvalidTransferException;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;


    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transfer(long idSender, long idReceiver, BigDecimal amount) {
        if (idSender == idReceiver) {
            throw new InvalidTransferException("Cannot transfer money to the same account");
        }
        Account sender = accountRepository.getAccountById(idSender);
        Account receiver = accountRepository.getAccountById(idReceiver);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender,senderNewAmount);
        accountRepository.changeAmount(idReceiver,receiverNewAmount);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }
}
