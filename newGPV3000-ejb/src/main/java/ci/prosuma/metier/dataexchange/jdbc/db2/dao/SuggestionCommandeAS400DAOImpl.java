package ci.prosuma.metier.dataexchange.jdbc.db2.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import ci.prosuma.metier.dataexchange.jdbc.db2.IJDBCConnectionAS400;
import ci.prosuma.metier.dataexchange.jdbc.mysql.IJDBCConnectionMySQL;
//import ci.prosuma.metier.dataexchange.jms.IGPV3000APPJMSSender;
import ci.prosuma.prosumagpv.entity.commande.DetailSuggestionCommande;
import ci.prosuma.prosumagpv.entity.commande.EnteteSuggestionCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatDossierSuggestion;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeDossier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.Query;

@Stateless
@Local(ISuggestionCommandeAS400DAO.class)
public class SuggestionCommandeAS400DAOImpl implements
		ISuggestionCommandeAS400DAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(SuggestionCommandeAS400DAOImpl.class);

	@PersistenceContext
	EntityManager em;

	@EJB
	private static IJDBCConnectionAS400 jdbcAS400Connection;

	@EJB
	private static IJDBCConnectionMySQL jdbcMySQLConnection;

//	@EJB
//	private IGPV3000APPJMSSender suggestionJMSSender;

	@SuppressWarnings("unused")
	private static ResultSet cursorEnteteSuggestionCommande;

	@SuppressWarnings("unused")
	private static ResultSet cursorDetailSuggestionCommande;

        String driverAS400 = null;
	String urlAS400 = null;
        
        private String getSqlRequestByLibelle(String libelle) {
		Query query = em.createQuery("SELECT a.requete FROM  SqlRequest a   WHERE a.libelle=:libelle");
		query.setParameter("libelle", libelle);
		return (String) query.getSingleResult();
	}
        
        public void initialize() {
		try {
			driverAS400 = getSqlRequestByLibelle("as400.driver");
			urlAS400 = getSqlRequestByLibelle("as400.url");
			Class.forName(driverAS400);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String request) {
		initialize();
		Connection conn = null;
		Statement state = null;
		ResultSet resultQuery = null;
		try {
			conn = DriverManager.getConnection(urlAS400);
			state = conn.createStatement();
			resultQuery = state.executeQuery(request);

			return resultQuery;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
        @Override
	public Map<String, EnteteSuggestionCommande> allOneEnteteSuggestionCommande(String mag) {
		Map<String, EnteteSuggestionCommande> mapEntete = new HashMap<>();
		Map<String, List<DetailSuggestionCommande>> mapDetails = new HashMap<>();
		System.out.println("Init Map");
		String query ;
		try{
			query = getSqlRequestByLibelle("suggestion.all.one");
			System.out.println("############    query  : "+query);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		System.out.println("Query : " + query);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -30);
		String requestQuery = query.replaceAll(":date", sdf.format(calendar.getTime())).replaceAll(":numMag","'"+mag+"'");
		System.out.println("############    requestQuery  : "+requestQuery);
		ResultSet cursor = executeQuery(requestQuery);
		if (cursor == null)
			return null;
		try {
			while (cursor.next()) {
				String numD = cursor.getString("NUMOEE");
				if (!mapEntete.containsKey(numD)) {
					EnteteSuggestionCommande esc = new EnteteSuggestionCommande();
					List<DetailSuggestionCommande> lists = new ArrayList<>();
					esc.setPvtCode(cursor.getString("CPVTEM"));
					esc.setRayon(cursor.getString("CSECEE")+ cursor.getString("CRAYEE"));
					esc.setSecteur(cursor.getString("CSECEE"));
					esc.setNumDossier(numD);
					esc.setDateSuggestion(sdf.parse(cursor.getString("DAMCEE")));
					esc.setObservation(cursor.getString("LI40EE"));
					esc.setDateLivaison(sdf.parse(cursor.getString("DAMLEE")));
					esc.setUtilisateurCrea(cursor.getString("UTCREE"));
					esc.setDateLimiteEnvoi(sdf.parse(cursor.getString("DALCEE")));
					esc.setTypeAppro(cursor.getString("CMETEE"));
					String mode = cursor.getString("MODLEE");
					if (mode != null && mode.equals("C")) {
						esc.setModeDossier(ModeDossier.CENTRAL);
					}
					if (mode != null && mode.equals("D")) {
						esc.setModeDossier(ModeDossier.LIVRAISON_DIRECT);
					}
					if (mode != null && mode.equals("P")) {
						esc.setModeDossier(ModeDossier.PREALLOTI);
					}
					if (cursor.getString("CTHEEE") != null || !cursor.getString("CTHEEE").equals("")) {
						esc.setPromo(cursor.getString("CTHEEE"));
					}
					String etatDossier = cursor.getString("ETACEE");
					//modifié par Ferdinand DIAHA
					/*if (etatDossier != null && etatDossier.equals("2")) {
						esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
					}*/
					if (etatDossier != null && (etatDossier.equals("2") || etatDossier.equals("4"))) {
						esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
					}
					if (cursor.getString("NFOUEE") != null) {
						esc.setFournisseur(cursor.getString("NFOUEE"));
					}
					lists.add(getDetailSuggestionCommande(cursor));
					mapEntete.put(numD, esc);
					mapDetails.put(numD,lists );
				} else {
					mapDetails.get(numD).add(getDetailSuggestionCommande(cursor));
				}
			}
			
			for (String key : mapEntete.keySet()) {
				EnteteSuggestionCommande esc = mapEntete.get(key);
				esc.setDetailSuggestionCommande(mapDetails.get(key));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapEntete;
	}

	private DetailSuggestionCommande getDetailSuggestionCommande(ResultSet rs) {
		DetailSuggestionCommande dsc = new DetailSuggestionCommande();
		try {
			try {
				dsc.setColisage(Integer.parseInt(rs.getString("NPCBED")));
			} catch (Exception e) {
				dsc.setColisage(0);
			}
			try {
				dsc.setDelaisVenteJours(Integer.parseInt(rs.getString("DLVJED")));
			} catch (Exception e) {
				dsc.setDelaisVenteJours(0);
			}
			String code = rs.getString("NARTED");
			long codeLong = Long.parseLong(code);
			DecimalFormat df = new DecimalFormat("000000000");
			dsc.setCodeArticle(df.format(codeLong));

			try {
				dsc.setPfUnitaireTTC(Float.parseFloat(rs.getString("PUFTED")));
			} catch (Exception e) {
				e.printStackTrace();
				dsc.setPfUnitaireTTC(0);
			}
			try {
				dsc.setPvUnitaireTTC(Float.parseFloat(rs.getString("PUVTED")));
			} catch (Exception e) {
				e.printStackTrace();
				dsc.setPvUnitaireTTC(0);
			}

			dsc.setPoidsVariable(rs.getString("SIPVED").equals("0") ? false
					: true);
			dsc.setQteUCCommander(Float.parseFloat(rs.getString("QTUCED")));
			dsc.setQteUCSuggerer(Float.parseFloat(rs.getString("QTUSED")));
			dsc.setQteVLSuggerer(Float.parseFloat(rs.getString("QTVSED")));
			dsc.setQteVLCommander(Float.parseFloat(rs.getString("QTVLED")));
			return dsc;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public ResultSet InitCursorEnteteSuggestionCommande(String mag) {
		System.out.println("Init Cursor");
		String query = getSqlRequestByLibelle("suggestion.entete.commande.tma");
		System.out.println("Query : " + query);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -30);
		ResultSet cursor = executeQuery(query.replaceAll(
				":date", sdf.format(calendar.getTime())).replaceAll(":numMag",
				mag));
		return cursor;
	}

	public EnteteSuggestionCommande nextEnteteSuggestionCommande(String mag,
			ResultSet cursor) {
		try {
			if (cursor == null)
				return null;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			EnteteSuggestionCommande esc = new EnteteSuggestionCommande();

			if (!cursor.getString("CPVTEM").equals(mag)) {
				System.err.println("Error ");
				return null;
			}

			esc.setPvtCode(cursor.getString("CPVTEM"));
			esc.setRayon(cursor.getString("CSECEE")
					+ cursor.getString("CRAYEE"));
			esc.setSecteur(cursor.getString("CSECEE"));
			esc.setNumDossier(cursor.getString("NUMOEE"));
			esc.setDateSuggestion(sdf.parse(cursor.getString("DAMCEE")));
			esc.setObservation(cursor.getString("LI40EE"));
			esc.setDateLivaison(sdf.parse(cursor.getString("DAMLEE")));
			esc.setUtilisateurCrea(cursor.getString("UTCREE"));
			esc.setDateLimiteEnvoi(sdf.parse(cursor.getString("DALCEE")));
			esc.setTypeAppro(cursor.getString("CMETEE"));
			String mode = cursor.getString("MODLEE");
			if (mode != null && mode.equals("C")) {
				esc.setModeDossier(ModeDossier.CENTRAL);
			}
			if (mode != null && mode.equals("D")) {
				esc.setModeDossier(ModeDossier.LIVRAISON_DIRECT);
			}
			if (mode != null && mode.equals("P")) {
				esc.setModeDossier(ModeDossier.PREALLOTI);
			}

			if (cursor.getString("CTHEEE") != null
					|| !cursor.getString("CTHEEE").equals("")) {
				esc.setPromo(cursor.getString("CTHEEE"));
			}
			String etatDossier = cursor.getString("ETACEE");
			/*if (etatDossier != null && etatDossier.equals("2")) {
				esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
			}*/
			if (etatDossier != null && (etatDossier.equals("2") || etatDossier.equals("4"))) {
				esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
			}
			if (cursor.getString("NFOUEE") != null) {
				esc.setFournisseur(cursor.getString("NFOUEE"));
			}
			esc.setDetailSuggestionCommande(getDetailForCommandeAS400(
					esc.getNumDossier(), esc.getPvtCode()));
			System.out.println("Others one");
			return esc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void InitCursorDetailSuggestionCommande(String numDossier,
			String mag) {
		String query = jdbcMySQLConnection
				.getSqlRequestByLibelle("suggestion.detail.commande");
		cursorDetailSuggestionCommande = jdbcAS400Connection.executeQuery(query
				.replaceAll(":numDossier", numDossier).replaceAll(":mag",
						"'" + mag + "'"));
	}

	@Override
	public List<EnteteSuggestionCommande> getAllSuggestionDeCommandeAvailable(
			String mag) {
		System.out.println("MAG : |" + mag + "|");
		try {
			String query = getSqlRequestByLibelle("suggestion.entete.commande");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, -30);

			ResultSet rs = executeQuery(query.replaceAll(
					":date", sdf.format(calendar.getTime())));
			// query = query.replaceAll(":date","20111120");
			// System.out.println("query : "+query);
			// ResultSet rs = jdbcAS400Connection.executeQuery(query);

			List<EnteteSuggestionCommande> listEntete = new ArrayList<EnteteSuggestionCommande>();

			while (rs.next()) {
				EnteteSuggestionCommande esc = new EnteteSuggestionCommande();
				if (!rs.getString("CPVTEM").equals(mag))
					continue;

				esc.setPvtCode(rs.getString("CPVTEM"));
				esc.setRayon(rs.getString("CSECEE") + rs.getString("CRAYEE"));
				esc.setSecteur(rs.getString("CSECEE"));
				esc.setNumDossier(rs.getString("NUMOEE"));
				esc.setDateSuggestion(sdf.parse(rs.getString("DAMCEE")));
				esc.setObservation(rs.getString("LI40EE"));
				esc.setDateLivaison(sdf.parse(rs.getString("DAMLEE")));
				esc.setUtilisateurCrea(rs.getString("UTCREE"));
				esc.setDateLimiteEnvoi(sdf.parse(rs.getString("DALCEE")));
				esc.setTypeAppro(rs.getString("CMETEE"));
				String mode = rs.getString("MODLEE");
				if (mode != null && mode.equals("C")) {
					esc.setModeDossier(ModeDossier.CENTRAL);
				}
				if (mode != null && mode.equals("D")) {
					esc.setModeDossier(ModeDossier.LIVRAISON_DIRECT);
				}
				if (mode != null && mode.equals("P")) {
					esc.setModeDossier(ModeDossier.PREALLOTI);
				}

				if (rs.getString("CTHEEE") != null
						|| !rs.getString("CTHEEE").equals("")) {
					esc.setPromo(rs.getString("CTHEEE"));
				}
				String etatDossier = rs.getString("ETACEE");
				//Modifié par Ferdinand DIAHA -- original ci dessous commenté
				/*if (etatDossier != null && etatDossier.equals("2")) {
					esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
				}*/
				
				if (etatDossier != null && (etatDossier.equals("2") || etatDossier.equals("4"))) {
					esc.setEtatDossier(EtatDossierSuggestion.TRANSMIT_MAGASIN);
				}
				
				if (rs.getString("NFOUEE") != null) {
					esc.setFournisseur(rs.getString("NFOUEE"));
				}

				esc.setDetailSuggestionCommande(getDetailForCommandeAS400(
						esc.getNumDossier(), esc.getPvtCode()));
				listEntete.add(esc);
				System.out.println("jai add un");

			}

			return listEntete;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<DetailSuggestionCommande> getDetailForCommandeAS400(
			String numDossier, String mag) {

		try {
			String query = getSqlRequestByLibelle("suggestion.detail.commande");

			ResultSet rs = executeQuery(query.replaceAll(
					":numDossier", numDossier).replaceAll(":mag",
					"'" + mag + "'"));
			List<DetailSuggestionCommande> entList = new ArrayList<DetailSuggestionCommande>();
			while (rs.next()) {

				DetailSuggestionCommande dsc = new DetailSuggestionCommande();

				try {
					dsc.setColisage(Integer.parseInt(rs.getString("NPCBED")));
				} catch (Exception e) {
					dsc.setColisage(0);
				}
				try {
					dsc.setDelaisVenteJours(Integer.parseInt(rs
							.getString("DLVJED")));
				} catch (Exception e) {
					dsc.setDelaisVenteJours(0);
				}
				String code = rs.getString("NARTED");
				long codeLong = Long.parseLong(code);
				DecimalFormat df = new DecimalFormat("000000000");
				dsc.setCodeArticle(df.format(codeLong));

				try {
					dsc.setPfUnitaireTTC(Float.parseFloat(rs
							.getString("PUFTED")));
				} catch (Exception e) {
					e.printStackTrace();
					dsc.setPfUnitaireTTC(0);
				}
				try {
					dsc.setPvUnitaireTTC(Float.parseFloat(rs
							.getString("PUVTED")));
				} catch (Exception e) {
					e.printStackTrace();
					dsc.setPvUnitaireTTC(0);
				}

				dsc.setPoidsVariable(rs.getString("SIPVED").equals("0") ? false
						: true);
				dsc.setQteUCCommander(Float.parseFloat(rs.getString("QTUCED")));
				dsc.setQteUCSuggerer(Float.parseFloat(rs.getString("QTUSED")));
				dsc.setQteVLSuggerer(Float.parseFloat(rs.getString("QTVSED")));
				dsc.setQteVLCommander(Float.parseFloat(rs.getString("QTVLED")));

				entList.add(dsc);
			}

			return entList;
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();

		}
		return null;

	}

	@SuppressWarnings("unused")
	private void sendSuggestionToApp(List<Serializable> listEntete) {
//		if (listEntete != null && listEntete.size() > 0) {
//			logger.info("debut envoi Suggestion commande: " + listEntete.size());
//			suggestionJMSSender.sendEntity(listEntete);
//		}
	}

}
