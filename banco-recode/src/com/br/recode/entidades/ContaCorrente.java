package com.br.recode.entidades;

public class ContaCorrente extends Conta {
	
		
	public double debitar(double valor) {
		if(valor <= getSaldo()) {
			setSaldo(getSaldo() - valor);			
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
