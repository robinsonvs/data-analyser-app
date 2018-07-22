package com.severo.data.analyser.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.severo.data.analyser.starter.Starter;

@Service
public class DataAnalyserService {

	@Autowired
	private Starter starter;
	
	
	@Scheduled(cron = "${data.analyser.cron.starter}")
	public void carregar() throws IOException {
		starter.executar();
	}	
}
