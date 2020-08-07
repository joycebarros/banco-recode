package com.br.recode.entidades;

public class ContaSalario extends Conta{
	
	private static final double TAXA = 0.02;
	
	@Override
	public double debitar(double valor) {
		if(valor <= getSaldo()) {
			setSaldo(getSaldo() - valor - (valor * TAXA));
		} else {
			System.out.println("Saldo insuficiente");
		}
		return getSaldo();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
