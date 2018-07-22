package com.severo.data.analyser.utils;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.VendaItem;
import com.severo.data.analyser.beans.Vendedor;
import com.severo.data.analyser.factory.Factory;

@Component
public class Relatorio {

    public Long quantidadeTotalClientes;
    public Long quantidadeAtualClientesNoArquivo;
    public Long quantidadeTotalVendedores;
    public Long quantidadeTotalVendedoresNoArquivo;
    public String piorVendedor;
    public String idDaMelhorVenda;

    public Relatorio() {
        this.quantidadeTotalClientes = 0L;
        this.quantidadeAtualClientesNoArquivo = 0L;
        this.quantidadeTotalVendedores = 0L;
        this.quantidadeTotalVendedoresNoArquivo = 0L;
        this.piorVendedor = "";
        this.idDaMelhorVenda = "";
    }

    /**
     * 
     * @param modelFactory
     * @return
     */
    public Long getQuantidadeAtualClientesNoArquivo(Factory modelFactory) {
        quantidadeAtualClientesNoArquivo = Long.valueOf(modelFactory.getClienteModel().getAll().size())-quantidadeTotalClientes;
        quantidadeTotalClientes = Long.valueOf(modelFactory.getClienteModel().getAll().size());
        return quantidadeAtualClientesNoArquivo;
    }

    /**
     * 
     * @param modelFactory
     * @return
     */
    public Long getQuantidadeTotalVendedoresNoArquivo(Factory modelFactory) {
        quantidadeTotalVendedoresNoArquivo = Long.valueOf(modelFactory.getVendedorModel().getAll().size())-quantidadeTotalVendedores;
        quantidadeTotalVendedores = Long.valueOf(modelFactory.getVendedorModel().getAll().size());
        return quantidadeTotalVendedoresNoArquivo;
    }

    /**
     * 
     * @param modelFactory
     * @return
     */
    public String getPiorVendedor(Factory modelFactory) {
        String piorVendedor="";
        BigDecimal quantidade = new BigDecimal("0.0");
        BigDecimal quantidadeMinima = new BigDecimal("0.0");
        boolean primeiroVendedor = true;

        for(Vendedor vendedor : modelFactory.getVendedorModel().getAll()){
            String nomeVendedor = vendedor.getNome();

            for (Venda venda : modelFactory.getVendaModel().getAll()){
                if(venda.getVendedor().getNome().equals(nomeVendedor)){
                    for(VendaItem vendaItem : venda.getVendaItem()){
                        quantidade = quantidade.add(vendaItem.getPreco().multiply(BigDecimal.valueOf(vendaItem.getQuantidade())));
                    }
                }
            }

            if(primeiroVendedor){
                quantidadeMinima = quantidade;
                piorVendedor= vendedor.getNome();
                primeiroVendedor=false;
            }else{
                if (quantidade.compareTo(quantidadeMinima) == -1){
                    quantidadeMinima = quantidade;
                    piorVendedor= vendedor.getNome();
                }
            }
            quantidade = new BigDecimal(0.0);
        }



        return piorVendedor;
    }

    /**
     * 
     * @param modelFactory
     * @return
     */
    public String getIdMelhorVenda(Factory modelFactory) {
        String idMaiorVenda="";
        BigDecimal quantidade = new BigDecimal("0.0");
        BigDecimal quantidadeMaxima = new BigDecimal("0.0");

        for(Venda venda : modelFactory.getVendaModel().getAll()){
            for (VendaItem vendaItem : venda.getVendaItem()){
                quantidade = quantidade.add((vendaItem.getPreco().multiply(BigDecimal.valueOf(vendaItem.getQuantidade()))));
            }

            if(quantidade.compareTo(quantidadeMaxima) == 1){
                quantidadeMaxima = quantidade;
                idMaiorVenda = venda.getId();
            }
            quantidade = new BigDecimal(0.0);
        }

        return idMaiorVenda;
    }
}
