package org.example.controller;

import org.example.exception.InvalidTransferException;
import org.example.model.Account;
import org.example.model.TransferRequest;
import org.example.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            transferService.transfer(
                    transferRequest.getSenderAccountId(),
                    transferRequest.getReceiverAccountId(),
                    transferRequest.getAmount()
            );
            return ResponseEntity.ok().build();
        } catch (InvalidTransferException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer/multiple")
    public ResponseEntity<?> transferMultiple(@RequestBody List<TransferRequest> transferRequests) {
        try {
            transferRequests.forEach(transferRequest -> {
                transferService.transfer(
                        transferRequest.getSenderAccountId(),
                        transferRequest.getReceiverAccountId(),
                        transferRequest.getAmount()
                );
            });
            return ResponseEntity.ok().build();
        } catch (InvalidTransferException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return transferService.getAllAccounts();
    }

}
