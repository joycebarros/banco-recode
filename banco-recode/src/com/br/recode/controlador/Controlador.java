package com.br.recode.controlador;

import java.util.List;

import com.br.recode.DAO.ContaDAO;
import com.br.recode.entidades.Cliente;
import com.br.recode.entidades.Conta;
import com.br.recode.entidades.ContaBonificada;
import com.br.recode.entidades.ContaCorrente;
import com.br.recode.entidades.ContaSalario;
import com.br.recode.entidades.Poupanca;

public class Controlador {
		
	public static final int CONTA_CORRENTE = 1;
	public static final int POUPANCA = 2;
	public static final int CONTA_BONIFICADA = 3;
	public static final int CONTA_SALARIO = 4;
	private ContaDAO contaDAO;
			
	public Controlador() {
		contaDAO = new ContaDAO();		
	}

	int numeroInformado;
	
	public Conta abrirConta(String cpf, String nome, String senha, int numeroInformado) {
		
		Conta conta = null;
		Cliente cliente = new Cliente(cpf, nome);
		if(!isClienteExistente(cliente)) {
			contaDAO.addClientes(cliente);
		}
		
		if(numeroInformado == CONTA_CORRENTE) {
			conta = new ContaCorrente();
		} else if (numeroInformado == POUPANCA) {
			conta = new Poupanca();
		} else if (numeroInformado == CONTA_BONIFICADA) {
			conta = new ContaBonificada();
		} else if (numeroInformado == CONTA_SALARIO) {
			conta = new ContaSalario();
		}
		
		conta.setNumero(gerarNumeroConta());
		conta.setSaldo(0.0);
		conta.setCliente(cliente);
		conta.setSenha(senha);
		contaDAO.addContas(conta);
		
		return conta;
	}
	
	public Conta buscarConta(int numero) {
		Conta conta = contaDAO.buscarConta(numero);		
		return conta;
	}
	
	public Integer gerarNumeroConta() {
		return 120 + contaDAO.tamanhoListaContas() +1;
	}
	
	public Double consultarSaldo(int numero) {
		Double valor = null;
		Conta conta = buscarConta(numero);
		if(conta != null) {
			valor = conta.getSaldo();			
		}
		return valor;
	}
	
	public void creditarValor(int numero, Double valor) {
		Conta conta = buscarConta(numero);
		if(valor != null) {
			conta.creditar(valor);	
		} else {
			throw new RuntimeException("Valor inválido");
		}
		
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
		List<Conta> listaContasCliente = contaDAO.listarContasCliente(cpf);
		return listaContasCliente;
	}
	
	public boolean isClienteExistente(Cliente pCliente) {
		boolean isClienteExiste = contaDAO.isClienteExistente(pCliente);
		return isClienteExiste;
	}
	
	public List<Cliente> listarClientes(){
		return contaDAO.listarClientes();
	}
	
	public Cliente buscarCliente(String cpf) {
		Cliente clienteAtual = contaDAO.buscarCliente(cpf);
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
