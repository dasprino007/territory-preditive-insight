package br.com.territoryPreditive.model;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class EstacaoColeta extends ComponenteTerritorial {
	// dados
	private DadosTerritorio DadosTerritorio;

	// Sensores
	private SensorTemperatura Temperatura;
	private SensorUmidade Umidade;
	private SensorEnergia Energia;

	// Previsões
	private PrevisaoMediaPonderada MediaPonderada;
	private PrevisaoComparacao Comparacao;

	public EstacaoColeta(String id, String nome, String regiao) {
		super(id, nome, regiao, Status.Normal);
		Temperatura = new SensorTemperatura();
		Umidade = new SensorUmidade();
		Energia = new SensorEnergia();

		MediaPonderada = new PrevisaoMediaPonderada();
		Comparacao = new PrevisaoComparacao();

		setDadosTerritorio(new DadosTerritorio(Umidade.lerValor(), Energia.lerValor(), Temperatura.lerValor()));
	}

	public void rodarSensores() {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return;
		}

		Comparacao.setDadoAnterior(DadosTerritorio);
		
		double temperatura = Temperatura.lerValor();
		double energia = Energia.lerValor();
		double umidade = Umidade.lerValor();

		DadosTerritorio.setTemperatura(temperatura);
		DadosTerritorio.setConsumoEnergiaTotal(energia);
		DadosTerritorio.setUmidade(umidade);

		DadosTerritorio.printDados();
	}

	public void rodarPrevisoes() {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return;
		}

		RespostaPrevisao respMediaPonderada = MediaPonderada.prever(getDadosTerritorio());
		RespostaPrevisao respComparacao = Comparacao.prever(getDadosTerritorio());

		System.out.println("--- Media Ponderada ---");
		if (respMediaPonderada == null) {
			System.out.println("[Error]: A previsao Media Ponderada não pode ser feitas, por falta de dados");
		} else {
			respMediaPonderada.getDadoPrevisto().printDados();
			respMediaPonderada.printMotivos();

			setStatus(respMediaPonderada.getStatus());
		}
		
		System.out.println("--- Comparaçao ---");
		if (respComparacao == null) {
			System.out.println("[Error]: A previsao Comparação não pode ser feitas, por falta de dados");
		} else {
			respComparacao.getDadoPrevisto().printDados();
			respComparacao.printMotivos();

			setStatus(respComparacao.getStatus());
		}
		
		System.out.println("Status: " + getStatus().toString());
	}

	@Override
	public void verificarStatus() {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return;
		}

		System.out.println(getStatus().toString());
	}

	public DadosTerritorio getDadosTerritorio() {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return null;
		}

		return DadosTerritorio;
	}

	private void setDadosTerritorio(DadosTerritorio dadosTerritorio) {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return;
		}

		DadosTerritorio = dadosTerritorio;
	}
	
	public void printInformacao() {
		if (!EstaAtivo()) {
			System.out.println("[Error]: Por favor ligue a estação");
			return;
		}
		
		System.out.println("Nome: " + getNome());
		System.out.println("Região: " + getRegiao());
		System.out.println("Status: " + getStatus());
		
		System.out.println("------ Informações recentes ------");
		DadosTerritorio.printDados();
	}

}
