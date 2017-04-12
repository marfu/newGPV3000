package ci.prosuma.prosumagpv.metier.util.app.entity;

public class LigneMouvementSA {
	
	private long gencode;
	
	private long qte;
	
	private long montant;
	
	private String libelle;
	
	public LigneMouvementSA() {
	}
	
	

	public LigneMouvementSA(long gencode, long qte, long montant) {
		super();
		this.gencode = gencode;
		this.qte = qte;
		this.montant = montant;
	}



	public long getGencode() {
		return gencode;
	}

	public void setGencode(long gencode) {
		this.gencode = gencode;
	}

	public long getQte() {
		return qte;
	}

	public void setQte(long qte) {
		this.qte = qte;
	}

	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "LigneMouvementSA [gencode=" + gencode + ", qte=" + qte
				+ ", montant=" + montant + ", libelle=" + libelle + "]";
	}

}
