package com.br.recode.DAO;

import java.util.ArrayList;
import java.util.List;

import com.br.recode.entidades.Cliente;
import com.br.recode.entidades.Conta;
import com.br.recode.entidades.ContaCorrente;
import com.br.recode.entidades.ContaSalario;

public class ContaDAO {
	
	private List<Conta> listaContas = new ArrayList<>();
	private List<Cliente> listaClientes = new ArrayList<>();
	
	public ContaDAO() {
		Cliente cliente1 = new Cliente();
		cliente1.setCpf("055");
		cliente1.setNome("Joyce");
		
		Conta conta1 = new ContaCorrente();
		conta1.setNumero(121);
		conta1.setSaldo(300.0);
		conta1.setCliente(cliente1);
		conta1.setSenha("1234");
		
		Cliente cliente2 = new Cliente();
		cliente2.setCpf("044");
		cliente2.setNome("Maria");
		
		Conta conta2 = new ContaSalario();
		conta2.setNumero(122);
		conta2.setSaldo(200.0);
		conta2.setCliente(cliente2);
		conta2.setSenha("4321");
		listaContas.add(conta1);
		listaContas.add(conta2);
		listaClientes.add(cliente1);
		listaClientes.add(cliente2);
		
	}
			
	public void addContas(Conta conta){
		listaContas.add(conta);				
	}
	
	public List<Conta> listarContas(){
		return listaContas;
	}
	
	public Conta buscarConta(int numero) {
		Conta conta = null;
		for (Conta contaAtual : listaContas) {
			if(numero == contaAtual.getNumero()) {
				conta = contaAtual;				
			}
		}
		return conta;
	}

	public Integer tamanhoListaContas() {
		return listaContas.size();
	}
	
	public void addClientes(Cliente cliente) {
		listaClientes.add(cliente);
	}
	
	public void removerCliente(Cliente cliente) {
		listaClientes.remove(cliente);
	}
		
	public List<Cliente> listarClientes(){
		return listaClientes;
	}
	
	public List<Conta> listarContasCliente(String cpf){
		List<Conta> listaContasCliente = new ArrayList<>();
		for (Conta contaAtual : listaContas) {
			if(cpf.equals(contaAtual.getCliente().getCpf())) {
				listaContasCliente.add(contaAtual);								
			}
		}
		return listaContasCliente;
	}
	
	public boolean isClienteExistente(Cliente pCliente) {
		boolean isClienteExiste = false;
		for (Cliente cliente : listaClientes) {
			if(cliente.equals(pCliente)) {
				isClienteExiste = true;
			}
		}
		return isClienteExiste;
	}
	
	public Cliente buscarCliente(String cpf) {
		Cliente clienteAtual = null;
		for (Cliente cliente : listaClientes) {
			if(cpf.equals(cliente.getCpf())) {
				clienteAtual = cliente;
			}			
		}
		return clienteAtual;
	}
			
}
