package Api.Bank.AccountManagement.controllers;

import Api.Bank.AccountManagement.domain.BankAccount;
import Api.Bank.AccountManagement.repositories.BankAccountRepository;
import Api.Bank.AccountManagement.requests.DepositRequest;
import Api.Bank.AccountManagement.requests.TransactionRequest;
import Api.Bank.AccountManagement.requests.TransferRequest;
import Api.Bank.AccountManagement.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final BankAccountRepository repository;

    private final TransactionService service;

    public TransactionsController(BankAccountRepository repository, TransactionService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/deposit")
    public ResponseEntity addBalance(@RequestBody DepositRequest request){
        BankAccount updatedAccount = service.deposit(request.accountId(), request.amount());

        return  ResponseEntity.ok(updatedAccount);
    }


    @PostMapping("/withdraw")
    @Transactional
    public ResponseEntity withdraw(@RequestBody TransactionRequest request){
        Optional<BankAccount> optionalBankAccount = repository.findById(request.accountId());

        if (optionalBankAccount.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        BankAccount account = optionalBankAccount.get();

        BigDecimal withdrawAmount = BigDecimal.valueOf(request.amount());

        if (account.getBalance().compareTo(withdrawAmount) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(withdrawAmount));
        repository.save(account);

        return ResponseEntity.ok("sake " + withdrawAmount +" successfully carried out.");

    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity transfer(@RequestBody TransferRequest request){
        Optional<BankAccount> fromAccountOpt =repository.findById(request.fromAccountId());
        Optional<BankAccount> toAccountOpt = repository.findById(request.toAccountId());

        if (fromAccountOpt.isEmpty() || toAccountOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Source or destination account not found!");
        }

        BankAccount fromAccount = fromAccountOpt.get();
        BankAccount toAccount = toAccountOpt.get();
        BigDecimal transferAmount = BigDecimal.valueOf(request.amount());

        if (request.amount() <= 0 || request.amount().isNaN()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transfer amount");
        }

        if (fromAccount.getBalance().compareTo(transferAmount) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insufficient balance in the source account ");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));

        repository.save(fromAccount);
        repository.save(toAccount);

        return ResponseEntity.ok("transfer of "+ transferAmount + " done successfully!");
    }
}
