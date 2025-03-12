package Api.Bank.AccountManagement.controllers;

import Api.Bank.AccountManagement.domain.Client;
import Api.Bank.AccountManagement.repositories.ClientRepository;
import Api.Bank.AccountManagement.requests.ClientRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;


    @PostMapping
    public ResponseEntity CreateClient(@RequestBody @Valid ClientRequest data){
        Client newClient = new Client(data);
        repository.save(newClient);
        return ResponseEntity.ok("Client created successfully");
    }

    @GetMapping
    public ResponseEntity showAllClients(){
        var allClients = repository.findAll();
        return ResponseEntity.ok(allClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> showClientById(@PathVariable String id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
