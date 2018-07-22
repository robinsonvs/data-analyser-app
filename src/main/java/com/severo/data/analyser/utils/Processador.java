package com.severo.data.analyser.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.severo.data.analyser.factory.Factory;

@Component
public class Processador {
	
	private static final Logger LOGGER = Logger.getLogger(Processador.class.getName());

	@Autowired
	private LeitorArquivos leitorArquivos;

	@Autowired
	private GeradorRelatorio geradorRelatorio;
	
    /**
     * 
     * @param factory
     * @param relatorio
     */
    public void processar(Factory factory, GeradorRelatorio relatorio) {
        try {
            List<File> arquivos = leitorArquivos.buscarArquivosParaProcessamento();
            for(File arquivo : arquivos){
            	leitorArquivos.processar(factory, arquivo);
            	geradorRelatorio.processar(factory, arquivo.getName(), relatorio);
            }
        } catch (IOException e1){
            LOGGER.log(Level.INFO, "Diretório de entrada não encontrado", e1);
        }
    }





    
    
}
