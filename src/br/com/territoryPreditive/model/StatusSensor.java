package br.com.territoryPreditive.model;

public enum StatusSensor {
	Funcionando {
		@Override
		public String toString() {
			return "Funcionado";
		}
	},
	Quebrado {
		@Override
		public String toString() {
			return "Quebrado";
		}
	}
}
