package br.com.territoryPreditive.model;

import java.util.ArrayList;

public class PrevisaoMediaPonderada extends EstrategiaPrevisao {
	public PrevisaoMediaPonderada() {
		super();
	}

	@Override
	public RespostaPrevisao prever(DadosTerritorio dados) {
		
		DadosTerritorio media = new DadosTerritorio(0, 0, 0);
		ArrayList<String> motivos = new ArrayList<String>();
		Status status = Status.Normal;
		
		if(!ListaDadosTerritorio.add(dados)) {
			System.err.println("[Error]: ListaDadosTerritorio não conseguiu adicionar os dados");
			return null;
		};
		
		// Calcular a media
		for (DadosTerritorio dadosTerritorio : ListaDadosTerritorio) {
			media.setTemperatura(media.getTemperatura() + dados.getTemperatura());
			media.setUmidade(media.getUmidade() + dadosTerritorio.getUmidade());
			media.setConsumoEnergiaTotal(media.getConsumoEnergiaTotal() + dadosTerritorio.getConsumoEnergiaTotal());
		}
		
		media.setTemperatura(media.getTemperatura() / ListaDadosTerritorio.size());
		media.setUmidade(media.getUmidade() / ListaDadosTerritorio.size());
		media.setConsumoEnergiaTotal(media.getConsumoEnergiaTotal() / ListaDadosTerritorio.size());
		
		if(media.getConsumoEnergiaTotal() > 1500) {
			motivos.add("[Alerta]: O uso de energia esta maior que 1500 GW/h");
			status = Status.Atencao;
		}
		
		else if(media.getConsumoEnergiaTotal() > 1700) {
			motivos.add("[Critico]: O uso de energia esta maior que 1700 GW/h");
			status = Status.Critico;
		}
		
		if(media.getUmidade() < 50) {
			motivos.add("[Alerta]: Umidade abaixo de 50%, clima seco");
			status = Status.Atencao;
		}
		
		else if(media.getUmidade() < 20) {
			motivos.add("[Critico]: Umidade abaixo de 20%, clima muito seco");
			status = Status.Critico;
		}
		
		if(media.getTemperatura() > 35) {
			motivos.add("[Alerta]: Temperatura acima 35 graus, temperatura acima do normal");
			status = Status.Atencao;
		}
		
		if(media.getTemperatura() > 45) {
			motivos.add("[Critico]: Temperatura acima de 45 graus, temperaturas em estado critico");
			status = Status.Critico;
		}
			
			
		return new RespostaPrevisao(dados, motivos, status);
	}

}
