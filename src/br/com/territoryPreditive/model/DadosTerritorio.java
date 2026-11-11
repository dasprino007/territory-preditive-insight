package br.com.territoryPreditive.model;

// Implementa a interface para ser clonavel os dados
public class DadosTerritorio {
	// O valor de umidade esta em porcetagem %
	private double Umidade;
	// O consumo de energia esta em MW
	private double ConsumoEnergiaTotal;
	// A temperatura esta em graus celcius
	private double Temperatura;

	public DadosTerritorio(double umidade, double consumoEnergiaTotal, double temperatura) {
		setUmidade(umidade);
		setConsumoEnergiaTotal(consumoEnergiaTotal);
		setTemperatura(temperatura);
	}
	
	public double getUmidade() {
		return Umidade;
	}

	public void setUmidade(double umidade) {
		if(umidade < 0) {
			System.out.println("[Error]: Umidade não pode ser um numero negativo");
			return;
		}
		
		if(umidade > 100) {
			System.out.println("[Error]: Não existe umidade maior que 100%");
			return;
		}
		
		Umidade = umidade;
	}
	
	public double getConsumoEnergiaTotal() {
		return ConsumoEnergiaTotal;
	}

	public void setConsumoEnergiaTotal(double consumoEnergiaTotal) {
		if (consumoEnergiaTotal < 0) {
			System.out.println("[Error]: O consumo de energia não pode ser um numero negativo");
			return;
		}

		ConsumoEnergiaTotal = consumoEnergiaTotal;
	}

	public double getTemperatura() {
		return Temperatura;
	}

	public void setTemperatura(double temperatura) {
		// Isso é a menor temperatura possivel
		double menotTemp = -273.15;

		if (temperatura < menotTemp) {
			System.out.println("[Error]: A temperatura não pode ser menor que " + menotTemp + "ºC");
			return;
		}

		Temperatura = temperatura;
	}

	protected void printDados() {
		System.out.println("Umidade : " + String.format("%.2f", Umidade) + "%");
		System.out.println("Temperatura: " + String.format("%.2f", Temperatura) + "ºC");
		System.out.println("Consumo de energia: " + String.format("%.2f", ConsumoEnergiaTotal) + "MW");
	}
}
