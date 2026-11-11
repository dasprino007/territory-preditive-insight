package br.com.territoryPreditive.model;

import java.util.ArrayList;

public class RespostaPrevisao {
	private DadosTerritorio DadoPrevisto;
	private ArrayList<String> Motivos;
	private Status Status;

	public RespostaPrevisao(DadosTerritorio dadoPrevisto, ArrayList<String> motivos, Status status) {
		this.DadoPrevisto = dadoPrevisto;
		this.Motivos = motivos;
		this.Status = status;
	}

	public ArrayList<String> getMotivos() {
		return Motivos;
	}

	public DadosTerritorio getDadoPrevisto() {
		return DadoPrevisto;
	}

	public Status getStatus() {
		return Status;
	}

	public void printMotivos() {
		if (getMotivos().size() == 0)
			return;
		
		System.out.println("Alertas: ");
		
		for (String motivo : getMotivos()) {
			System.out.println(motivo);
		}
	}

}
