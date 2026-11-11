package br.com.territoryPreditive.model;

import java.util.ArrayList;

public class PrevisaoComparacao extends EstrategiaPrevisao {
	private DadosTerritorio DadoAnterior;
	
	public PrevisaoComparacao() {
		DadoAnterior = null;
	}
	
	public void setDadoAnterior(DadosTerritorio dadosAnterior) {
		if(dadosAnterior == null)
			return;
		
		DadoAnterior = new DadosTerritorio(dadosAnterior.getUmidade(), dadosAnterior.getConsumoEnergiaTotal(), dadosAnterior.getTemperatura());
	}
	
	@Override
	public RespostaPrevisao prever(DadosTerritorio dados) {
		if(DadoAnterior == null) {			
			setDadoAnterior(dados);
			return null;
		}
		
		ArrayList<String> motivos = new ArrayList<String>();
		Status status = Status.Normal;
		
		double difEnergia = DadoAnterior.getConsumoEnergiaTotal() - dados.getConsumoEnergiaTotal();
		double difTemp = DadoAnterior.getTemperatura() - dados.getTemperatura();
		double difUmid = DadoAnterior.getUmidade() - dados.getUmidade();
		
		if(difEnergia >= 200) {
			motivos.add("[Alerta]: Houve um aumento de "+ String.format("%.2f", difEnergia) +" GW/h");
			status = status.Atencao;
		}
		
		else if(difEnergia >= 500) {
			motivos.add("[Alerta]: Houve um aumento de "+ String.format("%.2f", difEnergia) +" GW/h");
			status = status.Critico;
		}
		
		if(DadoAnterior.getTemperatura() - dados.getTemperatura() > 5) {
			motivos.add("[Alerta]: Houve um aumento de "+ String.format("%.2f", difTemp) +" ºC");
			status = status.Atencao;
		}
		
		else if(DadoAnterior.getTemperatura() - dados.getTemperatura() > 15) {
			motivos.add("[Critico]: Houve um aumento de "+ String.format("%.2f", difTemp) +" ºC");
			status = status.Critico;
		}
		
		if(difUmid > -10) {
			motivos.add("[Alerta]: A umidade diminuiu em "+ String.format("%.2f", difTemp) +" %");
			status = status.Atencao;
		}
		
		else if(difUmid > -20)
		{
			motivos.add("[Critico]: A umidade diminuiu em "+ String.format("%.2f", difTemp) +" %");
			status = status.Critico;
		}
		
		setDadoAnterior(dados);
		return new RespostaPrevisao(dados, motivos, status);
	}

}
