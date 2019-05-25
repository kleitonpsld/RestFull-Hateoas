package br.com.pimenta.restFullHateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pimenta.restFullHateoas.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
