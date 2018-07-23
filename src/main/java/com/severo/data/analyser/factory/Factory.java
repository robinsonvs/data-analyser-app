package com.severo.data.analyser.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Cliente;
import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.Vendedor;
import com.severo.data.analyser.models.ClienteModel;
import com.severo.data.analyser.models.VendaModel;
import com.severo.data.analyser.models.VendedorModel;
import com.severo.data.analyser.parsers.ClienteParser;
import com.severo.data.analyser.parsers.VendaParser;
import com.severo.data.analyser.parsers.VendedorParser;

@Component
public class Factory {
    public static final int TIPO = 0;
    public static final String VENDEDOR = "001";
    public static final String CLIENTE = "002";
    public static final String VENDA = "003";
    
    @Autowired
    private ClienteParser clienteParser;
    
    @Autowired
    private VendaParser vendaParser;
    
    @Autowired
    private VendedorParser vendedorParser;
    
    @Autowired
    private ClienteModel clienteModel;
    
    @Autowired
    private VendaModel vendaModel;
    
    @Autowired
    private VendedorModel vendedorModel;


    public Factory() {
    	
    }
    
    
    public Factory(
    		ClienteParser clienteParser, 
    		VendaParser vendaParser, 
    		VendedorParser vendedorParser,
			ClienteModel clienteModel, 
			VendaModel vendaModel, 
			VendedorModel vendedorModel) {
		super();
		this.clienteParser = clienteParser;
		this.vendaParser = vendaParser;
		this.vendedorParser = vendedorParser;
		this.clienteModel = clienteModel;
		this.vendaModel = vendaModel;
		this.vendedorModel = vendedorModel;
	}

	/**
     * 
     * @param dataModel
     */
    public void processarParsers(List<String> dataModel){
        switch (dataModel.get(TIPO)) {
		case VENDEDOR:
			vendedorModel.add((Vendedor)vendedorParser.parser(dataModel));
			break;
		case CLIENTE:
			clienteModel.add((Cliente)clienteParser.parser(dataModel));
			break;
		case VENDA:
			vendaModel.add((Venda)vendaParser.parser(dataModel));
			break;			
		default:
			break;
		}
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public VendaModel getVendaModel() {
        return vendaModel;
    }

    public VendedorModel getVendedorModel() {
        return vendedorModel;
    }
}
