package com.severo.data.analyser.beans;

import java.util.List;

public class Venda implements IGenericBean {
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
	 * @return the vendaItem
	 */
	public List<VendaItem> getVendaItem() {
		return vendaItem;
	}

	/**
	 * @param vendaItem the vendaItem to set
	 */
	public void setVendaItem(List<VendaItem> vendaItem) {
		this.vendaItem = vendaItem;
	}

	/**
	 * @return the vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

    
    
}
