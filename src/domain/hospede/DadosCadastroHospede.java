package domain.hospede;

import java.time.LocalDate;

import domain.nacionalidade.Nacionalidade;

public class DadosCadastroHospede{
	
	private String nome; 
	private String sobrenome; 
	private String dataNascimento;
	private Nacionalidade nacionalidade; 
	@Override
	public String toString() {
		return "DadosCadastroHospede [nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento
				+ ", nacionalidade=" + nacionalidade + ", telefone=" + telefone + ", numeroDeReserva=" + numeroDeReserva
				+ "]";
	}

	private String telefone; 
	private Integer numeroDeReserva;
	
	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public Integer getNumeroDeReserva() {
		return numeroDeReserva;
	}

	
	
	public void setNome(String nome) {
		if(nome == null) {
			throw new IllegalArgumentException("Nome não pode ser null");
		}
		else {
			this.nome = nome;
		}
		
	}

	public void setSobrenome(String sobrenome) {
		if(sobrenome == null) {
			throw new IllegalArgumentException("Sobrenome não pode ser null");
		}
		else {
			this.sobrenome = sobrenome;
		}
	}

	public void setDataNascimento(String dataNascimento) {
		if(dataNascimento == null) {
			throw new IllegalArgumentException("Data de nascimento não pode ser null");
		}
		else {
			
			this.dataNascimento = dataNascimento;
		}
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		if(nacionalidade == null) {
			throw new IllegalArgumentException("Nacionalidade não pode ser null");
		}
		else {
			
			this.nacionalidade = nacionalidade;
		}
	}

	public void setTelefone(String telefone) {
		if(telefone == null) {
			throw new IllegalArgumentException("Telefone não pode ser null");
		}
		else {
			this.telefone = telefone;
		}
	}

	public void setNumeroDeReserva(Integer numeroDeReserva) {
		if(numeroDeReserva == null) {
			throw new IllegalArgumentException("Numero de reserva não pode ser null");
		}
		else {
			this.numeroDeReserva = numeroDeReserva;
		}
	}

	public DadosCadastroHospede() {
		
		setNome("");
		setSobrenome("");
		setDataNascimento("");
		setNacionalidade(new Nacionalidade(null, null));
		setTelefone("");
		setNumeroDeReserva(0);
		
	}
	
	public DadosCadastroHospede(String nome, String sobrenome, String dataNascimento, Nacionalidade nacionalidade, String 
			telefone, Integer numeroDeReserva) {
		
		setNome(nome);
		
		setSobrenome(sobrenome);
		
		setDataNascimento(dataNascimento);
		
		setNacionalidade(nacionalidade);
		
		setTelefone(telefone);
		
		setNumeroDeReserva(numeroDeReserva);
	}
}
