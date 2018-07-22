package com.severo.data.analyser.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class VendaItem implements Serializable, IGenericBean {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "VendaItem [id=" + id + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}


}
