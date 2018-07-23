package com.severo.data.analyser.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.severo.data.analyser.beans.IGenericBean;
import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.VendaItem;
import com.severo.data.analyser.beans.Vendedor;

public class VendaParser implements IParser{

    private final static int TIPO = 0;
    private final static int ID = 1;
    private final static int ITEMS_VENDA = 2;
    private final static int VENDEDOR = 3;
    private final VendaItemParser vendaItemParser;

    public VendaParser() {
        this.vendaItemParser = new VendaItemParser();
    }

    @Override
    public IGenericBean parser(List<String> dadosVenda) {
        List<VendaItem> listaDeItensVenda = new ArrayList<>();
        List<String> listaDadosItensVenda = Arrays.asList(clearDadoItemVenda(dadosVenda).split(","));

        listaDadosItensVenda.forEach(dadosItemVenda->{
            listaDeItensVenda.add((VendaItem)vendaItemParser.parser(Arrays.asList(dadosItemVenda.split("-"))));
        });

        IGenericBean venda  = new Venda(
                dadosVenda.get(TIPO),
                dadosVenda.get(ID),
                listaDeItensVenda,
                new Vendedor(dadosVenda.get(VENDEDOR)));

        return venda;
    }

    /**
     * 
     * @param dadosVenda
     * @return
     */
    private String clearDadoItemVenda(List<String> dadosVenda) {
        return dadosVenda.get(ITEMS_VENDA).replace("[","").replace("]","");
    }
}
