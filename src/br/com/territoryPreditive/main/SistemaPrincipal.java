package br.com.territoryPreditive.main;
import java.util.Scanner;

import br.com.territoryPreditive.model.EstacaoColeta;

public class SistemaPrincipal {
	// Para facilitar o uso do scanner
	static Scanner scanner = new Scanner(System.in);

	static private void printIntroducao() {
		System.out.println("----- Controle da estação -----");
		System.out.println("Bem vindo ao controle de estação");
	}

	static private void printMenu() {
		System.out.println("Aperte um botão de 1 a 5");
		System.out.println("1. Ligar/Desligar a estação");
		System.out.println("2. Rodar os sensores");
		System.out.println("3. Fazer previsões sobre os dados");
		System.out.println("4. Info da estação");
		System.out.println("5. Sair");
	}

	static private boolean responderInput(int key, EstacaoColeta estacaoColeta) {
		switch (key) {
		case 1:
			if (estacaoColeta.EstaAtivo())
				estacaoColeta.desativar();
			else
				estacaoColeta.ativar();
			break;
		case 2:
			estacaoColeta.rodarSensores();
			break;
		case 3:
			estacaoColeta.rodarPrevisoes();
			break;
		case 4:
			estacaoColeta.printInformacao();
			break;
		case 5:
			System.out.println("Adeus, volte sempre!!!");
			return false;		
		}
		return true;
	}

	public static void main(String[] args) {
		EstacaoColeta estacoColeta = new EstacaoColeta("Estação 1", "Estação de coleta de minas gerais", "Mina gerais");
		boolean loop = true;
		
		while(loop) {
			printMenu();
			System.out.print("Aperte um numero de 1 a 5: ");
			int resp = scanner.nextInt();
			loop = responderInput(resp, estacoColeta);
			scanner.nextLine();
			
			System.out.print("Por favor aperte enter para continuar:");
			scanner.nextLine();
		}
		
	}

}
