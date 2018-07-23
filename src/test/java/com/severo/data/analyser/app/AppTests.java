package com.severo.data.analyser.app;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.severo.data.analyser.beans.Cliente;
import com.severo.data.analyser.beans.Venda;
import com.severo.data.analyser.beans.VendaItem;
import com.severo.data.analyser.beans.Vendedor;
import com.severo.data.analyser.factory.Factory;
import com.severo.data.analyser.models.ClienteModel;
import com.severo.data.analyser.models.VendaModel;
import com.severo.data.analyser.models.VendedorModel;
import com.severo.data.analyser.parsers.ClienteParser;
import com.severo.data.analyser.parsers.VendaParser;
import com.severo.data.analyser.parsers.VendedorParser;
import com.severo.data.analyser.utils.GeradorRelatorio;

public class AppTests {

	private static final String SEPARADOR = "ç";
	private static final String VENDEDOR_LINHA_ERRO = "001ç1234567891234çDiegoç";
	private static final String VENDEDOR_LINHA_1 = "001ç1234567891234çDiegoç50000";
	private static final String VENDEDOR_LINHA_2 = "001ç3245678865434çRenatoç40000.99";
	private static final String CLIENTE_LINHA_1 = "002ç2345675434544345çJosedaSilvaçRural";
	private static final String CLIENTE_LINHA_2 = "002ç2345675433444345çEduardoPereiraçRural";
	private static final String VENDA_LINHA_1 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
	private static final String VENDA_LINHA_2 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
	
	
	@Ignore
	public void separadorTest() {
		String lineOK = VENDEDOR_LINHA_1;
		String lineERROR = VENDEDOR_LINHA_ERRO;
		VendedorParser vendedorParser = new VendedorParser();
		List<String> lines = Arrays.asList(lineERROR.split(SEPARADOR));
		Vendedor vendedor = (Vendedor) vendedorParser.parser(lines);		
	}	
	
	
	@Test
	public void vendedorParserTest() {		
		List<String> dados = new ArrayList<>();
		VendedorParser vendedorParser = new VendedorParser();
		dados.addAll(Arrays.asList(VENDEDOR_LINHA_1.split(SEPARADOR)));
		Vendedor vendedor = (Vendedor) vendedorParser.parser(dados);
		assertEquals(vendedor.getCpf(), "1234567891234");
		assertEquals(vendedor.getNome(), "Diego");
		assertEquals(vendedor.getSalario(), new BigDecimal("50000"));
		
	}

	
	@Test
	public void clienteParserTest() {		
		List<String> dados = new ArrayList<>();		
		ClienteParser clienteParser = new ClienteParser();
		dados.addAll(Arrays.asList(CLIENTE_LINHA_1.split(SEPARADOR)));
		Cliente cliente = (Cliente) clienteParser.parser(dados);
		assertEquals(cliente.getCnpj(), "2345675434544345");
		assertEquals(cliente.getNome(), "JosedaSilva");
		assertEquals(cliente.getAreaDeTrabalho(), "Rural");
		assertEquals(cliente.getTipo(), "002");
	}
	
	
	@Ignore
	public void vendaParserTest(){
		List<String> dados = new ArrayList<>();		
		VendaParser vendaParser = new VendaParser();
		dados.addAll(Arrays.asList(VENDA_LINHA_1.split(SEPARADOR)));
		Venda venda = (Venda) vendaParser.parser(dados);
		assertEquals(venda.getId(), "10");
		assertEquals(venda.getVendedor().getNome(), "Diego");
		
		VendaItem vendaItem = new VendaItem("1", 10L, new BigDecimal("100"));
		Assert.assertTrue(venda.getVendaItem().contains(vendaItem));
	}
	
	
	@Test
	public void maiorVendaTest(){
		List<String> dadosVenda1 = new ArrayList<>();
		List<String> dadosVenda2 = new ArrayList<>();
		
		VendaParser vendaParser = new VendaParser();

		dadosVenda1.addAll(Arrays.asList(VENDA_LINHA_1.split(SEPARADOR)));
		Venda venda1 = (Venda) vendaParser.parser(dadosVenda1);
		dadosVenda2.addAll(Arrays.asList(VENDA_LINHA_2.split(SEPARADOR)));
		Venda venda2 = (Venda) vendaParser.parser(dadosVenda2);
		
		List<Venda> vendas = new ArrayList<>();
		vendas.add(venda1);
		vendas.add(venda2);
		
		vendas.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
		
		Venda vendaMaisCara = vendas.get(0);
		assertEquals(vendaMaisCara.getId(), "10");
	}

	
	
	
	@Test
	public void piorVendedorTest(){
		List<String> todos = new ArrayList<>();
		
		todos.add(CLIENTE_LINHA_1);
		todos.add(CLIENTE_LINHA_2);
		todos.add(VENDA_LINHA_1);
		todos.add(VENDA_LINHA_2);
		todos.add(VENDEDOR_LINHA_1);
		todos.add(VENDEDOR_LINHA_2);
		
		ClienteParser clienteParser = new ClienteParser();
		VendaParser vendaParser = new VendaParser(); 
		VendedorParser vendedorParser = new VendedorParser(); 
		ClienteModel clienteModel = new ClienteModel();
		VendaModel vendaModel = new VendaModel(); 
		VendedorModel vendedorModel = new VendedorModel();		
		
		Factory factory = new Factory(
				clienteParser, 
				vendaParser, 
				vendedorParser, 
				clienteModel,
				vendaModel,
				vendedorModel);
		
		todos.forEach(line->{
            factory.processarParsers(Arrays.asList(line.split(SEPARADOR)));
        });
		
		GeradorRelatorio geradorRelatorio = new GeradorRelatorio();
		String nome = geradorRelatorio.getPiorVendedor(factory);
		
		assertEquals(nome, "Renato");
	}
	
	
	@Test
	public void idMehorVendaTest(){
		List<String> todos = new ArrayList<>();
		
		todos.add(CLIENTE_LINHA_1);
		todos.add(CLIENTE_LINHA_2);
		todos.add(VENDA_LINHA_1);
		todos.add(VENDA_LINHA_2);
		todos.add(VENDEDOR_LINHA_1);
		todos.add(VENDEDOR_LINHA_2);
		
		ClienteParser clienteParser = new ClienteParser();
		VendaParser vendaParser = new VendaParser(); 
		VendedorParser vendedorParser = new VendedorParser(); 
		ClienteModel clienteModel = new ClienteModel();
		VendaModel vendaModel = new VendaModel(); 
		VendedorModel vendedorModel = new VendedorModel();		
		
		Factory factory = new Factory(
				clienteParser, 
				vendaParser, 
				vendedorParser, 
				clienteModel,
				vendaModel,
				vendedorModel);
		
		todos.forEach(line->{
            factory.processarParsers(Arrays.asList(line.split(SEPARADOR)));
        });
		
		GeradorRelatorio geradorRelatorio = new GeradorRelatorio();
		String idMelhorVenda = geradorRelatorio.getIdMelhorVenda(factory);
		
		assertEquals(idMelhorVenda, "10");
	}	
	

}
