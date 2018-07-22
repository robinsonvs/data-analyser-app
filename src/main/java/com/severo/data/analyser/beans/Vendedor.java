package com.severo.data.analyser.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class Vendedor implements Serializable, IGenericBean {
    public static final String CODIGO_VENDEDOR = "001";
    public static final String VAZIO = "";
    public static final String SALARIO_INICIAL = "0.0";
    private String id;
    private String cpf;
    private String nome;
    private BigDecimal salario;

    /**
     * 
     * @param id
     * @param cpf
     * @param nome
     * @param salario
     */
    public Vendedor(String id, String cpf, String nome, BigDecimal salario) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    /**
     * 
     * @param nome
     */
    public Vendedor(String nome) {
        this.id = CODIGO_VENDEDOR;
        this.cpf = VAZIO;
        this.nome = nome;
        this.salario = new BigDecimal(SALARIO_INICIAL);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	
	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + "]";
	}

}
