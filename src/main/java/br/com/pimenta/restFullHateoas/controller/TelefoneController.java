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

import br.com.pimenta.restFullHateoas.entity.Telefone;
import br.com.pimenta.restFullHateoas.interfaces.GenericOperationsController;
import br.com.pimenta.restFullHateoas.services.TelefoneService;

//@RestController
//@RequestMapping("/telefone")
public class TelefoneController implements GenericOperationsController<Telefone>{
	
	
	Logger log = LoggerFactory.getLogger(TelefoneController.class);

	
	@Autowired
	public TelefoneService telefoneService;
	
	@PostMapping(value = "/{post}",consumes = {MediaType.APPLICATION_JSON_VALUE,
			 				MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Telefone> post(@RequestBody Telefone entity) {
			try {
				telefoneService.post(entity);
				log.info("Registro inserido");
				Link link = linkTo(Telefone.class).slash(entity.getIdTelefone()).withSelfRel();
				Resource<Telefone> result = new Resource<Telefone>(entity,link);
				
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
	public void put(@RequestBody Telefone entity) {
			
			try {
				telefoneService.put(entity);
				log.info("Registro atualizado");
				Link link = linkTo(Telefone.class).slash(entity.getIdTelefone()).withSelfRel();
			} catch (Exception e) {
				log.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
			}
	}

	@Override
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Telefone entity) {
		try {
			telefoneService.delete(entity);
			log.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método DELETE.\nMensagem: %s",e.getMessage()));
		}
		
	}


	@Override
	@GetMapping(value = "/{get}",produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Telefone> get() {
		try {
			List<Telefone> allRegistros = telefoneService.get();
			log.info(String.format("Registro(s) recuperados(s): %s",allRegistros.toString()));
			List<Telefone> all = new ArrayList<Telefone>();
			for(Telefone registro : allRegistros) {
				String registroId = String.valueOf(registro.getIdTelefone());
				Link selfLink = linkTo(Telefone.class).slash(registroId).withSelfRel();
				registro.add(selfLink);
				all.add(registro);
			}
			
			Link link = linkTo(Telefone.class).withSelfRel();
			Resources<Telefone> result = new Resources<Telefone>(all,link);
			
			return result;
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	@GetMapping(value = "/{registro}/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE},
				produces = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.APPLICATION_XML_VALUE,
							MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Telefone> get(@PathVariable Long id) {
		
		try {
			Telefone registro = telefoneService.get(id);
			log.info(String.format("Registro recuperado: %s",registro.toString()));  
			Link link = linkTo(Telefone.class).slash(registro).withSelfRel();
			Resource<Telefone> result = new Resource<Telefone>(registro, link);
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
	public void patch(@RequestBody Telefone entity) {
		
		try {
			telefoneService.patch(entity);
			log.info(String.format("Registro atualizado: %s",entity.toString()));
			Link link = linkTo(Telefone.class).slash(entity.getIdTelefone()).withSelfRel();
		} catch (Exception e) {
			log.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
		}
		
	}
}
