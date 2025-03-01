package domain.formaPagamento;

import java.util.Objects;

public class FormaPagamento {

	Integer id;
	String nome;
	
	public FormaPagamento(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	@Override
	public String toString() {
		
		return this.nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}


	

}
