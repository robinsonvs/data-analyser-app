package com.severo.data.analyser.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.severo.data.analyser.factory.Factory;
import com.severo.data.analyser.parsers.ClienteParser;
import com.severo.data.analyser.parsers.VendaParser;
import com.severo.data.analyser.parsers.VendedorParser;
import com.severo.data.analyser.starter.Starter;
import com.severo.data.analyser.utils.Processador;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.severo")
public class DataAnalyserApp {

	public static void main(String[] args) {
		SpringApplication.run(DataAnalyserApp.class, args);
	}

	@Bean
	public Starter starter() {
		return new Starter();
	}
	
	@Bean
	public Processador processador() {
		return new Processador();
	}
	
	@Bean
	public Factory factor() {
		return new Factory();
	}
	
	@Bean
	public ClienteParser clienteParser() {
		return new ClienteParser();
	}		
	
	@Bean
	public VendaParser vendasParser() {
		return new VendaParser();
	}
	
	@Bean
	public VendedorParser vendedorParser() {
		return new VendedorParser();
	}	
	
}
