package cn.supermarche.model;

public class Statistique {
	
	private static String achats="0";
	private static String fournisseurs="0";
	private static String revenues="0";
	private static String clients="0";
	
	
	
	public static String getAchats() {
		return achats;
	}
	public static void setAchats(String achats) {
		Statistique.achats = achats;
	}
	public static String getFournisseurs() {
		return fournisseurs;
	}
	public static void setFournisseurs(String fournisseurs) {
		Statistique.fournisseurs = fournisseurs;
	}
	public static String getRevenues() {
		return revenues;
	}
	public static void setRevenues(String revenues) {
		Statistique.revenues = revenues;
	}
	public static String getClients() {
		return clients;
	}
	public static void setClients(String clients) {
		Statistique.clients = clients;
	}
	
	
	
	
	
	

}
