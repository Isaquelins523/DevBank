package Api.Bank.AccountManagement.requests;

public record TransferRequest(String fromAccountId, String toAccountId, Double amount) {
}
