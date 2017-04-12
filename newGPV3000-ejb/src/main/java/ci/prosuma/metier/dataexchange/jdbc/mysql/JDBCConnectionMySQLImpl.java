package ci.prosuma.metier.dataexchange.jdbc.mysql;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.GenCode;

@Stateless
@Local(IJDBCConnectionMySQL.class)
public class JDBCConnectionMySQLImpl implements IJDBCConnectionMySQL,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	String driverMSSQL = null;
	String urlMSSQL = null;
	Connection conn = null;

	@Override
	//à optimiser car la methode getsingleresult est suceptible lancer un NoResultException
	public String getSqlRequestByLibelle(String libelle) {
		Query query = em.createQuery("SELECT a.requete FROM  SqlRequest a WHERE a.libelle=:libelle");
		query.setParameter("libelle", libelle);
		return (String) query.getSingleResult();
	}

	//Optimisation a faire , fusionner les requetes en une seule 
	@Override
	public void executeUpdate(String codeMag) {
		Query query = em.createNativeQuery("update article set SI_MODIFIER = false, SI_NOUVEAU = false,SI_PRIX_MODIFIER = false WHERE  CODE_MAGASIN_PK =:codeMag");
		query.setParameter("codeMag", codeMag);
		query.executeUpdate();
	}

	@Override
	public void executeUpdateSequence(String libelle, int req) {
		System.out.println("QUERY EXECUTE UPDATE");
		Query query = em.createNativeQuery("update sql_request set requete = "
				+ req + " where libelle ='" + libelle + "'");
		query.executeUpdate();
	}

	@Override
	public void resetStatDem() {
		Query query = em.createNativeQuery("TRUNCATE TABLE  stat_dem");
		query.executeUpdate();
	}

	@Override
	public void resetStatDem(String codeMag) {
		Query query = em
				.createNativeQuery("DELETE FROM stat_dem WHERE codeMag ='"
						+ codeMag + "'");
		query.executeUpdate();
	}

	@Override
	public void resetParamStatDem() {
		Query query = em
				.createNativeQuery("TRUNCATE TABLE  param_mouvement_stat_dem");
		query.executeUpdate();
	}

	@Override
	public void executeSql(String sql) {
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void addInconnuGencode(String gencode, String codeMag) {
		Query query = em
				.createNativeQuery("INSERT INTO gencode_inconnu(gencode,magasin_fk) VALUES ('"
						+ gencode+ "','"+ codeMag+ "')");
		query.executeUpdate();
	}

	/* PROPHYL.COM */
	@SuppressWarnings("unchecked")
	@Override
	public String getAllNewGenCodeToDay(String codeMag, String lineSeparator) {
		List<GenCode> list = null;
		String result = "Code-Barres :" + lineSeparator
				+ "	SECTEUR	RAYON	CODE-BARRES		CODE ARTICLE	DESIGNATION";
		try {
			String q = "SELECT g.* " +
					"FROM " +
					"gpv3000.gencode g " +
					"JOIN " +
					"gpv3000.article a " +
					"ON " +
					"a.CODE_ARTICLE_PK = g.CODE_ARTICLE_FK " +
					"AND " +
					"a.CODE_MAGASIN_PK = g.CODE_MAGASIN_FK " +
					"WHERE " +
					"DATE(g.DATE_CREATION)=CURRENT_DATE " +
					"AND " +
					"g.MODE_GENCODE='PRINCIPAL' " +
					"AND " +
					"g.ORIGINE_GENCODE='MAGASIN'" +
					"ORDER BY" +
					" a.SECTEUR_FK,a.RAYON_FK;";
			Query query = em.createNativeQuery(q, GenCode.class);

			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (GenCode genCode : list) {
			result += lineSeparator + genCode.info();
		}
		return result;
	}

}
