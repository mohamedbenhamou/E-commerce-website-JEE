package cn.supermarche.model;

public class Commande extends Produit {
	private int commandeId;
	private int uid;
	private int quantite;
	private String date;
	private int pId;
	
	private String Status;
	
	public Commande() {
		this.Status = "en attente";
	}

	public Commande(int commandeId, int uid, int quantite, String date) {
		super();
		this.commandeId = commandeId;
		this.uid = uid;
		this.quantite = quantite;
		this.date = date;
		this.Status = "en attente";
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Commande [commandeId=" + commandeId + ", uid=" + uid + ", quantite=" + quantite + ", date=" + date
				+ "]";
	}
	

}
