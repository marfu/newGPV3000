package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.dto.CAArticleDTO;
import ci.prosuma.prosumagpv.entity.dto.CAFamilleDTO;
import ci.prosuma.prosumagpv.entity.dto.CARayonDTO;
import ci.prosuma.prosumagpv.entity.dto.CASecteurDTO;
import ci.prosuma.prosumagpv.entity.dto.CASousFamilleDTO;
import ci.prosuma.prosumagpv.entity.vente.VenteCaisse;

public interface IVenteCaisseService {

	public VenteCaisse createOrUpdateVenteCaisse(VenteCaisse entity);

	public VenteCaisse getVenteCaisse(long entityId);

	public boolean removeVenteCaisse(VenteCaisse entity);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDate(String pvtCode,
			Date dateDebut, Date dateFin);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySecteur(
			String pvtCode, Date dateDebut, Date dateFin, String secteurCode);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByRayon(
			String pvtCode, Date dateDebut, Date dateFin, String rayon);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByFamille(
			String pvtCode, Date dateDebut, Date dateFin, String familleCode);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySousFamille(
			String pvtCode, Date dateDebut, Date dateFin, String sousFamilleCode);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByGisement(
			String pvtCode, Date dateDebut, Date dateFin, long gisementDebut,
			long gisementFin);

	public List<VenteCaisse> getAllVenteCaisseForMagAndDateArticle(
			String pvtCode, Date dateDebut, Date dateFin, String codeArticle);

	public List<CASecteurDTO> getCaSecteurByDate(Date debut, Date fin,
			String codeMag);

	public List<CARayonDTO> getCaRayonBySecteurAndDate(Date debut, Date fin,
			String codeMag, String secteur);

	public List<CAFamilleDTO> getCaFamilleByRayonAndDate(Date debut, Date fin,
			String codeMag, String rayon);

	public List<CASousFamilleDTO> getCaSousFamilleByFamilleAndDate(Date debut,
			Date fin, String codeMag, String famille);

	public List<CAArticleDTO> getCaArticleBySousFamilleAndDate(Date debut,
			Date fin, String codeMag, String sousfamille);

}
