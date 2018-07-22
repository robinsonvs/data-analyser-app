package com.severo.data.analyser.beans;

import java.io.Serializable;
import java.util.List;

public class Venda implements Serializable, IGenericBean {
    private String tipo;
    private String id;
    private List<VendaItem> vendaItem;
    private Vendedor vendedor;

    /**
     * 
     * @param tipo
     * @param id
     * @param vendaItem
     * @param vendedor
     */
    public Venda(String tipo, String id, List<VendaItem> vendaItem, Vendedor vendedor) {
        this.id = id;
        this.tipo = tipo;
        this.vendaItem = vendaItem;
        this.vendedor = vendedor;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<VendaItem> getVendaItem() {
		return vendaItem;
	}

	public void setVendaItem(List<VendaItem> vendaItem) {
		this.vendaItem = vendaItem;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "Venda [tipo=" + tipo + ", id=" + id + ", vendaItem=" + vendaItem + ", vendedor=" + vendedor + "]";
	}


    
    
}
