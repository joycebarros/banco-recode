package com.br.recode.banco;

public class ContaBonificada extends ContaCorrente {

	public static final double TAXA_BONUS = 0.05;
	
	@Override
	public void creditar(double valor) {
		super.creditar(valor + (valor * TAXA_BONUS));
	}
}
