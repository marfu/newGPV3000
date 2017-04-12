/**
 * 
 */
package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.entity.util.SousFamille;

/**
 * @author tagsergi
 *
 */
public interface IClassificationArticle {
	
	public Secteur getSecteurByCode(String code);
	
	public Rayon getRayonByCode(String rayon);
	
	public Famille getFamilleByCode(String famille);
	
	public SousFamille getSpusFamilleByCode(String sFamille);

}
