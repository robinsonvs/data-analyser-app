package com.severo.data.analyser.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Cliente;

@Component
public class ClienteModel {
    private List<Cliente> listaCliente;

    public ClienteModel() {
        this.listaCliente = new ArrayList<Cliente>();
    }

    /**
     * 
     * @param newCliente
     */
    public void add(Cliente newCliente){
        boolean isJaExiste = listaCliente.stream().anyMatch(oldCliente -> isNomeJaExiste(newCliente, oldCliente));

        if(!isJaExiste) {
            listaCliente.add(newCliente);
        }
    }

    /**
     * 
     * @param newCustomer
     * @param oldCustomer
     * @return
     */
    private boolean isNomeJaExiste(Cliente newCustomer, Cliente oldCustomer) {
        return oldCustomer.getNome().toUpperCase().equals(newCustomer.getNome().toUpperCase());
    }

    /**
     * 
     * @return
     */
    public List<Cliente> getAll(){
        return listaCliente;
    }
    
    
    public void limparListaAnterior() {
    	listaCliente.clear();
    }
}
