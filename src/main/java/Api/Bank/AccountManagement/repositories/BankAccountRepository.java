package Api.Bank.AccountManagement.repositories;

import Api.Bank.AccountManagement.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findByClientId(String clientId);
    Optional<BankAccount> findById(String accountId);
}
