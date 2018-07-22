package com.severo.data.analyser.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.severo.data.analyser.factory.Factory;
import com.severo.data.analyser.utils.Processador;
import com.severo.data.analyser.utils.GeradorRelatorio;

@Component
public class Starter {

	@Autowired
    private Processador processador;
	
	@Autowired
    private Factory factory;
	
	@Autowired
    private GeradorRelatorio relatorio;

    public void executar(){
        processador.processar(factory, relatorio);
    }
}
