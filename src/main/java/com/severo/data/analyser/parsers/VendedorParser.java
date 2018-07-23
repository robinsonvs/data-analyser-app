package com.severo.data.analyser.parsers;

import java.math.BigDecimal;
import java.util.List;

import com.severo.data.analyser.beans.IGenericBean;
import com.severo.data.analyser.beans.Vendedor;

public class VendedorParser implements IParser{

    private final static int TIPO = 0;
    private final static int CPF = 1;
    private final static int NOME = 2;
    private final static int SALARIO = 3;

    @Override
    public IGenericBean parser(List<String> dadosVendedor) {
        IGenericBean vendedor  = new Vendedor(
                dadosVendedor.get(TIPO),
                dadosVendedor.get(CPF),
                dadosVendedor.get(NOME),
                new BigDecimal(dadosVendedor.get(SALARIO).replace(",",".")));

        return vendedor;
    }

    /**
     * 
     * @param nomeVendedor
     * @return
     */
    public IGenericBean extract(String nomeVendedor) {
        IGenericBean vendedor  = new Vendedor(nomeVendedor);
        return vendedor;
    }
}
