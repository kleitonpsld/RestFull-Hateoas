package br.com.pimenta.restFullHateoas.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pimenta.restFullHateoas.entity.Telefone;
import br.com.pimenta.restFullHateoas.repositories.TelefoneRepository;
import br.com.pimenta.restFullHateoas.services.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService {
	Telefone registro = new Telefone();

	Logger logger = LoggerFactory.getLogger(TelefoneServiceImpl.class);

	@Autowired
	public TelefoneRepository repository;

	@Override
	@Transactional
	public Telefone post(Telefone entity) {
		try {
			logger.debug("\tMétodo POST executado.");
			logger.debug("\tMétodo POST invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));

			Telefone rg = repository.save(entity);
			logger.info(String.format("\tValor persistido: %s", entity.toString()));
			return rg;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	public Telefone get(Long id) {
		try {
			logger.debug("\tMétodo GET executado.");
			logger.debug("\tMétodo GET invocado");
			logger.debug(String.format("\tValor recebido: %s", id.toString()));

			registro = repository.getOne(id);
			
			logger.info(String.format("\tValor buscado: %s", registro.toString()));
			return registro;
		} catch (Exception e) {
			logger.error(String.format("Error ao buscar registro. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(Telefone entity) {
		try {
			logger.debug("\tMétodo PUT executado.");
			logger.debug("\tMétodo PUT invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar registro. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(Telefone entity) {
		try {
			logger.debug("\tMétodo DELETE executado.");
			logger.debug("\tMétodo DELETE invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.delete(entity);
			
			logger.info("\tValor entidade deletada");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar. \nMensagem:%s", e.getMessage()));
		}
	}

	@Override
	@Transactional
	public void patch(Telefone entity) {
		try {
			logger.debug("\tMétodo PATCH executado.");
			logger.debug("\tMétodo PATCH invocado");
			logger.debug(String.format("\tValor recebido: %s", entity.toString()));
			
			repository.save(entity);
			
			logger.info(String.format("\tValor alterado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public List<Telefone> post(List<Telefone> entities) {
		try {
			List<Telefone> list = new ArrayList<Telefone>();
			logger.debug("\tMétodo POST LIST executado.");
			logger.debug("\tMétodo POST LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			list =  repository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao persistir a lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

	@Override
	@Transactional
	public void put(List<Telefone> entities) {
		try {
			logger.debug("\tMétodo PUT LIST executado.");
			logger.debug("\tMétodo PUT LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.saveAll(entities);
			logger.info(String.format("\tValor persistido: %s", entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao atualizar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void delete(List<Telefone> entities) {
		try {
			logger.debug("\tMétodo DELETE LIST executado.");
			logger.debug("\tMétodo DELETE LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.deleteAll(entities);
			
			logger.info("\tValor lista deletada: ");
		} catch (Exception e) {
			logger.error(String.format("Error ao deletar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	@Transactional
	public void patch(List<Telefone> entities) {
		try {
			logger.debug("\tMétodo PATCH LIST executado.");
			logger.debug("\tMétodo PATCH LIST invocado");
			logger.debug(String.format("\tValor recebido: %s", entities.toString()));
			
			repository.saveAll(entities);
			logger.info(String.format("\t lista alterada: %s",entities.toString()));
		} catch (Exception e) {
			logger.error(String.format("Error ao alterar a lista. \nMensagem:%s", e.getMessage()));
		}

	}

	@Override
	public List<Telefone> get() {
		try {
			List<Telefone> list = new ArrayList<Telefone>();
			logger.debug("\tMétodo GET LIST executado.");
			logger.debug("\tMétodo GET LIST invocado");
			
			list =  repository.findAll();
			logger.info(String.format("\t lista alterada: %s",list.toString()));
			return list;
		} catch (Exception e) {
			logger.error(String.format("Error ao get lista. \nMensagem:%s", e.getMessage()));
			return null;
		}
	}

}
