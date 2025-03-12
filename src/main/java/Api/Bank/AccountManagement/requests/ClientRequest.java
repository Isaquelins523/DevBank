package Api.Bank.AccountManagement.requests;

import java.time.LocalDate;

public record ClientRequest(String id, String name,String email, String cpf, LocalDate dateBirth) {
}
