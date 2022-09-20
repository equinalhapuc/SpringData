package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL"))
			nome = null;
		
		System.out.println("cpf: ");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL"))
			cpf = null;
		
		System.out.println("Salario: ");
		BigDecimal salario = scanner.nextBigDecimal();
		
		if(salario == BigDecimal.ZERO)
			salario = null;
		
		System.out.println("Data de Contratacao: ");
		String data = scanner.next();
		LocalDate dataContratacao;
		
		if(data.equals("NULL"))
			dataContratacao = null;
		else
			dataContratacao = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(SpecificationFuncionario.nome(nome))
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				.or(SpecificationFuncionario.salario(salario)));
		
		funcionarios.forEach(System.out::println);
	}
	
}
