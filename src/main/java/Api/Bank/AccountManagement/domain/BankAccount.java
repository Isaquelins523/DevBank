package Api.Bank.AccountManagement.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "numberaccount", unique = true, nullable = false)
    private String numberAccount;

    private BigDecimal balance;

    @Column(name = "typeaccount", nullable = false)
    private String typeAccount;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Client client;

    @Version
    private Long version;
}