package ci.prosuma.prosumagpv.entity.util;

public class EnumerationParam {

	public enum TypePromo {
		MAGASIN, CENTRALE, DLV
	}

	public enum TypeClient {
		DEMI_GROS, TERMES, COMMERCES_ELECTRONIQUE
	}

	public enum TypeDeDepot {
		MAGASIN, DEMI_GROS,RESERVE
	}

	public enum TypeReglement {
		ESPECE, CHEQUE, TRAITE, VIREMENT,  CARTE_ELECTRONIQUE , BON_ACHAT , COMPTE_A_TERME
	}

	public enum TypeOrigineMouvement {
		RECEPTION, INVENTAIRE, VENTE, CESSION, CAISSE, AJUSTEMENT, DEMARQUE, DLV, SCDT, GROS, DLR, SOLDE
	}

	public enum TypeArticle {
		PRINCIPAL, SECONDAIRE
	}
	
	public enum TypeAnomalie{
		GISEMENT, INCONNU
	}

	public enum TypeInventaire {
		GLOBAL, RAYON, GISEMENT, RAYON_ET_GISEMENT
	}

	public enum TypeVente {
		VENTE_A_TERME, DEMI_GROS, COMMERCE_ELECTRONIQUE
	}

	public enum TypeLivraison {
		CENTRALE, DIRECTE, ECLATEMENT, ASP, IMPORT
	}
	

	public enum TypePointDeVente {
		CASH_IVOIRE, CENTRALE, CFR, DGF, DGS, FOI, MAGASIN, MEDIASTORE, PSC, SOCOCE
	}

	public enum CategorieGenCode {
		PRINCIPAL, SECONDAIRE ,  RCOND , SCOND, DLVC , DLVM , GROS, GENERIQUE
	}
	public enum ModeGenCode {
		PRINCIPAL, SECONDAIRE ,  RCOND , SCOND, DLVC , DLVM , GROS, GENERIQUE
	}

	public enum OrigineGenCode {
		CENTRALE, MAGASIN, GPV3000
	}

	

	public enum EtatIncident {
		EN_COURS,CLOTURER
	}
	public enum TypePieceIdentite {
		PASSEPORT,ONI,CARTE_SEJOUR,CNI,CARTE_CONSULAIRE,PC
	}
	
	public enum TypeTarif {
		PARTICULIER,ENTREPRISE,RESTAURATEUR
	}
	public enum TypeAvantage {
		COMPTE_TERME,CHEQUE
	}
	
	public enum OrigineCommande {
		SUGGESTION,CENTRALE,LD,ALL
	}

	public enum SensDocument {
		CREDIT, DEBIT, PROFORMA
	}

	public enum EtatDossierSuggestion {
		TRANSMIT_MAGASIN, RECU_EN_PARTI, RECU_EN_TOTALITE
	}

	public enum ModeDossier {
		CENTRAL, LIVRAISON_DIRECT, PREALLOTI
	}
	
	public enum EtatCommande {
		SAISIE, VALIDER,RECU , TRANSMIT , FACTURER , CLOTURER  
	}
	public enum EtatTransmission {
	 A_RETRANSMETTRE,TRANSMIT
	}
	
	public enum TypeDocument{
		PRO_FORMA , ACHAT , REGLEMENT , AVOIR
	}

}
