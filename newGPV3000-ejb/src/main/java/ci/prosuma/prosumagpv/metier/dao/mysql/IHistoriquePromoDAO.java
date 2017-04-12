package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.Date;
import java.util.List;

import ci.prosuma.prosumagpv.entity.HistoriquePromo;

public interface IHistoriquePromoDAO {

	public HistoriquePromo createOrUpdateHistoriquePromo(HistoriquePromo entity);

	public HistoriquePromo getHistoriquePromo(HistoriquePromo entity);

	public boolean removeHistoriquePromo(HistoriquePromo entity);

	public List<HistoriquePromo> getAllHistoriquePromoForPromo(String pvtCode , String libelReduit);
	
	public List<HistoriquePromo> getAllHistoriquePromoForPromoAndDate(String pvtCode , String libelReduit, Date debutPromo, Date finPromo);
	
	
	
}
