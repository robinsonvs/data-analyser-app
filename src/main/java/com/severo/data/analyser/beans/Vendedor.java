package com.severo.data.analyser.beans;

import java.math.BigDecimal;

public class Vendedor implements IGenericBean {
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the salario
	 */
	public BigDecimal getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}


}
