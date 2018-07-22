package com.severo.data.analyser.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Vendedor;

@Component
public class VendedorModel {

    private List<Vendedor> listaVendedor;

    public VendedorModel() {
        this.listaVendedor = new ArrayList<Vendedor>();
    }

    /**
     * 
     * @param newVendedor
     */
    public void add(Vendedor newVendedor){
        boolean isJaExiste = isExisteVendedor(newVendedor);
        if(!isJaExiste){
            listaVendedor.add(newVendedor);
        }
    }

    /**
     * 
     * @param newVendedor
     * @return
     */
    private boolean isExisteVendedor(Vendedor newVendedor) {
        return listaVendedor.stream()
                .anyMatch(oldVendedor -> {
                    if(isNomeJaExiste(newVendedor, oldVendedor) && oldVendedor.getCpf().isEmpty()){
                        if(!newVendedor.getCpf().isEmpty()) {
                            atualizaVendedor(newVendedor, oldVendedor);
                        }
                        return true;
                    }else{
                        return false;
                    }
                });
    }

    /**
     * 
     * @param newVendedor
     * @param oldVendedor
     */
    private void atualizaVendedor(Vendedor newVendedor, Vendedor oldVendedor) {
        oldVendedor.setCpf(newVendedor.getCpf());
        oldVendedor.setSalario(newVendedor.getSalario());
        oldVendedor.setId(newVendedor.getId());
    }

    /**
     * 
     * @return
     */
    public List<Vendedor> getAll(){
        return listaVendedor;
    }

    /**
     * 
     * @param newVendedor
     * @param vendedor
     * @return
     */
    private boolean isNomeJaExiste(Vendedor newVendedor, Vendedor vendedor) {
        return vendedor.getNome().toUpperCase().equals(newVendedor.getNome().toUpperCase());
    }


}
