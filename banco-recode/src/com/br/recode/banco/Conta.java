package com.br.recode.banco;

import org.mindrot.jbcrypt.BCrypt;

public class Conta {

	private int numero;
	private double saldo;
	private Cliente cliente;
	private String senha;

		
	public Conta() {
		
	}
			
	public Conta(int numero, double saldo, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setSenha(String senha) {
		String salt = BCrypt.gensalt();
		this.senha = BCrypt.hashpw(senha, salt);;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void creditar(double valor) {
		saldo = saldo + valor;
	}
	
	public double debitar(double valor) {
		if(valor <= saldo) {
			saldo = saldo - valor;			
		} else {
			System.out.println("Saldo insuficiente");
		}
		return saldo;
	}
	
	public void tranferir (double valor, Conta destino) {
		debitar(valor);
		destino.creditar(valor);
	}
		
	public boolean validarSenha(String senha) {
		return BCrypt.checkpw(senha, this.getSenha());
	}
}
