package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.PromoHistoDateDTO;

public interface IPromoDAO {

	public Promo createOrUpdatePromo(Promo entity);

	public Promo getPromo(String entity);
	
	public Promo getPromoByMag(String entity, String Mag);
	
	public List<Promo> getPromoByMagAndThemePromoLike(String entity, String Mag);

	public boolean removePromo(Promo entity);

	public List<Promo> getAllPromoForPVT(String pvt);
	
	public List<Promo> findAllPromoByMagAndStatus(String pvt);

	public List<Promo> getAllPromoActiveForPVT(String pvt);

	public List<Promo> getAllPromoEnVenteForPVT(String pvt);
	
	public List<Promo> getAllPromoEnFacturationForPVT(String pvt);

	public List<Promo> getAllPromoForDateRangeAndMag(Date dateDebut, Date dateFin, String pvt);

	public List<Promo> getAllPromoForDateAndMag(Date date, String pvtCode);
	
	public List<PromoHistoDateDTO> getAllPastPromoDate(String codePromo, String codeMag);
	
	public Promo getPromoByCodePromoAndDateDebutFacturation(String entity,
			Date dateDebutPromo);

}
