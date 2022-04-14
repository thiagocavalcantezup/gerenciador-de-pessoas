package br.com.zup.edu.personmanager.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.personmanager.model.Pessoa;
import br.com.zup.edu.personmanager.repository.PessoaRepository;

@RestController
@RequestMapping(PessoaController.BASE_URI)
public class PessoaController {

    public final static String BASE_URI = "/pessoas";

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Não existe uma pessoa com o id informado."
                                            )
                                        );

        if (pessoa.idade() >= 18) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Impossível deletar uma pessoa maior de idade."
            );
        } else {
            pessoaRepository.delete(pessoa);

            return ResponseEntity.noContent().build();
        }
    }

}
