package ci.prosuma.prosumagpv.entity.dto;

import java.io.Serializable;
import java.util.List;

import ci.prosuma.prosumagpv.entity.commande.DetailBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;

public class BonCommandeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EnteteBonCommandeFournisseur ebcf;

	private List<DetailBonCommandeFournisseur> listDetail;
	
	private EnteteSuggestionCommande ebcfSug;

	private List<DetailSuggestionCommande> listDetailSug;



	public BonCommandeDTO() {
	}


	public EnteteBonCommandeFournisseur getEbcf() {
		return ebcf;
	}


	public void setEbcf(EnteteBonCommandeFournisseur ebcf) {
		this.ebcf = ebcf;
	}


	public List<DetailBonCommandeFournisseur> getListDetail() {
		return listDetail;
	}


	public void setListDetail(List<DetailBonCommandeFournisseur> listDetail) {
		this.listDetail = listDetail;
	}


	public EnteteSuggestionCommande getEbcfSug() {
		return ebcfSug;
	}


	public void setEbcfSug(EnteteSuggestionCommande ebcfSug) {
		this.ebcfSug = ebcfSug;
	}


	public List<DetailSuggestionCommande> getListDetailSug() {
		return listDetailSug;
	}


	public void setListDetailSug(List<DetailSuggestionCommande> listDetailSug) {
		this.listDetailSug = listDetailSug;
	}

	

}
