package Api.Bank.AccountManagement.repositories;

import Api.Bank.AccountManagement.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
