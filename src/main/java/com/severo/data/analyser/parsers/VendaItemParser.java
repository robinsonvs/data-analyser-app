package com.severo.data.analyser.parsers;

import java.math.BigDecimal;
import java.util.List;

import com.severo.data.analyser.beans.IGenericBean;
import com.severo.data.analyser.beans.VendaItem;

public class VendaItemParser implements IParser{

    private final static int ID = 0;
    private final static int QUANTIDADE = 1;
    private final static int PRECO = 2;

    @Override
    public IGenericBean parser(List<String> vendaItemDados) {
        IGenericBean vendaItem  = new VendaItem(
                vendaItemDados.get(ID),
                Long.valueOf(vendaItemDados.get(QUANTIDADE)),
                new BigDecimal(vendaItemDados.get(PRECO).replace(",",".")));

        return vendaItem;
    }
}
