package com.br.recode.gui;

import java.io.IOException;
import java.util.Scanner;

import com.br.recode.controlador.Controlador;
import com.br.recode.entidades.Conta;

public class ContaGui {
	
	private Controlador controlador;
			
	public ContaGui() {
		controlador = new Controlador();		
	}
	
	Scanner scan = new Scanner(System.in);
	
	public void iniciar() throws IOException {
		
		int codigo;
				
		do {
					
			System.out.println(Menu.lerMenu());
			System.out.println("Digite o cógido da ação desejada:");
			codigo = scan.nextInt();
			
			switch (codigo) {
			case 1:
				String cpf = lerCPF();
				String nome = lerNome();
				String senha = lerSenha();
		    	controlador.abrirConta(cpf, nome, senha);
		    	System.out.println("Conta aberta com sucesso!");
				break;
			case 2:
				int numero = informarNumeroConta();
				//Double valor = controlador.consultarSaldo(numero);
				Conta conta = controlador.buscarConta(numero);
				if(conta != null)
					System.out.println("O saldo da conta é :" + conta.getSaldo());
				else
					System.out.println("Número da conta inválida");		
				break;
			case 3:
				numero = informarNumeroConta();
				double valor = informarValor();
				controlador.creditarValor(numero, valor);
				System.out.println("Valor :" + valor + " creditado com sucesso!");
				break;
			case 4:
				numero = informarNumeroConta();
				valor = informarValor();
				senha = lerSenha();
				if(controlador.debitarValor(numero, valor, senha)) {
					System.out.println("Valor :" + valor + " debitado com sucesso");
				}else {
					System.out.println("Senha inválida");
				}		
				break;
			case 5:
				int numero1 = informarNumeroConta();
				valor = informarValor();
				System.out.println("Digite o número da conta de destino");
				int numero2 = scan.nextInt();
				senha = lerSenha();
				if(controlador.transferirValor(numero1, valor, numero2, senha)) {
					System.out.println("tranferência realizada com sucesso");
				}else {
					System.out.println("Senha inválida");
				}				
				break;
			case 6:
				cpf = lerCPF();
				System.out.println(controlador.listarContasCliente(cpf));
				break;
			case 7:
				System.out.println(controlador.listarClientes());
				break;
			case 8:
				cpf = lerCPF();
				nome = lerNome();
				controlador.atualizarCliente(cpf, nome);
		    	System.out.println("Cliente atualizado com sucesso!");
				break;
			case 9:
				System.out.println("Aguarde implementação");
				break;
			default:
				break;
			}		   
			
		} while (codigo != 151); 
			System.out.println("Você saiu do sistema. Volte sempre!");	
				
	}	
	
	public Integer informarNumeroConta() {
		System.out.println("Digite o número da sua conta");
		int numeroInformado = scan.nextInt();
		return numeroInformado;
	}

	public Double informarValor() {
		System.out.println("Informe o valor : ");
		double valor = scan.nextDouble();
		return valor;
	}
	
	public Integer tipoConta() {
		System.out.println("Digite [1] para Conta Corrente \n [2] para Poupança \n [3] para Conta Bonificada \n [4] para Conta Salário");
		int numeroInformado = scan.nextInt();
		return numeroInformado;
	}
	
	protected String lerCPF() {
		System.out.println("Informe o CPF:");
		return scan.next();
	}
	
	protected String lerNome() {
		System.out.println("Informe o nome:");
		return scan.next();
	}
	
	protected String lerSenha() {
		System.out.println("Informe a senha:");
		return scan.next();
	}
}
