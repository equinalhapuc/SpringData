package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private boolean system = true;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
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
		// TODO Auto-generated method stub
		
	}

	private void visualizar() {
		funcionarioRepository.findAll().forEach(funcionario -> System.out.println(funcionario));
		
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer idFuncionario = scanner.nextInt();
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("CPF: ");
		String cpf = scanner.next();
		System.out.println("Salário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		System.out.println("Data de Contratação: ");
		LocalDate dataContratacao = LocalDate.now();
		cargoRepository.findAll().forEach(cargo -> System.out.println(cargo));
		System.out.println("Id do cargo:");
		Integer idCargo = scanner.nextInt();
		Cargo cargo = cargoRepository.findById(idCargo).get();
		
		Funcionario funcionario = new Funcionario(idFuncionario, nome, cpf, salario, dataContratacao, cargo);
		
		funcionarioRepository.save(funcionario);
		
	}

	private void salvar(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("CPF: ");
		String cpf = scanner.next();
		System.out.println("Salário: ");
		BigDecimal salario = scanner.nextBigDecimal();
		System.out.println("Data de Contratação: ");
		LocalDate dataContratacao = LocalDate.now();
		cargoRepository.findAll().forEach(cargo -> System.out.println(cargo));
		System.out.println("Id do cargo:");
		Integer idCargo = scanner.nextInt();
		Cargo cargo = cargoRepository.findById(idCargo).get();
		
		Funcionario funcionario = new Funcionario(nome, cpf, salario, dataContratacao, cargo);
		
		funcionarioRepository.save(funcionario);
		
	}
}
