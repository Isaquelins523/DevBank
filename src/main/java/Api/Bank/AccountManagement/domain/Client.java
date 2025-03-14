package Api.Bank.AccountManagement.domain;

import Api.Bank.AccountManagement.requests.ClientRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "datebirth", nullable = false)
    private LocalDate dateBirth;

    public Client(ClientRequest request) {
        this.id = request.id();
        this.name = request.name();
        this.email = request.email();
        this.cpf = request.cpf();
        this.dateBirth = request.dateBirth();
    }
}
