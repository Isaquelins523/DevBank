package Api.Bank.AccountManagement.services;

import Api.Bank.AccountManagement.domain.BankAccount;
import Api.Bank.AccountManagement.repositories.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {
    private final BankAccountRepository repository;

    public TransactionService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount deposit(String accountId, Double amount){
        Optional<BankAccount> optionalBankAccount = repository.findById(accountId);

        if (optionalBankAccount.isEmpty()){
            throw new IllegalArgumentException("Account not found!");
        }
        BankAccount account = optionalBankAccount.get();

        BigDecimal depositAmount = BigDecimal.valueOf(amount);
        account.setBalance(account.getBalance().add(depositAmount));
        return repository.save(account);
    }
}
