package domain.hospede;

import java.time.LocalDate;
import java.time.LocalDateTime;

import domain.nacionalidade.Nacionalidade;

public class Hospede {
	
	private Integer indice;
	private String nome;
	private String sobrenome;
	private String dataNascimento;
	private Nacionalidade nacionalidade;
	private String telefone;
	

	public Hospede(Integer indice,  String nome, String sobrenome, String dataNascimento, Nacionalidade nacionalidade,
			String telefone) {
		super();
		this.indice = indice;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
	}




	public Hospede(DadosCadastroHospede cadastro) {
		
		this(null, cadastro.getNome(), cadastro.getSobrenome(), cadastro.getDataNascimento(), cadastro.getNacionalidade(), 
				cadastro.getTelefone());
	}

	
	public Hospede() {
		super();
		this.indice = null;
		this.nome = null;
		this.sobrenome = null;
		this.dataNascimento = null;
		this.nacionalidade = null;
		this.telefone = null;
	}




	public Integer getIndice() {
		return indice;
	}


	public void setIndice(Integer indice) {
		this.indice = indice;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getSobrenome() {
		return sobrenome;
	}




	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}




	public String getDataNascimento() {
		return dataNascimento;
	}




	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}




	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}




	public String getTelefone() {
		return telefone;
	}




	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}




	@Override
	public String toString() {
 
		return String.format("Hospede: nome %s,  sobrenome %s, telefone %s, "
				+ "data de nascimento %s, nacionalidade %s",
				nome, sobrenome, telefone, dataNascimento,(nacionalidade == null ? null : nacionalidade.getNome()));
	}
	
	
}
