package br.com.territoryPreditive.model;

public abstract class ComponenteTerritorial {
	
	private String Id;
	private String Nome;
	private String Regiao;
	private Status Status;
	
	private boolean EstaAtivo = true;
	
	public ComponenteTerritorial(String id, String nome,String regiao, Status status) {
		setId(id);
		setNome(nome);
		setRegiao(regiao);
		setStatus(status);
	}
	
	public void ativar() {
		this.EstaAtivo =  true;
		System.out.println("[Info]: A estação esta ligada!!!!");
	}
	
	public void desativar() {
		this.EstaAtivo =  false;
		System.out.println("[Info]: A estação esta desligada!!!!");
	}
	
	public abstract void verificarStatus();

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	protected void setNome(String nome) {
		Nome = nome;
	}

	public String getRegiao() {
		return Regiao;
	}

	protected void setRegiao(String regiao) {
		Regiao = regiao;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}
	
	public boolean EstaAtivo() {
		return EstaAtivo;
	}
}
