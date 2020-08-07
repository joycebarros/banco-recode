package com.br.recode.entidades;

public class Poupanca extends ContaCorrente {
	
	private static final double TAXA_JUROS = 0.1;
	
	public void renderJuros() {
		double saldoAtual = getSaldo() + (getSaldo() * TAXA_JUROS);
		setSaldo(saldoAtual);
		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
