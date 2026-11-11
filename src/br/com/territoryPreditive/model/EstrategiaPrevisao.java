package br.com.territoryPreditive.model;

import java.util.ArrayList;

public abstract class EstrategiaPrevisao {

	protected ArrayList<DadosTerritorio> ListaDadosTerritorio;

	public EstrategiaPrevisao() {
		ListaDadosTerritorio = new ArrayList<DadosTerritorio>(); // lista vazia
	}

	public abstract RespostaPrevisao prever(DadosTerritorio dados);

	public ArrayList<DadosTerritorio> getListaDadosTerritorio() {
		// Devolve uma copia para proteger os dados
		ArrayList<DadosTerritorio> copiaListaDadosTerritorio = new ArrayList<DadosTerritorio>(ListaDadosTerritorio);

		return copiaListaDadosTerritorio;
	}
}
