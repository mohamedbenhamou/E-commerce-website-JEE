package cn.supermarche.model;

public  class Utilisateur {
	private int id;
	private String name;
	private String email;
	private String password;
	private String adress ;
	private String NumTel;
	
	private boolean isAdmin;
	
	
	public Utilisateur(int id, String name, String email, String password, String adress, String numTel) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.adress = adress;
		NumTel = numTel;
		this.isAdmin = false;
	}

	public Utilisateur() {
		
	}
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getNumTel() {
		return NumTel;
	}

	public void setNumTel(String numTel) {
		NumTel = numTel;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean getisAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	

}
