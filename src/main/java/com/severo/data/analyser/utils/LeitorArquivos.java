package com.severo.data.analyser.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.severo.data.analyser.factory.Factory;

@Component
public class LeitorArquivos {

	private static final Logger LOGGER = Logger.getLogger(LeitorArquivos.class.getName());
	
	@Value("${data.analyser.diretorio.entrada.dados}")
	private String diretorioEntradaDados;
	
	@Value("${data.analyser.extensao.arquivo.entrada}")
	private String extensaoArquivoEntrada;

	@Value("${data.analyser.separador.dados}")
	private String separador;
	
	@Value("${data.analyser.caminho.execucao.aplicacao}")
	private String caminhoExecucao;	
	
    /**
     * 
     * @return
     * @throws IOException
     */
    public List<File> buscarArquivosParaProcessamento() throws IOException {
        File diretorioEntrada = new File(getUrl(diretorioEntradaDados));
        if (!diretorioEntrada.exists()) {
            diretorioEntrada.createNewFile();
        }
        return Files.walk(Paths.get(getUrl(diretorioEntradaDados)))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().toLowerCase().endsWith(extensaoArquivoEntrada))
                .filter(path -> !path.toString().toLowerCase().endsWith("[PROCESSADO]"))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }	
    
    
    /**
     * 
     * @param factory
     * @param arquivo
     */
    public void processar(Factory factory, File arquivo) {
        try {
            Files.readAllLines(arquivo.toPath()).forEach(line->{
                factory.processarParsers(Arrays.asList(line.split(separador)));
            });
           arquivo.renameTo(new File(arquivo.getAbsoluteFile().toString()+"[PROCESSADO]"));
        } catch (IOException e2) {
            LOGGER.log(Level.INFO, "Arquivo não disponível", e2);
        }
    }    
    
    /**
     * 
     * @param relativeUrl
     * @return
     */
    public String getUrl(String relativeUrl){
        String urlAbsolute = System.getProperty(caminhoExecucao);
        return MessageFormat.format(relativeUrl, urlAbsolute);
    }    
}
