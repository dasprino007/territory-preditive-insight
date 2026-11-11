package br.com.territoryPreditive.model;

public enum Status {
	Normal {
		@Override
		public String toString() {
			return "Normal";
		}
	},
	Atencao {
		@Override
		public String toString() {
			return "Atenção";
		}
	},
	Critico {
		@Override
		public String toString() {
			return "Critico";
		}
	},
}