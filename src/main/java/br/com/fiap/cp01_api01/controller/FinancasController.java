package br.com.fiap.cp01_api01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cp01_api01.repository.FinancaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.fiap.cp01_api01.model.Financa;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financas")
public class FinancasController {

    @Autowired
    private FinancaRepository repository;

    @GetMapping
    public ResponseEntity<List<Financa>> findAll() {
        repository.findAll();
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financa> findById(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Financa> create(@RequestBody Financa financa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(financa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financa> update(@PathVariable Long id, @RequestBody Financa financa) {
        Optional<Financa> optFinanca = repository.findById(id);

        if (optFinanca.isPresent()) {
            financa.setId(id);
            Financa financaAlterado = repository.save(financa);
            return ResponseEntity.ok(financaAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
