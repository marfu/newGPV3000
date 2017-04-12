package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;

public interface IMouvementService {

	public List<DetailMouvement> getAllDetailMouvementForEntete(long enteteId);

	public EnteteMouvement createOrUpdateEnteteMouvement(EnteteMouvement ei);

	public EnteteMouvement getEnteteMouvement(long id);

	public boolean removeEnteteMouvement(EnteteMouvement ei);

	public List<EnteteMouvement> getAllEnteteMouvementForMag(String pvtCode);

	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvement(
			String pvtCode, String codeOrigine);

	public DetailMouvement createOrUpdateDetailMouvement(DetailMouvement ei);

	public DetailMouvement getDetailMouvement(long id);

	public boolean removeDetailMouvement(DetailMouvement ei);

	public List<EnteteMouvement> executeQuery(String s);

	public List<DetailMouvement> executeQueryForDetail(String s);

	public String createMouvementReceptionFromFact(String codeMag,
			List<String> lineList, String user);

	public List<EnteteMouvement> executeLazyQuery(String query, int first,
			int pageSize);

	public Long getRowCount(String query);

	public EnteteMouvement createOrUpdateEnteteMouvementWithoutMAJStock(
			EnteteMouvement ei);

	public List<DetailMouvement> getAllDetailMouvementForArticle(
			String codeArticle, String codeMag);

	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvementAndDate(
			String pvtCode, String codeOrigine, Date dateDebut, Date dateFin);

	public List<DetailMouvement> getAllDetailMouvementForIntervalDate(
			String codeMag, Date dateDebut, Date dateFin, List<String> qteFiltre);

	public List<DetailMouvement> getAllDetailMouvementForIntervalDate(String codeMag,
			Date dateDebut, Date dateFin, List<String> qteFiltre,
			String secteur, String rayon, String famille, String sousFamille,
			String gisDebut, String gisFin);

}
