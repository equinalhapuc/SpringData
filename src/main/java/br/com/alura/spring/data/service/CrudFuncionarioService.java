package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeRepository unidadeRepository;
	private boolean system = true;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeRepository unidadeRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
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
				visualizar(scanner);
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

	private void visualizar(Scanner scanner) {
		System.out.println("Pagina:");
		Integer pagina = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(pagina, 2, Sort.by(Sort.Direction.DESC, "nome"));
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		System.out.println(funcionarios);
		System.out.println("Pagina atual: " + funcionarios.getNumber());
		System.out.println("Total: " + funcionarios.getTotalElements());
		funcionarios.forEach(System.out::println);
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
		unidadeRepository.findAll().forEach(unidade -> System.out.println(unidade));
		List<UnidadeDeTrabalho> unidades = new ArrayList<UnidadeDeTrabalho>();
		
		boolean continua = true;
		while(continua) {
			System.out.println("Digite o Id de uma uniade ou 0 para sair");
			Integer idUnidade = scanner.nextInt();
			if(idUnidade > 0) {
				UnidadeDeTrabalho unidade = unidadeRepository.findById(idUnidade).get();
				unidades.add(unidade);
			}
			else {
				continua = false;
			}
		}
		
		Funcionario funcionario = new Funcionario(nome, cpf, salario, dataContratacao, cargo);
		funcionario.setUnidadeTrabalhos(unidades);
		funcionario.setId(idFuncionario);
		funcionarioRepository.save(funcionario);
		System.out.println("Funcionario salvo com sucesso!");
		
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
		
		unidadeRepository.findAll().forEach(unidade -> System.out.println(unidade));
		List<UnidadeDeTrabalho> unidades = new ArrayList<UnidadeDeTrabalho>();
		
		boolean continua = true;
		while(continua) {
			System.out.println("Digite o Id de uma uniade ou 0 para sair");
			Integer idUnidade = scanner.nextInt();
			if(idUnidade > 0) {
				UnidadeDeTrabalho unidade = unidadeRepository.findById(idUnidade).get();
				unidades.add(unidade);
			}
			else {
				continua = false;
			}
		}
		
		Funcionario funcionario = new Funcionario(nome, cpf, salario, dataContratacao, cargo);
		funcionario.setUnidadeTrabalhos(unidades);
		funcionarioRepository.save(funcionario);
		System.out.println("Funcionario salvo com sucesso!");
	}
}
