package com.severo.data.analyser.beans;

public class Cliente implements IGenericBean {
    private String tipo;
    private String cnpj;
    private String nome;
    private String areaDeTrabalho;

    /**
     * 
     * @param tipo
     * @param cnpj
     * @param nome
     * @param areaDeTrabalho
     */
    public Cliente(String tipo, String cnpj, String nome, String areaDeTrabalho) {
        this.tipo = tipo;
        this.cnpj = cnpj;
        this.nome = nome;
        this.areaDeTrabalho = areaDeTrabalho;
    }

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	 * @return the areaDeTrabalho
	 */
	public String getAreaDeTrabalho() {
		return areaDeTrabalho;
	}

	/**
	 * @param areaDeTrabalho the areaDeTrabalho to set
	 */
	public void setAreaDeTrabalho(String areaDeTrabalho) {
		this.areaDeTrabalho = areaDeTrabalho;
	}


    
 
}
