package br.com.alura.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dataContratacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionario_unidade", joinColumns = {@JoinColumn(name = "fk_funcionario")}, inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
	private List<UnidadeDeTrabalho> unidadeTrabalhos;

	public List<UnidadeDeTrabalho> getUnidadeTrabalhos() {
		return unidadeTrabalhos;
	}

	public void setUnidadeTrabalhos(List<UnidadeDeTrabalho> unidadeTrabalhos) {
		this.unidadeTrabalhos = unidadeTrabalhos;
	}

	public Funcionario(String nome, String cpf, BigDecimal salario, LocalDate dataContratacao, Cargo cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
		this.cargo = cargo;
	}

	public Funcionario(Integer idFuncionario, String nome, String cpf, BigDecimal salario, LocalDate dataContratacao,
			Cargo cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
		this.cargo = cargo;
		this.id = idFuncionario;
	}
	

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", CPF=" + cpf + ", salario=" + salario
				+ ", dataContratacao=" + dataContratacao + ", cargo=" + cargo + "]";
	}

}
