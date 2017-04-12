package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;

public interface IDetailMouvementDAO {

	public DetailMouvement createOrUpdateDetailMouvement(DetailMouvement ei);

	public DetailMouvement getDetailMouvement(long id);

	public boolean removeDetailMouvement(DetailMouvement ei);

	public List<DetailMouvement> getAllDetailMouvementForEntete(long enteteId);

	public List<DetailMouvement> getAllDetailMouvementForArticle(
			String codeArticle, String codeMag);

	public List<DetailMouvement> getAllDetailMouvement();

	public List<DetailMouvement> getAllDetailMouvementForGisement(
			long gisementDebut, long gisementFin, String codeMag,
			Date dateDebut, Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementForSecteurFilterRayon(
			String secteur, String rayon, String codeMag, Date dateDebut,
			Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementForSecteur(
			String secteur, String codeMag, Date dateDebut, Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementForRayon(String rayon,
			String codeMag, Date dateDebut, Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementBySecteurAndRayon(
			String secteur, String rayon, String codeMag, Date dateDebut,
			Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementToUpdateStatDem(
			String secteur, String rayon, String codeMag, Date dateDebut,
			Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementForGisementStatDem(
			long gisementDebut, long gisementFin, String codeMag,
			Date dateDebut, Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementToUpdateStatDem(
			String secteur, String rayon, String codeMag, Date dateDebut,
			Date dateFin, List<String> qteFiltre);

	public List<DetailMouvement> getAllDetailMouvementForGisementStatDem(
			long gisementDebut, long gisementFin, String codeMag,
			Date dateDebut, Date dateFin, List<String> qteFiltre);

	public List<DetailMouvement> getAllDetailMouvementForIntervalDate(String codeMag,
			Date dateDebut, Date dateFin, List<String> qteFiltre);

	 public List<DetailMouvement> getAllDetailMouvementForIntervalDate(String codeMag,
			Date dateDebut, Date dateFin, List<String> qteFiltre,
			String secteur, String rayon, String famille, String sousFamille,
			String gisDebut, String gisFin);
}
