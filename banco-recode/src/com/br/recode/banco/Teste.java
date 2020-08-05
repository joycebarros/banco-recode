package com.br.recode.banco;

import java.io.IOException;
import java.util.Scanner;

import com.br.recode.controlador.Controlador;

public class Teste {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		int codigo;
				
		do {
					
			System.out.println(Menu.lerMenu());
			System.out.println("Digite o cógido da ação desejada:");
			codigo = scan.nextInt();
			
		    if (codigo == 1){
		    	Controlador.abrirConta();
		    	
		    }else if(codigo == 2) {
				Controlador.consultarSaldo();				
						
			} else if (codigo == 3) {
				Controlador.creditarValor();
				
			} else if (codigo == 4) {
				Controlador.debitarValor();
				
			} else if (codigo == 5) {
				
				Controlador.transferirValor();
			}
			
		} while (codigo != 151); 
			System.out.println("Você saiu do sistema. Volte sempre!");
					
		scan.close();
		
	}		

}
