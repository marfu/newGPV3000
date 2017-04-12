package ci.prosuma.prosumagpv.metier.dao.jdbc.impl;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.metier.dao.jdbc.IJDBCGPV3000MySQLConnection;


@Stateless
@Local(IJDBCGPV3000MySQLConnection.class)
public class JDBCGPV3000MySqlConnectionImpl implements IJDBCGPV3000MySQLConnection , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	

	@Override
	public String getSqlRequestByLibelle(String libelle) {
		System.out.println("LE LIBELLE : "+libelle);
		Query query = em.createQuery("SELECT a.requete FROM  SqlRequest a   WHERE a.libelle=:libelle");
		query.setParameter("libelle", libelle);
		return (String) query.getSingleResult();
	}


}
