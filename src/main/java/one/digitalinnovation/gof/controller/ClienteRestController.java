package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.dto.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author falvojr
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteService clienteService;


	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<Cliente> clientes = clienteService.buscarTodos();
		List<ClienteDTO> clienteDTOs = clientes.stream()
				.map(cliente -> mapper.map(cliente, ClienteDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(clienteDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(mapper.map(clienteService.buscarPorId(id), ClienteDTO.class));
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> inserir(@RequestBody ClienteDTO clienteDto) {
		Cliente cliente = mapper.map(clienteDto, Cliente.class);
		Cliente clienteSalvo = clienteService.inserir(cliente);
		return ResponseEntity.ok(mapper.map(clienteSalvo, ClienteDTO.class));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
		Cliente cliente = mapper.map(clienteDto, Cliente.class);
		Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(mapper.map(clienteAtualizado, ClienteDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
