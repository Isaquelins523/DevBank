package Api.Bank.AccountManagement.services;

import Api.Bank.AccountManagement.domain.BankAccount;
import Api.Bank.AccountManagement.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<BankAccount> getAccountByClientId(String clientId){
        return repository.findByClientId(clientId);
    }

    public Optional<BankAccount> getAccountByAccountId(String accountId){
        return repository.findById(accountId);
    }
}
