package br.com.pimenta.restFullHateoas.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pimenta.restFullHateoas.entity.Contato;
import br.com.pimenta.restFullHateoas.repositories.ContatoRepository;
import br.com.pimenta.restFullHateoas.services.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService {
	Contato registro = new Contato();

	Logger logger = LoggerFactory.getLogger(ContatoServiceImpl.class);

	@Autowired
	public ContatoRepository contatoRepository;

	@Override
	@Transactional
	public Contato post(Contato entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			Contato rg = contatoRepository.save(entity);

			logger.info(String.format("\tValor persistido: %s", entity.toString()));
			return rg;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	public Contato get(Long id) {
		try {
			logger.debug("\tMétodo GET executado.");
			logger.debug("\tMétodo GET invocado");
			logger.debug(String.format("\tValor recebido: %s", id.toString()));

			registro = contatoRepository.getOne(id);
			
			logger.info(String.format("\tValor buscado: %s", registro.toString()));
			return registro;
		} catch (Exception e) {
			logger.error(String.format("Error ao buscar registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(Contato entity) {
		try {
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			contatoRepository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(Contato entity) {
		try {
			logger.debug("\tMétodo DELETE executado.");
			logger.debug("\tMétodo DELETE invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			contatoRepository.delete(entity);
			
			logger.info("\tValor entidade deletada:");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar. \nMensagem:%s", e.getMessage()));
			
		}
	}

	@Override
	@Transactional
	public void patch(Contato entity) {
		try {
			logger.debug("\tMétodo PATCH executado.");
			logger.debug("\tMétodo PATCH invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			contatoRepository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public List<Contato> post(List<Contato> entities) {
		try {
			List<Contato> list = new ArrayList<Contato>();
			logger.debug("\tMétodo POST LIST executado.");
			logger.debug("\tMétodo POST LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			list =  contatoRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir a lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(List<Contato> entities) {
		try {
			
			logger.debug("\tMétodo PUT LIST executado.");
			logger.debug("\tMétodo PUT LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			contatoRepository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(List<Contato> entities) {
		try {
			logger.debug("\tMétodo DELETE LIST executado.");
			logger.debug("\tMétodo DELETE LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			contatoRepository.deleteAll(entities);
			logger.info("\tValor lista deletada ");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar a lista. \nMensagem:%s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(List<Contato> entities) {
		try {
			logger.debug("\tMétodo PATCH LIST executado.");
			logger.debug("\tMétodo PATCH LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			contatoRepository.saveAll(entities);
			logger.info(String.format("\t lista alterada: %s",entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	public List<Contato> get() {
		try {
			List<Contato> list = new ArrayList<Contato>();
			logger.debug("\tMétodo GET LIST executado.");
			logger.debug("\tMétodo GET LIST invocado");
			
			list = (contatoRepository.findAll());
			logger.info(String.format("\t lista buscada: %s",list.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao get lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

}
