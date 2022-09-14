package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {

	private final UnidadeRepository unidadeRepository;
	private boolean system = true;

	public CrudUnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Nova Unidade");
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
		unidadeRepository.deleteById(id);
		System.out.println("Unidade excluída!");
	}

	private void visualizar() {
		unidadeRepository.findAll().forEach(unidade -> System.out.println(unidade));
		
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id:");
		Integer id = scanner.nextInt();
		System.out.println("Descrição da Unidade:");
		String nome = scanner.next();
		System.out.println("Endereço:");
		String endereco = scanner.next();
		UnidadeDeTrabalho unidade = new UnidadeDeTrabalho(nome, endereco);
		unidade.setId(id);
		unidadeRepository.save(unidade);
		System.out.println("Unidade atualizada com sucesso");
	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição da Unidade:");
		String nome = scanner.next();
		System.out.println("Endereço:");
		String endereco = scanner.next();
		UnidadeDeTrabalho unidade = new UnidadeDeTrabalho(nome, endereco);
		unidadeRepository.save(unidade);
		System.out.println("Nova unidade salva com sucesso!");
	}
	
}
