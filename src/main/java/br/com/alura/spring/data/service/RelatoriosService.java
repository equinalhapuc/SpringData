package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Busca por Nome");
			System.out.println("2 - Busca por Nome, Data de contratacao e salário Maior");
			System.out.println("3 - Busca por data de contratação maior ou igual");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 0:
				system = false;
				break;
				
			case 1:
				buscaFuncionarioNome(scanner);
				break;

			case 2:
				buscaNomeDataSalario(scanner);
				break;
				
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			
			case 4:
//				deletar(scanner);
				break;
				
			default:
				system = false;
				break;
			}
		}
	}

	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Data de contratação");
		String sData = scanner.next();
		LocalDate data = LocalDate.parse(sData, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findByDataContratacaoSuperior(data);
		funcionarios.forEach(System.out::println);
	}

	private void buscaNomeDataSalario(Scanner scanner) {
		System.out.println("Nome:");
		String nome = scanner.next();
		System.out.println("Data de contratação");
		String sData = scanner.next();
		LocalDate data = LocalDate.parse(sData, formatter);
		System.out.println("Salario");
		BigDecimal salario = scanner.nextBigDecimal();
		
		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, data);
		funcionarios.forEach(System.out::println);
		
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome:");
		String nome = scanner.next();
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(System.out::println);
	}
	
}
