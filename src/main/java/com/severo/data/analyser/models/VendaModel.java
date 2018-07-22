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

    public void add(Venda venda){
        listVenda.add(venda);
    }

    public List<Venda> getAll(){
        return listVenda;
    }

    public void addItemSale(VendaItem vendaItem, String vendaId){
        listVenda.forEach(venda -> {
            if(venda.getId().equals(vendaId)){
            	venda.getVendaItem().add(vendaItem);
            }
        });
    }


}
