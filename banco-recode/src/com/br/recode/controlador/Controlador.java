package com.br.recode.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.br.recode.banco.Cliente;
import com.br.recode.banco.Conta;
import com.br.recode.banco.ContaBonificada;
import com.br.recode.banco.ContaCorrente;
import com.br.recode.banco.ContaSalario;
import com.br.recode.banco.Poupanca;

public class Controlador {

	static List<Conta> listaContas = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	static int numeroInformado;
	
	public static Conta abrirConta() {
		
		Conta conta = null;
		Cliente cliente;
		
		System.out.println("Informe o CPF:");
		String cpf = scan.next();
		System.out.println("Informe o nome:");
		String nome = scan.next();
		cliente = new Cliente(cpf, nome);
		
		System.out.println("Digite [1] para Conta Corrente \n [2] para Poupança \n [3] para Conta Bonificada \n [4] para Conta Salário");
		
		boolean isNumeroInvalido = true;
		while(isNumeroInvalido) {
			numeroInformado = scan.nextInt();
						
			switch (numeroInformado) {
			case 1:
				conta = new ContaCorrente();
				isNumeroInvalido = false;
				break;
	        case 2:
	        	conta = new Poupanca();
	        	isNumeroInvalido = false;
				break;
	        case 3:
	        	conta = new ContaBonificada();
	        	isNumeroInvalido = false;
				break;
	        case 4:
	        	conta = new ContaSalario();
	        	isNumeroInvalido = false;
				break;		
			default:
				System.out.println("Número inválido");
				break;
			}
		}
		conta.setNumero(120 + listaContas.size() + 1);
		conta.setSaldo(0.0);
		conta.setCliente(cliente);
		System.out.println("Digite a sua senha:");
		String senha = scan.next();
		conta.setSenha(senha);
		listaContas.add(conta);

		System.out.println("Conta número " + conta.getNumero() + " aberta com sucesso!");
		return conta;
	}
	
	public static Conta buscarConta() {
		Conta conta = null;
		
		System.out.println("Digite o número da sua conta");
		numeroInformado = scan.nextInt();
				
		for (Conta contaAtual : listaContas) {
			if(numeroInformado == contaAtual.getNumero()) {
				conta = contaAtual;				
			}
		}
		return conta;
	}
	
	public static Double consultarSaldo() {
		
		Conta conta = buscarConta();
		
		if(conta != null) {
			System.out.println("O saldo da conta é :" + conta.getSaldo());
			return conta.getSaldo();			
		} else {
			System.out.println("Conta não encontrada");
			return null;
		}
	}
	
	public static void creditarValor() {
		
		Conta conta = buscarConta();
						
		System.out.println("Informe o valor a ser creditado: ");
		double valor = scan.nextDouble();
		conta.creditar(valor);
		System.out.println("Valor :" + valor + " creditado na conta: " + conta.getNumero());
	
	}
	
	public static void debitarValor() {
		
		Conta conta = buscarConta();
		
		System.out.println("Informe o valor a ser debitado: ");
		double valor = scan.nextDouble();
		System.out.println("Digite a sua senha:");
		String senha = scan.next();
		if(conta.validarSenha(senha)) {
			conta.debitar(valor);
			System.out.println("Valor :" + valor + " debitado na conta: " + conta.getNumero());
		}else {
			System.out.println("Senha inválida");
		}
			
	}
	
	public static void transferirValor() {
		Conta conta = null;
		Conta conta2 = null;
		System.out.println("Informe o número da conta que deseja debitar o valor:");
		numeroInformado = scan.nextInt();
				
		for (Conta contaAtual : listaContas) {
			if(numeroInformado == contaAtual.getNumero()) {
				conta = contaAtual;
			}
		}
		System.out.println("Informe o valor a ser transferido: ");
		double valor = scan.nextDouble();
		System.out.println("Informe o número da conta que deseja creditar o valor:");
		int numeroInformado2 = scan.nextInt();
			
		for (Conta contaDestino : listaContas) {
			if(numeroInformado2 == contaDestino.getNumero()) {
				conta2 = contaDestino;
			}
		}
		
		System.out.println("Digite a sua senha:");
		String senha = scan.next();
		if(conta.validarSenha(senha)) {
			conta.tranferir(valor, conta2);
			System.out.println("Valor :" + valor + " debitado da conta: " 
					+ conta.getNumero() + " e creditado na conta :" + conta2.getNumero());	
		}else {
			System.out.println("Senha inválida");
		}		
		
	}
	
}
