package com.br.recode.gui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.br.recode.controlador.Controlador;

public class ContaGui {
	
	public static final int ABRIR_CONTA = 1;
	public static final int CONSULTAR_SALDO = 2;
	public static final int CREDITAR = 3;
	public static final int DEBITAR = 4;
	public static final int TRANSFERIR = 5;
	public static final int LISTAR_CONTAS_CLIENTE = 6;
	public static final int LISTAR_CLIENTES = 7;
	public static final int ATUALIZAR_CLIENTE = 8;
	public static final int INATIVAR_CLIENTE = 9;
	public static final int SAIR = 151;
	private Controlador controlador;
				
	public ContaGui() {
		controlador = new Controlador();		
	}
	
	Scanner scan = new Scanner(System.in);
	
	public void iniciar() throws IOException {
		
		int codigo = 0;
				
		do {
					
			try {
				System.out.println(Menu.lerMenu());
				System.out.println("Digite o cógido da ação desejada:");
				codigo = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Você deve digitar um número.");
				scan.nextLine();
			}
									
			switch (codigo) {
			case ABRIR_CONTA:
				String cpf = lerCPF();
				String nome = lerNome();
				String senha = lerSenha();
				int numero = tipoConta();
		    	controlador.abrirConta(cpf, nome, senha, numero);
		    	System.out.println("Conta aberta com sucesso!");
				break;
			case CONSULTAR_SALDO:
				numero = informarNumeroConta();
				Double valor = controlador.consultarSaldo(numero);
				System.out.println("O saldo da conta é : " + valor);
				break;
			case CREDITAR:
				numero = informarNumeroConta();
				valor = informarValor();
				try {
					controlador.creditarValor(numero, valor);
					System.out.println("Valor :" + valor + " creditado com sucesso!");
					break;
				}catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				
			case DEBITAR:
				numero = informarNumeroConta();
				valor = informarValor();
				senha = lerSenha();
				if(controlador.debitarValor(numero, valor, senha)) {
					System.out.println("Valor :" + valor + " debitado com sucesso");
				}else {
					System.out.println("Senha inválida");
				}		
				break;
			case TRANSFERIR:
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
			case LISTAR_CONTAS_CLIENTE:
				cpf = lerCPF();
				System.out.println(controlador.listarContasCliente(cpf));
				break;
			case LISTAR_CLIENTES:
				System.out.println(controlador.listarClientes());
				break;
			case ATUALIZAR_CLIENTE:
				cpf = lerCPF();
				nome = lerNome();
				controlador.atualizarCliente(cpf, nome);
		    	System.out.println("Cliente atualizado com sucesso!");
				break;
			case INATIVAR_CLIENTE:
				System.out.println("Aguarde implementação");
				break;
			default:
				break;
			}		   
			
		} while (codigo != SAIR); 
			System.out.println("Você saiu do sistema. Volte sempre!");	
				
	}	
	
	public Integer informarNumeroConta() {
		int numeroInformado = 0; 
		try {
			System.out.println("Digite o número da sua conta");
			numeroInformado = scan.nextInt();			
		} catch (InputMismatchException e) {
			System.out.println("Você deve digitar números.");
			scan.nextLine();
			informarNumeroConta();
		}
		return numeroInformado;
	}

	public Double informarValor() {
		double valor = 0;
		try {
			System.out.println("Informe o valor : ");
			valor = scan.nextDouble();			
		}catch (InputMismatchException e) {
			System.out.println("Você deve digitar números.");
			scan.nextLine();
			informarValor();
		}
		return valor;
	}
	
	public Integer tipoConta() {
		int numeroInformado = 0;
		try {
			System.out.println("Digite [1] para Conta Corrente \n [2] para Poupança \n [3] para Conta Bonificada \n [4] para Conta Salário");
			numeroInformado = scan.nextInt();			
		} catch (InputMismatchException e) {
			System.out.println("Você deve digitar números.");
			scan.nextLine();			
		}
		
		boolean isNumeroInvalido = true;
		while(isNumeroInvalido) {
									
			switch (numeroInformado) {
			case 1:
				isNumeroInvalido = false;
				break;
	        case 2:
	        	isNumeroInvalido = false;
				break;
	        case 3:
	        	isNumeroInvalido = false;
				break;
	        case 4:
	        	isNumeroInvalido = false;
				break;		
			default:
				System.out.println("Número inválido!");
				try {
					System.out.println("Digite [1] para Conta Corrente \n [2] para Poupança \n [3] para Conta Bonificada \n [4] para Conta Salário");
					numeroInformado = scan.nextInt();			
				} catch (InputMismatchException e) {
					System.out.println("Você deve digitar números.");
					scan.nextLine();			
				}
				break;
			}				
		}
		return numeroInformado;
	}
	
	protected String lerCPF() {
		System.out.println("Informe o CPF:");
		return scan.next();
	}
	
	protected String lerNome() {
		System.out.println("Informe o nome:");
		scan.nextLine();
		return scan.nextLine();
	}
	
	protected String lerSenha() {
		System.out.println("Informe a senha:");
		return scan.next();
	}	
	
}
