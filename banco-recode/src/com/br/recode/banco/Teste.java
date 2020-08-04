package com.br.recode.banco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws IOException {
		Cliente cliente1 = new Cliente("1234", "Jair");
		Conta conta1 = new Conta(123, 100.0, cliente1);
		conta1.setSenha("1234");
		Poupanca poupanca1 = new Poupanca();
		poupanca1.setCliente(cliente1);
		poupanca1.setNumero(124);
		poupanca1.setSaldo(1000);
				
		Cliente cliente2 = new Cliente("5678", "Ana");
		Conta conta2 = new Conta(125, 500, cliente2);
		ContaSalario contaS = new ContaSalario();
		contaS.setCliente(cliente2);
		contaS.setNumero(126);
		contaS.setSaldo(500);
		
		System.out.println(conta1.getSenha());
		System.out.println(conta1.validarSenha("1234"));
		System.out.println("Saldo conta 1 : " + conta1.getSaldo());
		System.out.println("Saldo conta 2 : " + conta2.getSaldo());
		System.out.println("Saldo poupança 1 : " + poupanca1.getSaldo());
		System.out.println("Saldo conta salário : " + contaS.getSaldo());
		
		conta1.creditar(200);
		conta2.tranferir(50.0, conta1);
		poupanca1.renderJuros();
		contaS.debitar(50);
		
		System.out.println("====================");
		System.out.println("Saldo conta 1 : " + conta1.getSaldo());
		System.out.println("Saldo conta 2 : " + conta2.getSaldo());
		System.out.println("Conta de " + conta1.getCliente().getNome());
		System.out.println("Saldo poupança 1 : " + poupanca1.getSaldo());
		System.out.println("Saldo conta salário : " + contaS.getSaldo());
				
		List<Conta> listaContas = new ArrayList<>();
		
		listaContas.add(conta1);
		listaContas.add(poupanca1);
		listaContas.add(conta2);
		listaContas.add(contaS);
		
		Scanner scan = new Scanner(System.in);
		int codigo;
		int numeroInformado;
		int numeroInformado2;
		double valor;
		
		do {
					
			System.out.println(Menu.lerMenu());
			System.out.println("Digite o cógido da ação desejada:");
			codigo = scan.nextInt();
			
			if(codigo == 2) {
				System.out.println("Contas disponíveis");
				for (Conta contaAtual : listaContas) {
					System.out.println("Conta número: " + contaAtual.getNumero());			
				}
				System.out.println("Informe o número da conta que deseja consultar o saldo:");
				numeroInformado = scan.nextInt();
				boolean achouConta = false;
				Conta conta = new Conta();
				for (Conta contaAtual : listaContas) {
					if(numeroInformado == contaAtual.getNumero()) {
						achouConta = true;
						conta = contaAtual;
					}
				}
				if(achouConta) {
					System.out.println("O saldo da conta " + conta.getNumero() + " é : " + conta.getSaldo());
					
				}
				
						
			} else if (codigo == 3) {
				System.out.println("Contas disponíveis");
				for (Conta contaAtual : listaContas) {
					System.out.println("Conta número: " + contaAtual.getNumero());			
				}
				System.out.println("Informe o número da conta que deseja creditar o valor:");
				numeroInformado = scan.nextInt();
				
				Conta conta = new Conta();
				for (Conta contaAtual : listaContas) {
					if(numeroInformado == contaAtual.getNumero()) {
						conta = contaAtual;
					}
				}
				System.out.println("Informe o valor a ser creditado: ");
				valor = scan.nextDouble();
				conta.creditar(valor);
				System.out.println("Valor :" + valor + " creditado na conta: " + conta.getNumero() + " com saldo atual de :" + conta.getSaldo());
			
			} else if (codigo == 4) {
				System.out.println("Contas disponíveis");
				for (Conta contaAtual : listaContas) {
					System.out.println("Conta número: " + contaAtual.getNumero());			
				}
				System.out.println("Informe o número da conta que deseja debitar o valor:");
				numeroInformado = scan.nextInt();
				
				Conta conta = new Conta();
				for (Conta contaAtual : listaContas) {
					if(numeroInformado == contaAtual.getNumero()) {
						conta = contaAtual;
					}
				}
				System.out.println("Informe o valor a ser debitado: ");
				valor = scan.nextDouble();
				conta.debitar(valor);
				System.out.println("Valor :" + valor + " debitado na conta: " + conta.getNumero() + " com saldo atual de :" + conta.getSaldo());
			
			} else if (codigo == 5) {
				System.out.println("Contas disponíveis");
				for (Conta contaAtual : listaContas) {
					System.out.println("Conta número: " + contaAtual.getNumero());			
				}
				System.out.println("Informe o número da conta que deseja debitar o valor:");
				numeroInformado = scan.nextInt();
				
				Conta conta = new Conta();
				for (Conta contaAtual : listaContas) {
					if(numeroInformado == contaAtual.getNumero()) {
						conta = contaAtual;
					}
				}
				System.out.println("Informe o valor a ser transferido: ");
				valor = scan.nextDouble();
				System.out.println("Informe o número da conta que deseja creditar o valor:");
				numeroInformado2 = scan.nextInt();
				
				Conta contaDois = new Conta();
				for (Conta contaDestino : listaContas) {
					if(numeroInformado2 == contaDestino.getNumero()) {
						contaDois = contaDestino;
					}
				}
				conta.tranferir(valor, contaDois);
				System.out.println("Valor :" + valor + " debitado da conta: " 
						+ conta.getNumero() + " com saldo atual de :" + conta.getSaldo() 
						+ " e creditado na conta :" + contaDois.getNumero() + " com saldo atual de: " + contaDois.getSaldo() );
			
			}
			
		} while (codigo != 151); 
			System.out.println("Você saiu do sistema. Volte sempre!");
					
		scan.close();
		
	}
		

}
