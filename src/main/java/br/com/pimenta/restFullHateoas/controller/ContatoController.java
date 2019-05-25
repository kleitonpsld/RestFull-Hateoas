package br.com.pimenta.restFullHateoas.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pimenta.restFullHateoas.entity.Contato;
import br.com.pimenta.restFullHateoas.interfaces.GenericOperationsController;
import br.com.pimenta.restFullHateoas.services.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController implements GenericOperationsController<Contato>{
	
	
	Logger log = LoggerFactory.getLogger(ContatoController.class);

	
	@Autowired
	public ContatoService contatoService;
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Contato> post(@RequestBody Contato entity) {
		

		try {
			contatoService.post(entity);
			log.info("Registro inserido");
			
			Link link = linkTo(Contato.class).slash(entity.getId()).withSelfRel();
			Resource<Contato> result = new Resource<Contato>(entity,link);
			
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método POST.\nMensagem: %s",e.getMessage()));
		}
			
		return null;
	}


	@Override
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody Contato entity) {
			
			try {
				contatoService.put(entity);
				log.info(String.format("Registro atualizado: %s",entity.toString()));
				//Link link = linkTo(RegistroPonto.class).slash(entity.getId()).withSelfRel();
			} catch (Exception e) {
				log.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			}
	}


	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Contato entity) {
		try {
			contatoService.delete(entity);
			log.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método DELETE.\nMensagem: %s",e.getMessage()));
		}
		
	}


	@Override
	@GetMapping(value = "/{get}/",produces = {MediaType.APPLICATION_JSON_VALUE,
											  MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Contato> get() {
		List<Contato> allRegistros = new ArrayList<Contato>();
		allRegistros.addAll(contatoService.get());
		List<Contato> all = new ArrayList<Contato>();
		try {
			for(Contato registro : allRegistros) {
				String registroId = String.valueOf(registro.getIdContato());
				Link selfLink = linkTo(Contato.class).slash(registroId).withSelfRel();
				registro.add(selfLink);
				all.add(registro);
			}
			
			Link link = linkTo(Contato.class).withSelfRel();
			Resources<Contato> result = new Resources<Contato>(all,link);
			log.info(String.format("Registro(s) recuperados(s): %s",all.toString()));
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}
	
	@Override
	@GetMapping(value = "/get/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,
											MediaType.APPLICATION_XML_VALUE,
											MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Contato> get(@PathVariable Long id) {
		
		try {
			Contato registro = contatoService.get(id);
			  
			Link link = linkTo(Contato.class).slash(registro).withSelfRel();
			Resource<Contato> result = new Resource<Contato>(registro, link);
			log.info(String.format("Registro recuperado: %s",result.toString()));
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}


	@Override
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Contato entity) {
		
		try {
			contatoService.patch(entity);
			log.info(String.format("Registro atualizado: %s",entity.toString()));
			Link link = linkTo(Contato.class).slash(entity.getId()).withSelfRel();
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
		}
		
	}
}
