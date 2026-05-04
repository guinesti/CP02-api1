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
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cp01_api01.repository.FutebolRepository;

import br.com.fiap.cp01_api01.model.Futebol;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/copa")
public class FutebolController {

    @Autowired
    private FutebolRepository repository;

    @GetMapping
    public ResponseEntity<List<Futebol>> findAll(){
        repository.findAll();
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Futebol> findById(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Futebol> create(@RequestBody Futebol futebol) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(futebol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Futebol> update(@PathVariable Long id, @RequestBody Futebol futebol) {
        Optional<Futebol> optFutebol = repository.findById(id);

        if (optFutebol.isPresent()) {
            futebol.setId(id);
            Futebol futebolAlterado = repository.save(futebol);
            return ResponseEntity.ok(futebolAlterado);
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
