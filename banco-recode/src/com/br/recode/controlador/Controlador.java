package com.br.recode.controlador;

import java.util.ArrayList;
import java.util.List;

import com.br.recode.DAO.ContaDAO;
import com.br.recode.entidades.Cliente;
import com.br.recode.entidades.Conta;
import com.br.recode.entidades.ContaBonificada;
import com.br.recode.entidades.ContaCorrente;
import com.br.recode.entidades.ContaSalario;
import com.br.recode.entidades.Poupanca;
import com.br.recode.gui.ContaGui;

public class Controlador {
	
	private ContaDAO contaDAO;
		
	public Controlador() {
		contaDAO = new ContaDAO();		
	}

	int numeroInformado;
	
	public Conta abrirConta(String cpf, String nome, String senha) {
		
		Conta conta = null;
		Cliente cliente = new Cliente(cpf, nome);
		if(!isClienteExistente(cliente)) {
			contaDAO.addClientes(cliente);
		}
		ContaGui contaGui = new ContaGui();
		numeroInformado = contaGui.tipoConta();
		boolean isNumeroInvalido = true;
		while(isNumeroInvalido) {
									
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
				System.out.println("Número Inválido");
				break;
			}
		}
		
		conta.setNumero(gerarNumeroConta());
		conta.setSaldo(0.0);
		conta.setCliente(cliente);
		conta.setSenha(senha);
		contaDAO.addContas(conta);
		
		return conta;
	}
	
	public Conta buscarConta(int numero) {
		Conta conta = null;
		List<Conta> listaContas = contaDAO.listarContas();		
		for (Conta contaAtual : listaContas) {
			if(numero == contaAtual.getNumero()) {
				conta = contaAtual;				
			}
		}
		return conta;
	}
	
	public Integer gerarNumeroConta() {
		return 120 + contaDAO.tamanhoListaContas() +1;
	}
	
//	public Double consultarSaldo(int numero) {
//		Double valor = null;
//		Conta conta = buscarConta(numero);
//		if(conta != null) {
//			valor = conta.getSaldo();			
//		}
//		return valor;
//	}
	
	public void creditarValor(int numero, double valor) {
		
		Conta conta = buscarConta(numero);
		conta.creditar(valor);	
	}
	
	public boolean debitarValor(int numero, double valor, String senha) {
		
		Conta conta = buscarConta(numero);
		
		if(conta.validarSenha(senha)) {
			conta.debitar(valor);	
			return true;
		}else {
			return false;
		}
	}
	
	public boolean transferirValor(int numero1, double valor, int numero2, String senha) {
		Conta conta = buscarConta(numero1);
		Conta conta2 = buscarConta(numero2);
		if(conta.validarSenha(senha)) {
			conta.tranferir(valor, conta2);
			return true;
		}else {
			return false;
		}		
	}
		
	public List<Conta> listarContasCliente(String cpf){
		List<Conta> listaContasCliente = new ArrayList<>();
		List<Conta> listaContas = contaDAO.listarContas();		
		for (Conta contaAtual : listaContas) {
			if(cpf.equals(contaAtual.getCliente().getCpf())) {
				listaContasCliente.add(contaAtual);								
			}
		}
		return listaContasCliente;
	}
	
	public boolean isClienteExistente(Cliente pCliente) {
		boolean isClienteExiste = false;
		List<Cliente> lista = contaDAO.listarClientes();
		for (Cliente cliente : lista) {
			if(cliente.equals(pCliente)) {
				isClienteExiste = true;
			}
		}
		return isClienteExiste;
	}
	
	public List<Cliente> listarClientes(){
		return contaDAO.listarClientes();
	}
	
	public Cliente buscarCliente(String cpf) {
		Cliente clienteAtual = null;
		List<Cliente> listaClientes = contaDAO.listarClientes();		
		for (Cliente cliente : listaClientes) {
			if(cpf.equals(cliente.getCpf())) {
				clienteAtual = cliente;
			}			
		}
		return clienteAtual;
	}

	public void removerClienteLista(String cpf) {
		Cliente cliente = buscarCliente(cpf);
		contaDAO.removerCliente(cliente);
	}
	
	public void atualizarCliente(String cpf, String nome) {
		Cliente cliente = buscarCliente(cpf);
		removerClienteLista(cpf);
		cliente.setCpf(cpf);
		cliente.setNome(nome);	
		contaDAO.addClientes(cliente);
	}
		
}
