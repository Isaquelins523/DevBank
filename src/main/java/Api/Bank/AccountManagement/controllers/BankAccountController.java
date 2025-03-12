package Api.Bank.AccountManagement.controllers;

import Api.Bank.AccountManagement.domain.BankAccount;
import Api.Bank.AccountManagement.domain.Client;
import Api.Bank.AccountManagement.repositories.BankAccountRepository;
import Api.Bank.AccountManagement.repositories.ClientRepository;
import Api.Bank.AccountManagement.requests.BankAccountRequest;
import Api.Bank.AccountManagement.services.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    private final ClientRepository clientRepository;

    private final BankAccountRepository repository;

    private final BankAccountService service;

    public BankAccountController(ClientRepository clientRepository, BankAccountRepository repository, BankAccountService service) {
        this.clientRepository = clientRepository;
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody @Valid BankAccountRequest data){
        Optional<Client> optionalClient = clientRepository.findById(data.clientId());

        if (optionalClient.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Client client = optionalClient.get();

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setNumberAccount(UUID.randomUUID().toString());
        newBankAccount.setBalance(BigDecimal.ZERO);
        newBankAccount.setTypeAccount(data.accountType());
        newBankAccount.setClient(client);

        BankAccount savedAccount = repository.save(newBankAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity getAccountByClientId(@PathVariable String clientId){
        return service.getAccountByClientId(clientId)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity getAccountByAccountId(@PathVariable String accountId){
        var findAccount = service.getAccountByAccountId(accountId);

        if (findAccount.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        BankAccount account = findAccount.get();
        Map<String, Object> response = new HashMap<>();
        response.put("accountId", account.getId());
        response.put("balance",account.getBalance());

        return ResponseEntity.ok(response);

    }

}
