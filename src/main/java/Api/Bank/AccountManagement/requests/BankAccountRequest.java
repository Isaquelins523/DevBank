package Api.Bank.AccountManagement.requests;

import Api.Bank.AccountManagement.domain.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BankAccountRequest(
        @NotBlank(message = "ClientId cannot be blank")
        String clientId,
        @NotNull(message = "AccountType cannot be null")
        String accountType
) {
}
