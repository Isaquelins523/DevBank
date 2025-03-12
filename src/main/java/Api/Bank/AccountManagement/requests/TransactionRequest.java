package Api.Bank.AccountManagement.requests;

public record TransactionRequest(String accountId, Double amount) {
}
