package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;

public interface IEnteteMouvementDAO {

	public EnteteMouvement createOrUpdateEnteteMouvement(EnteteMouvement ei);
	
	public EnteteMouvement createOrUpdateEnteteMouvementWithoutMAJStock(EnteteMouvement ei);

	public EnteteMouvement getEnteteMouvement(long id);

	public boolean removeEnteteMouvement(EnteteMouvement ei);

	public List<EnteteMouvement> getAllEnteteMouvementForMag(String pvtCode);

	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvement(String pvtCode, String codeOrigine);

	public String createMouvementReceptionFromFact(String codeMag, List<String> lineList, String user);

	public List<EnteteMouvement> executeQuery(String s);

	public List<DetailMouvement> executeQueryForDetail(String s);

	public List<EnteteMouvement> executeLazyQuery(String query, int first, int pageSize);

	public Long getRowCount(String query);
	
	public List<EnteteMouvement> getAllEnteteMouvementForMagByOrigineMouvementAndDate(String pvtCode, String codeOrigine,Date dateDebut , Date dateFin);

}
