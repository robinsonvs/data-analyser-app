package com.severo.data.analyser.beans;

import java.math.BigDecimal;

public class VendaItem implements IGenericBean {
    private String id;
    private Long quantidade;
    private BigDecimal preco;

    /**
     * 
     * @param id
     * @param quantidade
     * @param preco
     */
    public VendaItem(String id, Long quantidade, BigDecimal preco) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
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
	 * @return the quantidade
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


}
