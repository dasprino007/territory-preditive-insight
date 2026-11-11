package br.com.territoryPreditive.model;

public class SensorTemperatura implements Sensor {
	@Override
	public double lerValor() {
		double min = 20.0; // Valor minimo do random
		double max = 45.0; // valor maximo

		if (verificarStatus() == StatusSensor.Quebrado) {
			System.out.println("O Sensor não esta funcionado corretamente");
			return 0;
		}

		return Math.random() * (max - min) + min;
	}

	@Override
	public StatusSensor verificarStatus() {
		return StatusSensor.Funcionando;
	}

	@Override
	public String TipoSensor() {
		return "Temperatura";
	}

}
