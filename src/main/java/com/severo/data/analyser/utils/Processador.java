package com.severo.data.analyser.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import org.springframework.stereotype.Component;

import com.severo.data.analyser.factory.Factory;

@Component
public class Processador {

    private static String DIRETORIO_ENTRADA_DADOS = "{0}/data/in" ;
    private static String DIRETORIO_SAIDA_DADOS = "{0}/data/out" ;
    private static final Logger LOGGER = Logger.getLogger(Processador.class.getName());


    public Processador(){
    }


    /**
     * 
     * @param factory
     * @param relatorio
     */
    public void processar(Factory factory, Relatorio relatorio) {
        try {
            List<File> arquivos = buscarArquivosParaProcessamento();
            for(File arquivo : arquivos){
                processarArquivos(factory, arquivo);
                gerarRelatorio(factory, arquivo.getName(), relatorio);
            }
        } catch (IOException e1){
            LOGGER.log(Level.INFO, "Diretório de entrada não encontrado", e1);
        }
    }

    /**
     * 
     * @param factory
     * @param arquivo
     */
    private void processarArquivos(Factory factory, File arquivo) {
        try {
            Files.readAllLines(arquivo.toPath()).forEach(line->{
                factory.processarParsers(Arrays.asList(line.split("ç")));
            });
           arquivo.renameTo(new File(arquivo.getAbsoluteFile().toString()+"[PROCESSADO]"));
        } catch (IOException e2) {
            LOGGER.log(Level.INFO, "Arquivo não disponível", e2);
        }
    }

    /**
     * 
     * @return
     * @throws IOException
     */
    private List<File> buscarArquivosParaProcessamento() throws IOException {
        File diretorioEntrada = new File(getUrl(DIRETORIO_ENTRADA_DADOS));
        if (!diretorioEntrada.exists()) {
            diretorioEntrada.createNewFile();
        }
        return Files.walk(Paths.get(getUrl(DIRETORIO_ENTRADA_DADOS)))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().toLowerCase().endsWith(".dat"))
                .filter(path -> !path.toString().toLowerCase().endsWith("[PROCESSADO]"))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    /**
     * 
     * @param factory
     * @param nomeArquivo
     * @param relatorio
     */
    public void gerarRelatorio(Factory factory, String nomeArquivo, Relatorio relatorio){
        try {
            BufferedWriter writer = null;
            File arquivoSaida = new File(getUrl(DIRETORIO_SAIDA_DADOS)+"/"+nomeArquivo+".done.dat");
            FileWriter fw = new FileWriter(arquivoSaida);
            writer = new BufferedWriter(fw);

            writer.write("Quantidade de Clientes no Arquivo de Entrada : "+String.valueOf(relatorio.getQuantidadeAtualClientesNoArquivo(factory)));
            writer.newLine();
            writer.write("Quantidade de Vendedores no Arquivo de Entrada : "+String.valueOf(relatorio.getQuantidadeTotalVendedoresNoArquivo(factory)));
            writer.newLine();
            writer.write("Id da Venda Mais Cara: "+relatorio.getIdMelhorVenda(factory));
            writer.newLine();
            writer.write("Pior Vendedor : "+relatorio.getPiorVendedor(factory));
            writer.newLine();
            writer.flush();
            writer.close();

        }catch(IOException e){
           LOGGER.log(Level.INFO, "Diretório indisponível.", e);
        }
    }
    
    /**
     * 
     * @param relativeUrl
     * @return
     */
    private String getUrl(String relativeUrl){
        String urlAbsolute = System.getProperty("user.home");
        return MessageFormat.format(relativeUrl, urlAbsolute);
    }    
}
