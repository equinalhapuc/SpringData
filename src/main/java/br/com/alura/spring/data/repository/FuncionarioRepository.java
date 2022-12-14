package br.com.alura.spring.data.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
//public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
// A implementação abaixo permite o uso de paginação
public interface FuncionarioRepository
		extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
	List<Funcionario> findByNome(String nome);

	// List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String
	// nome, BigDecimal salario, LocalDate data);
	// Mesmo método usando jpql
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);

	// Usando Native Query
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findByDataContratacaoSuperior(LocalDate data);

	// Usando projeção
	@Query(value = "select f.id, f.nome, f.salario  from spring_jpa.funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();

}
