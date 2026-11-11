package br.com.territoryPreditive.model;

public class SensorEnergia implements Sensor {

	@Override
	public double lerValor() {
		double min = 1000; // Valor minimo do random
		double max = 2000; // valor maximo

		if (verificarStatus() == StatusSensor.Quebrado) {
			System.out.println("O Sensor não esta funcionado corretamente");
			return 0;
		}

		return Math.random() * (max - min) + min;
	}

	@Override
	public StatusSensor verificarStatus() {
		// TODO Auto-generated method stub
		return StatusSensor.Funcionando;
	}

	@Override
	public String TipoSensor() {
		// TODO Auto-generated method stub
		return "Consumo Energia";
	}

}
