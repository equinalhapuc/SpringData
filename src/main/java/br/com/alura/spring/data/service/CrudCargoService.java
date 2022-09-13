package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	private final CargoRepository cargoRepository;
	private boolean system = true;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Novo Cargo");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 0:
				system = false;
				break;
				
			case 1:
				salvar(scanner);
				break;

			case 2:
				atualizar(scanner);
				break;
				
			case 3:
				visualizar();
				break;
			
			case 4:
				deletar(scanner);
				break;
				
			default:
				system = false;
				break;
			}
		}
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id:");
		Integer id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Registro excluído.");
		
	}

	private void visualizar() {
		System.out.println("Cargos cadastrados:\n");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
		
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id do cargo:");
		Integer id = scanner.nextInt();
		System.out.println("Descrição:");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Registro atualizado com sucesso!");
		
	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Registro salvo com sucesso!");
	}
}
