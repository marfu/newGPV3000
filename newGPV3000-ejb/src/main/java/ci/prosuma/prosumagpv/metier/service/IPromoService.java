package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ci.prosuma.prosumagpv.entity.HistoriquePromo;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.PromoHistoDateDTO;

public interface IPromoService {

	public Promo createOrUpdatePromo(Promo entity);

	public Promo getPromo(String entity);

	public boolean removePromo(Promo entity);

	public List<Promo> getAllPromoForPVT(String pvt);

	public List<Promo> getAllPromoEnVenteForPVT(String pvt);

	public List<Promo> getAllPromoActiveForPVT(String pvt);
	
	public HashMap<String,String> getAllPromoEnFacturationByMag(String pvt);
        
        public List<String> getAllPromoStringEnFacturationByMag(String pvt);
	
	public List<Promo> getAllPromoForDateRangeAndMag(Date dateDebut, Date dateFin, String pvt);
	
	public List<Promo> getAllPromoRangeAndMag(String codeLot, String pvt);
	
	public List<Promo> getAllPromoByMagAndThemePromoLike(String codeLot, String pvt);
	
	public List<Promo> getAllPromoForDateAndMag(Date date, String pvtCode);

	public void processPromoForMag(String pvtCode,String promo);
	//Ajouté par serge Ayepi
	
	public HistoriquePromo createOrUpdateHistoriquePromo(HistoriquePromo entity);

	public HistoriquePromo getHistoriquePromo(HistoriquePromo entity);

	public boolean removeHistoriquePromo(HistoriquePromo entity);

	public List<HistoriquePromo> getAllHistoriquePromoForPromo(String pvtCode , String libelReduit);

	public void calculPromoAuto(String mag);

	public void calculArticlePromo(String mag);

	void UpdatePromoZero();
	
	public List<PromoHistoDateDTO> getAllPromoDate(String codePromo, String mag);
	
	public Promo getPromoByCodePromoAndDateDebutFacturation(String entity, Date dateDebutPromo);

}
