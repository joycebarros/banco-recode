package com.br.recode.banco;

public class Poupanca extends Conta {
	
	private static final double TAXA_JUROS = 0.1;
	
	public void renderJuros() {
		double saldoAtual = getSaldo() + (getSaldo() * TAXA_JUROS);
		setSaldo(saldoAtual);
		
	}
	
	

}
