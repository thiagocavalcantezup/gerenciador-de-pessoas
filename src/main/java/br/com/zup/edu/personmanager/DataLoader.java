package br.com.zup.edu.personmanager;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.personmanager.model.Pessoa;
import br.com.zup.edu.personmanager.repository.PessoaRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;

    public DataLoader(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Pessoa pessoaMaiorDeIdade = new Pessoa(
            "Thiago", "24009441054", "Thi", LocalDate.of(1990, 01, 01)
        );
        Pessoa pessoaMenorDeIdade = new Pessoa(
            "Enzo", "89696416024", "Zinho", LocalDate.of(2010, 02, 02)
        );

        pessoaRepository.save(pessoaMaiorDeIdade);
        pessoaRepository.save(pessoaMenorDeIdade);
    }

}
