package com.br.recode.gui;

import java.io.IOException;

public class Menu {

	public static StringBuilder lerMenu() throws IOException{
			
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("#########################################\n");
		stringBuilder.append("#[1] -                      Abrir Conta #\n");
		stringBuilder.append("#[2] -                   Consulta saldo #\n");
		stringBuilder.append("#[3] -                creditar em conta #\n");
		stringBuilder.append("#[4] -                 debitar em conta #\n");
		stringBuilder.append("#[5] -                       Transferir #\n");
		stringBuilder.append("#[6] - Lista todas as contas do cliente #\n");
		stringBuilder.append("#[7] -           Lista todos os cliente #\n");
		stringBuilder.append("#[8] -                Atualizar Cliente #\n");
		stringBuilder.append("#[9] -                 Inativar Cliente #\n");
		stringBuilder.append("#[151] -                           sair #\n");
		stringBuilder.append("#########################################");
			
		return stringBuilder;
	}
}
