package com.severo.data.analyser.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.VendaItem;
import com.severo.data.analyser.beans.Vendedor;
import com.severo.data.analyser.factory.Factory;

@Component
public class GeradorRelatorio {
	
	private static final Logger LOGGER = Logger.getLogger(GeradorRelatorio.class.getName());

	@Value("${data.analyser.diretorio.saida.dados}")
	private String diretorioSaidaEntradaDados;	

	
	@Autowired
	LeitorArquivos arquivoUtil;
	
    /**
     * 
     * @param factory
     * @param nomeArquivo
     * @param relatorio
     */
    public void processar(Factory factory, String nomeArquivo, GeradorRelatorio relatorio){
        try {
        	
            BufferedWriter writer = null;
            File arquivoSaida = new File(arquivoUtil.getUrl(diretorioSaidaEntradaDados)+"/"+nomeArquivo+".done.dat");
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
     * @param modelFactory
     * @return
     */
    private Long getQuantidadeAtualClientesNoArquivo(Factory modelFactory) {
        return Long.valueOf(modelFactory.getClienteModel().getAll().size());
    }

    /**
     * 
     * @param modelFactory
     * @return
     */
    private Long getQuantidadeTotalVendedoresNoArquivo(Factory modelFactory) {
        return Long.valueOf(modelFactory.getVendedorModel().getAll().size());
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
