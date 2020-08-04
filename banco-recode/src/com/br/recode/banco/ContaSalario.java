package com.br.recode.banco;

public class ContaSalario extends Conta{
	
	private static final double TAXA = 0.02;
	
	@Override
	public double debitar(double valor) {
		if(valor <= getSaldo()) {
			double saldoAtual = getSaldo() - valor - (valor * TAXA);
			setSaldo(saldoAtual);
		} else {
			System.out.println("Saldo insuficiente");
		}
		return getSaldo();
	}

}
