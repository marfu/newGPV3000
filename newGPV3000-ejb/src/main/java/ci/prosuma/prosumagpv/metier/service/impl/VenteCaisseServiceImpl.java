package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ci.prosuma.prosumagpv.entity.dto.CAArticleDTO;
import ci.prosuma.prosumagpv.entity.dto.CAFamilleDTO;
import ci.prosuma.prosumagpv.entity.dto.CARayonDTO;
import ci.prosuma.prosumagpv.entity.dto.CASecteurDTO;
import ci.prosuma.prosumagpv.entity.dto.CASousFamilleDTO;
import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.entity.util.SousFamille;
import ci.prosuma.prosumagpv.entity.vente.VenteCaisse;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IVenteCaisseDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFamilleDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeRayonDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSecteurDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeSousFamilleDAO;
import ci.prosuma.prosumagpv.metier.service.IVenteCaisseService;


@Stateless
@Local(IVenteCaisseService.class)
public class VenteCaisseServiceImpl implements IVenteCaisseService , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private IVenteCaisseDAO  venteCaisseDAO;
	
	@EJB
	private ITypeRayonDAO rayonDAO;

	@EJB
	private ITypeSecteurDAO secteurDAO;

	@EJB
	private ITypeSousFamilleDAO sousFamilleDAO;
	
	@EJB
	private ITypeFamilleDAO familleDAO;
	
	@EJB
	private IArticleDAO articleDAO;

	@Override
	public VenteCaisse createOrUpdateVenteCaisse(VenteCaisse entity) {
		return venteCaisseDAO.createOrUpdateVenteCaisse(entity);
	}

	@Override
	public VenteCaisse getVenteCaisse(long entityId) {
		return venteCaisseDAO.getVenteCaisse(entityId);
	}

	@Override
	public boolean removeVenteCaisse(VenteCaisse entity) {
		return venteCaisseDAO.removeVenteCaisse(entity);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDate(String pvtCode,
			Date dateDebut, Date dateFin) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDate(pvtCode, dateDebut, dateFin);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySecteur(
			String pvtCode, Date dateDebut, Date dateFin, String secteurCode) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateBySecteur(pvtCode, dateDebut, dateFin, secteurCode);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByRayon(
			String pvtCode, Date dateDebut, Date dateFin, String rayon) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateByRayon(pvtCode, dateDebut, dateFin, rayon);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByFamille(
			String pvtCode, Date dateDebut, Date dateFin, String familleCode) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateByFamille(pvtCode, dateDebut, dateFin, familleCode);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateBySousFamille(
			String pvtCode, Date dateDebut, Date dateFin, String sousFamilleCode) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateBySousFamille(pvtCode, dateDebut, dateFin, sousFamilleCode);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateByGisement(
			String pvtCode, Date dateDebut, Date dateFin, long gisementDebut,
			long gisementFin) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateByGisement(pvtCode, dateDebut, dateFin, gisementDebut, gisementFin);
	}

	@Override
	public List<VenteCaisse> getAllVenteCaisseForMagAndDateArticle(
			String pvtCode, Date dateDebut, Date dateFin, String codeArticle) {
		return venteCaisseDAO.getAllVenteCaisseForMagAndDateArticle(pvtCode, dateDebut, dateFin, codeArticle);
	}

	@Override
	public List<CASecteurDTO> getCaSecteurByDate(Date debut, Date fin,
			String codeMag) {
		List<CASecteurDTO> listSec = venteCaisseDAO.getCaSecteurByDate(debut, fin, codeMag);
		List<CASecteurDTO> result = new ArrayList<>();
		BigDecimal total = new BigDecimal(0) ;
		for(CASecteurDTO sec : listSec){
			total=total.add(sec.getPrixVente());
		}
		System.out.println("Chiffre Affaire Total : "+total.toPlainString());
		for(CASecteurDTO sec : listSec){
			BigDecimal percent = (sec.getPrixVente().divide(total, 3, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
			sec.setPourcentage(percent.floatValue());
			Secteur s = secteurDAO.getSecteur(sec.getCodeSecteur());
			if(s!=null){
				sec.setLibSecteur(s.getLibelle());
			}else{
				sec.setLibSecteur("INCONNU");
			}
			result.add(sec);
		}
		return result;
	}

	@Override
	public List<CARayonDTO> getCaRayonBySecteurAndDate(Date debut, Date fin,
			String codeMag, String secteur) {
		// TODO Auto-generated method stub
		List<CARayonDTO> listRay = venteCaisseDAO.getCaRayonBySecteurAndDate(debut, fin, codeMag, secteur);
		List<CARayonDTO> result = new ArrayList<>();
		BigDecimal total = new BigDecimal(0) ;
		for(CARayonDTO sec : listRay){
			total=total.add(sec.getPrixVente());
		}
		System.out.println("Chiffre Affaire Total : "+total.toPlainString());
		for(CARayonDTO sec : listRay){
			BigDecimal percent = (sec.getPrixVente().divide(total, 3, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
			sec.setPourcentage(percent.floatValue());
			Rayon ray = rayonDAO.getRayon(sec.getCodeRayon());
			if(ray!=null){
				sec.setLibRayon(ray.getLibelle());
			}else{
				sec.setLibRayon("INCONNU");
			}
			result.add(sec);
		}
		return result;
	}

	@Override
	public List<CAFamilleDTO> getCaFamilleByRayonAndDate(Date debut, Date fin,
			String codeMag, String rayon) {
		List<CAFamilleDTO> listFam = venteCaisseDAO.getCaFamilleByRayonAndDate(debut, fin, codeMag, rayon);
		List<CAFamilleDTO> result = new ArrayList<>();
		BigDecimal total = new BigDecimal(0) ;
		for(CAFamilleDTO sec : listFam){
			total=total.add(sec.getPrixVente());
		}
		System.out.println("Chiffre Affaire Total : "+total.toPlainString());
		for(CAFamilleDTO sec : listFam){
			BigDecimal percent = (sec.getPrixVente().divide(total, 3, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
			sec.setPourcentage(percent.floatValue());
			Famille fam = familleDAO.getFamille(sec.getCodeFamille());
			if(fam!=null){
				sec.setLibFamille(fam.getLibelle());
			}else{
				sec.setLibFamille("INCONNU");
			}
			result.add(sec);
		}
		return result;
	}

	@Override
	public List<CASousFamilleDTO> getCaSousFamilleByFamilleAndDate(Date debut,
			Date fin, String codeMag, String famille) {
		
		List<CASousFamilleDTO> listSouFam = venteCaisseDAO.getCaSousFamilleByFamilleAndDate(debut, fin, codeMag, famille);
		List<CASousFamilleDTO> result = new ArrayList<>();
		BigDecimal total = new BigDecimal(0) ;
		for(CASousFamilleDTO sec : listSouFam){
			total=total.add(sec.getPrixVente());
		}
		System.out.println("Chiffre Affaire Total : "+total.toPlainString());
		for(CASousFamilleDTO sec : listSouFam){
			BigDecimal percent = (sec.getPrixVente().divide(total, 3, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
			sec.setPourcentage(percent.floatValue());
			SousFamille fam = sousFamilleDAO.getSousFamille(sec.getCodeSousFamille());
			if(fam!=null){
				sec.setLibSousFamille(fam.getLibelle());
			}else{
				sec.setLibSousFamille("INCONNU");
			}
			result.add(sec);
		}
		return result;
	}

	@Override
	public List<CAArticleDTO> getCaArticleBySousFamilleAndDate(Date debut,
			Date fin, String codeMag, String sousfamille) {
		
		List<CAArticleDTO> listArt = venteCaisseDAO.getCaArticleBySousFamilleAndDate(debut, fin, codeMag, sousfamille);
		List<CAArticleDTO> result = new ArrayList<>();
		BigDecimal total = new BigDecimal(0) ;
		for(CAArticleDTO sec : listArt){
			total=total.add(sec.getPrixVente());
		}
		System.out.println("Chiffre Affaire Total : "+total.toPlainString());
		for(CAArticleDTO sec : listArt){
			BigDecimal percent = (sec.getPrixVente().divide(total, 3, RoundingMode.HALF_UP)).multiply(new BigDecimal(100));
			sec.setPourcentage(percent.floatValue());
			String art = articleDAO.getArticleByCodeAndMag(codeMag,sec.getCodeArticle());
			if(art!=null){
				sec.setLibArticle(art);
			}else{
				sec.setLibArticle("INCONNU");
			}
			result.add(sec);
		}
		return result;
	}

}
