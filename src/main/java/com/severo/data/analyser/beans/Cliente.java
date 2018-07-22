package com.severo.data.analyser.beans;

import java.io.Serializable;

public class Cliente implements Serializable, IGenericBean {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaDeTrabalho() {
		return areaDeTrabalho;
	}

	public void setAreaDeTrabalho(String areaDeTrabalho) {
		this.areaDeTrabalho = areaDeTrabalho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaDeTrabalho == null) ? 0 : areaDeTrabalho.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (areaDeTrabalho == null) {
			if (other.areaDeTrabalho != null)
				return false;
		} else if (!areaDeTrabalho.equals(other.areaDeTrabalho))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [tipo=" + tipo + ", cnpj=" + cnpj + ", nome=" + nome + ", areaDeTrabalho=" + areaDeTrabalho
				+ "]";
	}

    
 
}
