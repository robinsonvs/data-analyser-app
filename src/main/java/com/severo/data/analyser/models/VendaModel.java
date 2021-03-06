package com.severo.data.analyser.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.VendaItem;

@Component
public class VendaModel {
    private List<Venda> listVenda;

    public VendaModel() {
        this.listVenda = new ArrayList<>();
    }

    /**
     * 
     * @param venda
     */
    public void add(Venda venda){
        listVenda.add(venda);
    }

    /**
     * 
     * @return
     */
    public List<Venda> getAll(){
        return listVenda;
    }

    /**
     * 
     * @param vendaItem
     * @param vendaId
     */
    public void addItemSale(VendaItem vendaItem, String vendaId){
        listVenda.forEach(venda -> {
            if(venda.getId().equals(vendaId)){
            	venda.getVendaItem().add(vendaItem);
            }
        });
    }

    public void limparListaAnterior() {
    	listVenda.clear();
    }    

}
