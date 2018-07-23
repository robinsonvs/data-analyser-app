package com.severo.data.analyser.parsers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Cliente;
import com.severo.data.analyser.beans.IGenericBean;

@Component
public class ClienteParser implements IParser{

    private final static int TIPO = 0;
    private final static int CNPJ = 1;
    private final static int NOME = 2;
    private final static int AREA = 3;

    @Override
    public IGenericBean parser(List<String> dadosCliente) {
        IGenericBean cliente  = new Cliente(
                dadosCliente.get(TIPO),
                dadosCliente.get(CNPJ),
                dadosCliente.get(NOME),
                dadosCliente.get(AREA));

        return cliente;
    }
}
