package ci.prosuma.prosumagpv.metier.dao.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.LienPromoPvt;
import ci.prosuma.prosumagpv.entity.pk.PromoPvtRefPK;

public interface ILienPromoPvtDAO {

	public LienPromoPvt createOrUpdateLPP(LienPromoPvt entity);

	public LienPromoPvt getLPP(PromoPvtRefPK id);

	public boolean removeLPP(LienPromoPvt entity);

	public List<String> getAllPromoForPvt(String pvtCode);

	public List<String> getAllPvtForPromo(String promo);

	public LienPromoPvt getLienPromoPvtByPromoAndPvt(String pvtCode,
			String promo);

}
