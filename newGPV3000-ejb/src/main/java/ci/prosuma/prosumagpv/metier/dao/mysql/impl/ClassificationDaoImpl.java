/**
 * 
 */
package ci.prosuma.prosumagpv.metier.dao.mysql.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.entity.util.Rayon;
import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosuma.prosumagpv.entity.util.SousFamille;
import ci.prosuma.prosumagpv.metier.dao.mysql.IClassificationArticle;

/**
 * @author tagsergi
 *
 */
public class ClassificationDaoImpl implements IClassificationArticle , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public Secteur getSecteurByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rayon getRayonByCode(String rayon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Famille getFamilleByCode(String famille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SousFamille getSpusFamilleByCode(String sFamille) {
		// TODO Auto-generated method stub
		return null;
	}


}
