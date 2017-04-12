package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ci.prosuma.metier.dataexchange.jdbc.mssql.IJDBCConnectionMSSQL;
import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.GenCode;
import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.CategorieGenCode;
import ci.prosuma.prosumagpv.entity.util.TypeMouvementStock;
import ci.prosuma.prosumagpv.entity.vente.VenteCaisse;
import ci.prosuma.prosumagpv.metier.dao.jdbc.IJDBCGPV3000MySQLConnection;
import ci.prosuma.prosumagpv.metier.dao.mysql.IArticleDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteMouvementDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IGenCodeDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPromoDAO;
import ci.prosuma.prosumagpv.metier.dao.mysql.IVenteCaisseDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeMouvementStockDAO;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeOrigineMouvementDAO;
import ci.prosuma.prosumagpv.metier.service.IArticleService;
import ci.prosuma.prosumagpv.metier.service.IUtilService;
import ci.prosuma.prosumagpv.metier.service.IWinstore;

@SuppressWarnings("serial")
@Local(IWinstore.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class WinstoreImpl implements IWinstore, Serializable {

	@Resource
	UserTransaction tx;

	@EJB
	private ITypeOrigineMouvementDAO origineMvmtnDAO;

	@EJB
	private ITypeMouvementStockDAO mouvStockTypeDAO;

	@EJB
	private IEnteteMouvementDAO enteteMouvementDAO;

	@EJB
	private IPointDeVenteDAO pointDeVenteDAO;

	@EJB
	private IJDBCConnectionMSSQL mssqlConnection;

	@EJB
	private IArticleDAO articleDAO;

	@EJB
	private IJDBCGPV3000MySQLConnection requestDAO;

	@EJB
	private IArticleService articleService;

	@EJB
	private IPromoDAO promoDAO;

	// @EJB
	// private IClientService clientService;

	@EJB
	private IVenteCaisseDAO venteCaisseDAO;

	@EJB
	private IGenCodeDAO genCodeDAO;

	@EJB
	private IUtilService utilService;

	private String query;
	private String query2;
	private String query3;
	ArrayList<String> listGenerique;

	public WinstoreImpl() {

	}

	private void initVar() {
		query = utilService.getSqlRequestByLibelle("winstore.request1");
		query2 = utilService.getSqlRequestByLibelle("winstore.request2");
		query3 = utilService.getSqlRequestByLibelle("winstore.request3");
		listGenerique = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(
				utilService.getSqlRequestByLibelle("liste.generique"), ";");

		while (tokenizer.hasMoreTokens()) {
			listGenerique.add(tokenizer.nextToken());
		}

	}

	private void comWithWinstore(Article a, String ip, String pvtCode,
			List<GenCode> genCodeList, Connection conn) {
		if (query == null || query2 == null || query3 == null
				|| listGenerique == null)
			initVar();

		Statement state = null;

		try {

			state = conn.createStatement();

			if (a.getPrixVenteTTCEnCours() == 0
					&& !listGenerique.contains(a.getCodeArticle())) {
				return;
			}

			StringBuilder sb1 = new StringBuilder(query);
			StringBuilder sb2 = new StringBuilder(query2);
			StringBuilder sb3 = new StringBuilder(query3);

			sb1.append(Long.parseLong(a.getCodeArticle()) + ",");
			sb1.append("'" + a.getCodeArticle() + "'" + ",");

			if (genCodeList != null) {
				if (genCodeList.size() == 1) {
					GenCode gc = genCodeList.get(0);
					sb1.append("'" + gc.getCode() + gc.getCaractereControle()
							+ "'" + ",");
				}
				if (genCodeList.size() > 1) {
					boolean setter = false;
					for (GenCode gc : genCodeList) {
						if (!setter) {
							sb1.append("'" + gc.getCode()
									+ gc.getCaractereControle() + "'" + ",");
							setter = true;
						} else {
							sb3.append("'" + gc.getCode()
									+ gc.getCaractereControle() + "'" + ",");
							sb3.append(Long.parseLong(a.getCodeArticle()) + ")");

							try {
								tx.begin();

								state.executeUpdate(sb3.toString());
								sb3 = new StringBuilder(query3);
								tx.commit();
							} catch (Exception e) {
								try {
									tx.rollback();
								} catch (IllegalStateException e1) {
									e1.printStackTrace();
								} catch (SecurityException e1) {
									e1.printStackTrace();
								} catch (SystemException e1) {
									e1.printStackTrace();
								}
								e.printStackTrace();
							}

						}
					}
				}

			}
			if (genCodeList == null || genCodeList.size() < 1) {
				sb1.append("''" + ",");
			}

			if (a.getDesignation().length() <= 20) {
				sb1.append("'" + a.getDesignation().replaceAll("'", "''") + "'"
						+ ",");
				sb1.append("'" + a.getDesignation().replaceAll("'", "''") + "'"
						+ ",");
			} else {
				sb1.append("'"
						+ (a.getDesignation().substring(0, 19)).replaceAll("'",
								"''") + "'" + ",");
				sb1.append("'"
						+ (a.getDesignation().substring(0, 19)).replaceAll("'",
								"''") + "'" + ",");
			}
			sb1.append("'"
					+ (a.getFamille() == null ? a.getRayon() : a.getFamille())
					+ "'" + ",");
			sb1.append("0" + ",");
			sb1.append("'" + 0 + "'" + ",");
			sb1.append("0" + ",");
			sb1.append((a.getTauxTVA() == null ? 0 : Integer.parseInt(a
					.getTauxTVA())) + ",");
			sb1.append("0" + ",");

			if (null != a.getPromo()) {
				Promo p = promoDAO.getPromo(a.getPromo());
				if (p.isEnVente()) {
					sb1.append(a.getPrixVentePromoTTC() + ",");
				} else {
					sb1.append(a.getPrixVenteTTCEnCours() + ",");
				}
			} else {
				if (listGenerique.contains(a.getCodeArticle())) {
					sb1.append(0 + ",");
				} else {
					sb1.append(a.getPrixVenteTTCEnCours() + ",");
				}

			}

			sb1.append("0" + ",");
			sb1.append("''" + ",");
			sb1.append("''" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sb1.append("'" + sdf.format(new Date(System.currentTimeMillis()))
					+ "'" + ",");
			sb1.append("0" + ",");
			sb1.append("0" + ",");
			sb1.append("''" + ",");
			sb1.append("''" + ")");

			if (a.getArticeConsigne() != null
					&& !a.getArticeConsigne().trim().equals("000000000")) {
				sb2.append(Long.parseLong(a.getCodeArticle()) + ",");
				sb2.append(Long.parseLong(a.getArticeConsigne()) * -1 + ",");
				sb2.append("-1" + ")");
				try {
					tx.begin();
					state.executeUpdate(sb2.toString());
					tx.commit();
				} catch (Exception e) {
					try {
						tx.rollback();
					} catch (IllegalStateException e1) {
						e1.printStackTrace();
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (SystemException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}

			try {
				tx.begin();
				state.executeUpdate(sb1.toString());
				tx.commit();
			} catch (Exception e) {
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (SystemException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				if (state != null)
					state.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void uploadToTPVReset(String ip, String pvtCode) {
		Connection conn = null;
		try {
			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			String driverMSSQL = requestDAO
					.getSqlRequestByLibelle("mssql.driver");
			String urlMSSQL = requestDAO.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out
					.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll(
					"ipServeurCaisse", ip));
			// vider les tables
			truncateAllTables(ip, conn);
			for (String s : articleDAO.getAllCodeArticleForMag(pvtCode)) {
				uploadArtToTPV(s, pvtCode, ip, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public void uploadPromoToTPV(String pvtCode, String ip, String promo) {
		Connection conn = null;
		try {

			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			String driverMSSQL = requestDAO
					.getSqlRequestByLibelle("mssql.driver");
			String urlMSSQL = requestDAO.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out
					.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll(
					"ipServeurCaisse", ip));

			for (Article s : articleDAO.getAllArticleForPromoAndPVT(promo,
					pvtCode)) {
				uploadArtToTPV(s.getCodeArticle(), pvtCode, ip, conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public void uploadUpdateToTPV(String pvtCode, String ip) {
		Connection conn = null;
		try {

			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			String driverMSSQL = requestDAO
					.getSqlRequestByLibelle("mssql.driver");
			String urlMSSQL = requestDAO.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out
					.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll(
					"ipServeurCaisse", ip));
			for (String s : articleDAO.getAllCodeArticleForMagUpdated(pvtCode)) {
				System.out.println("upload update to tpv" + s);
				uploadArtToTPV(s, pvtCode, ip, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public void uploadArticleToTPV(String s, String pvtCode, String ip) {
		Connection conn = null;
		try {

			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			String driverMSSQL = requestDAO
					.getSqlRequestByLibelle("mssql.driver");
			String urlMSSQL = requestDAO.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out
					.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll(
					"ipServeurCaisse", ip));
			uploadArtToTPV(s, pvtCode, ip, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public void uploadArtToTPV(String s, String pvtCode, String ip,
			Connection conn) {
		System.out.println("upload art to ");
		Article a = articleDAO.getArticle(new ArticleMagRefPK(pvtCode, s));
		if (a != null) {
			System.out.println("a diuff de null");

			List<GenCode> genCodeList = genCodeDAO.getAllGenCodeForArticle(
					a.getCodeArticle(), a.getPvtCode());
			List<GenCode> result = new ArrayList<GenCode>();

			if (genCodeList != null && genCodeList.size() > 0) {
				for (GenCode g : genCodeList) {
					if (g.getCatGen().equals(CategorieGenCode.PRINCIPAL)
							|| g.getCatGen().equals(CategorieGenCode.DLVM)) {
						result.add(g);
					}
					if (g.getCatGen().equals(CategorieGenCode.SECONDAIRE)
							|| g.getCatGen().equals(CategorieGenCode.GROS)
							|| g.getCatGen().equals(CategorieGenCode.RCOND)
							|| g.getCatGen().equals(CategorieGenCode.SCOND)) {
						System.out.println("CB SC");
						Article temp = new Article();
						temp.setCodeArticle(g.getCode().substring(0, 4)
								+ g.getCode().substring(7, 12));
						temp.setPvtCode(a.getPvtCode());
						temp.setArticeConsigne(a.getArticeConsigne());
						temp.setRayon(a.getRayon());
						temp.setFamille(a.getFamille());
						temp.setDesignation(a.getDesignation());
						temp.setPrixReviensTTCEnCours(g
								.getPrixReviensTTCEnCours());
						temp.setPrixVenteTTCEnCours(g.getPrixVenteTTCEnCours());
						temp.setPromo(null);
						temp.setTauxTVA(a.getTauxTVA());
						List<GenCode> tempResult = new ArrayList<GenCode>();
						tempResult.add(g);
						deleteArt(ip, temp, pvtCode, tempResult, conn);
						comWithWinstore(temp, ip, pvtCode, tempResult, conn);
					}
				}
			}
			deleteArt(ip, a, pvtCode, result, conn);
			comWithWinstore(a, ip, pvtCode, result, conn);
		}
	}

	@Override
	public void uploadToTPV(String ip, String pvtCode) {
		Connection conn = null;
		try {

			System.out.println("INITIALIZE JDBCConnectionMSSQL");
			String driverMSSQL = requestDAO
					.getSqlRequestByLibelle("mssql.driver");
			String urlMSSQL = requestDAO.getSqlRequestByLibelle("mssql.url");
			Class.forName(driverMSSQL);
			System.out
					.println("INITIALIZE JDBCConnectionMSSQL class for name ");
			conn = DriverManager.getConnection(urlMSSQL.replaceAll(
					"ipServeurCaisse", ip));
			for (String s : articleDAO.getAllCodeArticleForMag(pvtCode)) {
				uploadArtToTPV(s, pvtCode, ip, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public String downoadFromTPV(String mag, String ip, String date)
			throws Exception {

		try {
			boolean plu = false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String queryTemp = "SELECT c FROM EnteteMouvement c WHERE c.pvt.pvtCode='"
					+ mag
					+ "' AND c.dateMouvement = '"
					+ date
					+ "' AND c.origineMouvement = 'CAISS'";
			System.out.println(queryTemp);
			List<EnteteMouvement> listEM = enteteMouvementDAO
					.executeQuery(queryTemp);
			System.out.println("LIST EME EXIST " + listEM);
			if (listEM != null && listEM.size() > 0) {
				return "EXIST";
			}
			tx.begin();
			PointDeVente pvt = pointDeVenteDAO.getPVT(mag);
			Depot depot = pointDeVenteDAO.getDepotPrincipalForMag(pvt
					.getPvtCode());
			String query = requestDAO.getSqlRequestByLibelle("winstore.ticket");
			query = query.replaceAll("dateInput", date);
			ResultSet rs = mssqlConnection.executeQuery(ip, query);
			EnteteMouvement em = new EnteteMouvement();
			em.setDateMouvement(sdf.parse(date));
			em.setObservations("REMONTEE DE CAISSE DU " + date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("CAISS"));
			em.setPvt(pvt);
			em.setUserCreation("GPV3000");

			List<DetailMouvement> listDm = new ArrayList<DetailMouvement>();
			DecimalFormat df = new DecimalFormat("000000000");

			TypeMouvementStock tms = mouvStockTypeDAO
					.getTypeMouvementStock("C-");
			TypeMouvementStock tms2 = mouvStockTypeDAO
					.getTypeMouvementStock("C+");

			List<VenteCaisse> listVente = new ArrayList<VenteCaisse>();
			double priceTotal = 0;
			double priceTotalRemb = 0;
			DecimalFormat dfG = new DecimalFormat("000000000000");
			while (rs.next()) {
				int typeLigne = rs.getInt("TypeLigne");
				String code1 = rs.getString("Libelle1");
				int codeArticle = rs.getInt("CodeInterne");
				int qte = rs.getInt("Qte");
				double prixVenteArt = rs.getFloat("PrixVenteArt");
				prixVenteArt = Math.floor(prixVenteArt + 0.5);
				plu = false;
				GenCode gc = null;
				Article a = null;
				code1 = code1.trim();

				if (typeLigne == 1) {
					if (code1 != null && !code1.equals("")
							&& !code1.equals("0")) {
						String gcString = dfG.format(Long.parseLong(code1
								.substring(0, code1.length() - 1)));
						gc = genCodeDAO.getGenCodeByCodeAndPVT(gcString, mag);

					}

					if (gc == null) {
						a = articleDAO.getArticle(new ArticleMagRefPK(mag, df
								.format(codeArticle)));
					} else {
						a = gc.getArticle();
					}

					if (null != a
							&& null != gc
							&& null != gc.getCatGen()
							&& (gc.getCatGen().equals(CategorieGenCode.SCOND)
									|| gc.getCatGen().equals(
											CategorieGenCode.RCOND) || gc
									.getCatGen().equals(CategorieGenCode.GROS))) {
						plu = true;
						createMouvementForGcMinus(a, gc, qte, a.getStock()
								.getDepot(), date);
						createMouvementForGcPlus(a, gc, qte, a.getStock()
								.getDepot(), date);
					}

					priceTotal = priceTotal + (prixVenteArt * qte);
					VenteCaisse vc = new VenteCaisse();
					vc.setCodeArticle(codeArticle + "");
					vc.setCodeMagasin(pvt.getPvtCode());
					vc.setDateVente(em.getDateMouvement());
					vc.setNbrArticleVendu(qte);
					vc.setMontantArticleVendu((long) prixVenteArt);
					vc.setRetour(false);
					vc.setMontantTotal((long) prixVenteArt * qte);
					if (a != null) {
						vc.setSecteur(a.getSecteur());
						vc.setRayon(a.getRayon());
					} else {
						vc.setSecteur("XX");
						vc.setRayon("XXXXX");
					}

					listVente.add(vc);

					if (a != null) {
						DetailMouvement dm = new DetailMouvement();
						if (plu) {
							dm.setGcCode(gc.getCode());
							dm.setQteMvtUc(gc.getColisage() * qte);
						}
						dm.setArticle(a);
						dm.setDepot(depot);
						dm.setObservations("VENTE ARTICLE : " + codeArticle
								+ " QTE : " + qte + " PV : " + prixVenteArt);
						dm.setQteMvt(qte);
						dm.setTypeMouvement(tms);
						dm.setDateMouvement(em.getDateMouvement());
						if (null != a.getPromo()) {
							Promo p = promoDAO.getPromo(a.getPromo());
							if (p.isEnFacturation()) {
								dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							} else {
								dm.setArtPrixReviens(a
										.getPrixReviensTTCEnCours());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							}
						} else {
							dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
							dm.setArtPrixReviensHT((long) articleService
									.calculateHTForPrice(a.getTauxTVA(),
											dm.getArtPrixReviens()));
						}
						dm.setArtPrixVente((long) prixVenteArt);
						dm.setArtPrixVenteHT((long) articleService
								.calculateHTForPrice(a.getTauxTVA(),
										dm.getArtPrixVente()));

						dm.setQtePhysiqueAvantMouvement(a.getStock()
								.getQteComptable());
						dm.setSens(Integer.parseInt(tms.getSens()));
						dm.setThemePromo(a.getPromo());

						listDm.add(dm);

					}

				}

				if (typeLigne == 2) {
					a = articleDAO.getArticle(new ArticleMagRefPK(mag, df
							.format(codeArticle)));
					priceTotalRemb = priceTotalRemb + (prixVenteArt * qte);

					VenteCaisse vc = new VenteCaisse();
					vc.setCodeArticle(codeArticle + "");
					vc.setCodeMagasin(pvt.getPvtCode());
					vc.setDateVente(em.getDateMouvement());
					vc.setNbrArticleRembourser(qte);
					vc.setMontantArticleRembourser((long) prixVenteArt);
					vc.setRetour(true);
					vc.setMontantTotal((long) (prixVenteArt * qte));
					if (a != null) {
						vc.setSecteur(a.getSecteur());
						vc.setRayon(a.getRayon());
					} else {
						vc.setSecteur("XX");
						vc.setRayon("XXXXX");
					}
					listVente.add(vc);

					if (a != null) {
						DetailMouvement dm = new DetailMouvement();
						dm.setArticle(a);

						if (null != a.getPromo()) {
							Promo p = promoDAO.getPromo(a.getPromo());
							if (p.isEnFacturation()) {
								dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							} else {
								dm.setArtPrixReviens(a
										.getPrixReviensTTCEnCours());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							}
						} else {
							dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
							dm.setArtPrixReviensHT((long) articleService
									.calculateHTForPrice(a.getTauxTVA(),
											dm.getArtPrixReviens()));
						}
						dm.setArtPrixVente((long) prixVenteArt);
						dm.setArtPrixVenteHT((long) articleService
								.calculateHTForPrice(a.getTauxTVA(),
										dm.getArtPrixVente()));

						dm.setDepot(depot);
						dm.setObservations("RETOUR ARTICLE : " + codeArticle
								+ " QTE : " + qte + " PV : " + prixVenteArt);
						dm.setQteMvt(Math.abs(qte));
						dm.setTypeMouvement(tms2);
						dm.setDateMouvement(em.getDateMouvement());
						if (null != a.getPromo()) {
							Promo p = promoDAO.getPromo(a.getPromo());
							if (p.isEnFacturation()) {
								dm.setArtPrixReviens(a.getPrixReviensPromoTTC());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							} else {
								dm.setArtPrixReviens(a
										.getPrixReviensTTCEnCours());
								dm.setArtPrixReviensHT((long) articleService
										.calculateHTForPrice(a.getTauxTVA(),
												dm.getArtPrixReviens()));
							}
						} else {
							dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
							dm.setArtPrixReviensHT((long) articleService
									.calculateHTForPrice(a.getTauxTVA(),
											dm.getArtPrixReviens()));
						}
						dm.setArtPrixVente((long) prixVenteArt);
						dm.setArtPrixVenteHT((long) articleService
								.calculateHTForPrice(a.getTauxTVA(),
										dm.getArtPrixVente()));
						dm.setQtePhysiqueAvantMouvement(a.getStock()
								.getQteComptable());
						dm.setSens(Integer.parseInt(tms2.getSens()));
						dm.setThemePromo(a.getPromo());

						listDm.add(dm);

					}
				}
			}

			em.setMouvements(listDm);

			enteteMouvementDAO.createOrUpdateEnteteMouvement(em);

			for (VenteCaisse vc : listVente) {
				venteCaisseDAO.createOrUpdateVenteCaisse(vc);
			}

			tx.commit();
			return "SUCCESS";
		} catch (Exception e) {

			e.printStackTrace();
			tx.rollback();
			return "FAILURE";
		}

	}

	public void createMouvementForGcMinus(Article a, GenCode gc, float qte,
			Depot d, String date) {
		EnteteMouvement em = new EnteteMouvement();
		em.setDateMouvement(new Date(System.currentTimeMillis()));
		em.setPvt(pointDeVenteDAO.getPVT(a.getPvtCode()));
		em.setUserCreation("GPV3000");
		DetailMouvement dm = new DetailMouvement();
		dm.setArticle(a);
		dm.setArtPrixReviens(a.getPrixReviensTTCEnCours());
		dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(
				a.getTauxTVA(), a.getPrixReviensTTCEnCours()));
		dm.setArtPrixVente(a.getPrixVenteTTCEnCours());
		dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(
				a.getTauxTVA(), a.getPrixVenteTTCEnCours()));
		dm.setCodeAnal(a.getCodeAnalytique());
		dm.setDateMouvement(em.getDateMouvement());
		dm.setDepot(d);
		dm.setDesignationArt(a.getDesignation());
		dm.setFamille(a.getFamille());
		dm.setQteMvt(gc.getColisage() * qte);
		dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
		dm.setRayon(a.getRayon());
		dm.setSecteur(a.getSecteur());
		dm.setSens(-1);
		dm.setSousFamille(a.getSousFamille());

		if (gc.getCatGen().equals(CategorieGenCode.RCOND)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  RCOND -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("RCOND"));
			dm.setObservations("MOUVEMENT RCOND- : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("R-"));
		}
		if (gc.getCatGen().equals(CategorieGenCode.SCOND)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  SCOND -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("SCOND"));
			dm.setObservations("MOUVEMENT SCOND- : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("S-"));
		}
		if (gc.getCatGen().equals(CategorieGenCode.GROS)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  GROS -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("GROS"));
			dm.setObservations("MOUVEMENT GROS- : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("G-"));
		}
		List<DetailMouvement> listDm = new ArrayList<DetailMouvement>();
		listDm.add(dm);
		em.setMouvements(listDm);
		em = enteteMouvementDAO.createOrUpdateEnteteMouvement(em);

	}

	public void createMouvementForGcPlus(Article a, GenCode gc, float qte,
			Depot d, String date) {
		EnteteMouvement em = new EnteteMouvement();
		em.setDateMouvement(new Date(System.currentTimeMillis()));
		em.setPvt(pointDeVenteDAO.getPVT(a.getPvtCode()));
		em.setUserCreation("GPV3000");
		DetailMouvement dm = new DetailMouvement();
		dm.setArticle(a);
		dm.setGcCode(gc.getCode());
		dm.setArtPrixReviens(gc.getPrixReviensTTCEnCours());
		dm.setArtPrixReviensHT((long) articleService.calculateHTForPrice(
				a.getTauxTVA(), gc.getPrixReviensTTCEnCours()));
		dm.setArtPrixVente(gc.getPrixVenteTTCEnCours());
		dm.setArtPrixVenteHT((long) articleService.calculateHTForPrice(
				a.getTauxTVA(), gc.getPrixVenteTTCEnCours()));
		dm.setCodeAnal(a.getCodeAnalytique());
		dm.setDateMouvement(em.getDateMouvement());
		dm.setDepot(d);
		dm.setDesignationArt(a.getDesignation());
		dm.setFamille(a.getFamille());
		dm.setQteMvt(qte);
		dm.setQtePhysiqueAvantMouvement(a.getStock().getQteComptable());
		dm.setRayon(a.getRayon());
		dm.setSecteur(a.getSecteur());
		dm.setSens(-1);
		dm.setSousFamille(a.getSousFamille());

		if (gc.getCatGen().equals(CategorieGenCode.RCOND)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  RCOND -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("RCOND"));
			dm.setObservations("MOUVEMENT RCOND+ : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("R+"));
		}
		if (gc.getCatGen().equals(CategorieGenCode.SCOND)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  SCOND -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("SCOND"));
			dm.setObservations("MOUVEMENT SCOND+ : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("S+"));
		}
		if (gc.getCatGen().equals(CategorieGenCode.GROS)) {
			// MOUVEMENT DEBITER ARTICLE PRINCIPAL
			em.setObservations("MOUVEMENT AJUSTEMENT  GROS -  REMOTE DE CAISSE DU "
					+ date);
			em.setOrigineMouvement(origineMvmtnDAO.getOrigineMouvement("GROS"));
			dm.setObservations("MOUVEMENT GROS+ : " + a.getCodeArticle()
					+ " Qte " + dm.getQteMvt());
			dm.setTypeMouvement(mouvStockTypeDAO.getTypeMouvementStock("G+"));
		}
		List<DetailMouvement> listDm = new ArrayList<DetailMouvement>();
		listDm.add(dm);
		em.setMouvements(listDm);
		em = enteteMouvementDAO
				.createOrUpdateEnteteMouvementWithoutMAJStock(em);

	}

	public void truncateAllTables(String ip, Connection conn) {

		Statement state = null;
		try {
			state = conn.createStatement();
			state.executeUpdate("TRUNCATE  TABLE winstore.dbo.Article;");
			state.executeUpdate("TRUNCATE  TABLE winstore.dbo.ArtRef;");
			state.executeUpdate("TRUNCATE  TABLE winstore.dbo.EAN;");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public void deleteArt(String ip, Article a, String pvtCode,
			List<GenCode> genCodeList, Connection conn) {
		Statement state = null;
		try {
			state = conn.createStatement();
			if (genCodeList != null) {
				for (GenCode gc : genCodeList) {
					tx.begin();
					state.executeUpdate("delete from dbo.EAN where CodeEAN ='"
							+ (gc.getCode() + gc.getCaractereControle()).trim()
							+ "' ;");
					tx.commit();
				}

			}
			tx.begin();
			state.executeUpdate("delete from dbo.EAN where CodeInterne ="
					+ Long.parseLong(a.getCodeArticle()) + " ;");
			state.executeUpdate("delete from dbo.ArtRef where CodeInterne ="
					+ Long.parseLong(a.getCodeArticle()) + " ;");
			state.executeUpdate("delete from dbo.Article where CodeInterne ="
					+ Long.parseLong(a.getCodeArticle()) + " ;");
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (state != null)
					state.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	// @Override
	// public void getAllClientFromWinstore(String pvtCode, String ip) {
	//
	// try {
	//
	// tx.begin();
	// String query = requestDAO.getSqlRequestByLibelle("winstore.allclient");
	// ResultSet rs = mssqlConnection.executeQuery(ip, query);
	//
	// while (rs.next()) {
	// int typeClient = rs.getInt("Titre");
	//
	// CompteClient compte = new CompteClient();
	// compte.setNumClientWinstore(rs.getString("CodeClient"));
	// compte.setEnabled(true);
	// compte.setMagCreation(pvtCode);
	// compte.setUserCreation("GPV3000");
	// compte.setDateCreation(new Date(System.currentTimeMillis()));
	// compte = clientService.createOrUpdateCompteClient(compte);
	//
	// Client c = new Client();
	// System.out.println(rs.getString("CodeClient"));
	// if (typeClient == 1 || typeClient == 2 || typeClient == 3) {
	// c.setNom(rs.getString("Nom"));
	// c.setPrenom(rs.getString("Prenom"));
	// compte.setType(1);
	// compte.setSoumisTVA(true);
	// }
	// if (typeClient == 4) {
	// compte.setType(2);
	// c.setRaisonSocial(rs.getString("Nom"));
	// }
	//
	// c.setBloquer(false);
	// c.setDateCreation(new Date(System.currentTimeMillis()));
	// c.setEnable(true);
	// c.setMagClient(pvtCode);
	// c.setUserCreation("GPV3000");
	// c = clientService.createOrUpdateClient(c);
	//
	// compte.setClientPrincipal(c);
	// compte = clientService.createOrUpdateCompteClient(compte);
	//
	//
	//
	// }
	//
	// tx.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// try {
	// tx.rollback();
	// } catch (IllegalStateException e1) {
	// e1.printStackTrace();
	// } catch (SecurityException e1) {
	// e1.printStackTrace();
	// } catch (SystemException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	//
	// }

	// public void downloadTicketFromTPV(String mag, String ip) throws Exception
	// {
	// try {
	//
	// tx.begin();
	// String query = requestDAO.getSqlRequestByLibelle("winstore.ticket.jour");
	// ResultSet rs = mssqlConnection.executeQuery(ip, query);
	// EnteteAchat ef = null;
	//
	// while (rs.next()) {
	//
	// int TypeTicket = rs.getInt("TypeTicket");
	// String codeClient = rs.getString("CodeClient");
	// CompteClient compte =
	// clientService.getCompteClientByNumClientWinstore(codeClient);
	// if (TypeTicket == 0) {
	// List<DetailDocument> listDa = new ArrayList<DetailDocument>();
	// // FACTURE
	// ef = new EnteteAchat();
	// ef.setCodeMagasin(mag);
	// ef.setDateEmission(rs.getDate("DateTicket"));
	// ef.setNumTicket(rs.getInt("NumTicket") + "");
	// ef.setObservation("TICKET CAISSE N° " + ef.getNumTicket());
	// ef.setUserCreation(rs.getInt("MatCaissiere") + "");
	// ef.setValeurPV((int) rs.getFloat("MontantTotal"));
	// ef = clientService.createOrUpdateEnteteAchat(ef);
	//
	// String query2 =
	// requestDAO.getSqlRequestByLibelle("winstore.detail.ticket");
	// query2 = query2.replaceAll("tik", rs.getInt("IDTicket")+"");
	// System.out.println(query2);
	// ResultSet rs2 = mssqlConnection.executeQuery(ip, query2);
	//
	// while (rs2.next()) {
	// DetailDocument df = null;
	// System.out.println("nouveau detail");
	// String codeInterne = new
	// DecimalFormat("000000000").format(Long.parseLong(rs2.getString("CodeInterne")));
	// int typeLigne = rs2.getInt("TypeLigne");
	// double prixVenteNet = Math.floor(rs2.getFloat("PrixVenteNet") + 0.5); ;
	// int qte = rs2.getInt("Qte");
	//
	// Article a = articleService.getArticle(new ArticleMagRefPK(mag,
	// codeInterne));
	// if(a == null){
	// System.out.println("ARTICLE NULL "+codeInterne+" "+typeLigne+" "+prixVenteNet+" "+qte);
	// continue;
	// }
	//
	//
	// df = new DetailDocument();
	// df.setArticle(a);
	// df.setPrixVente((int)prixVenteNet);
	// df.setPrixVenteHT((int)
	// articleService.calculateHTForPrice(df.getArticle().getTauxTVA(),
	// df.getPrixVente()));
	// df.setQte(qte);
	// if (null != df.getArticle().getPromo()) {
	// Promo p = promoDAO.getPromo(df.getArticle().getPromo());
	// if (p.isEnFacturation()) {
	// df.setPrixReviens((int)df.getArticle().getPrixReviensPromoTTC());
	// df.setPrixReviensHT((int)
	// articleService.calculateHTForPrice(df.getArticle().getTauxTVA(),
	// df.getPrixReviens()));
	// } else {
	// df.setPrixReviens((int)df.getArticle().getPrixReviensTTCEnCours());
	// df.setPrixReviensHT((int)
	// articleService.calculateHTForPrice(df.getArticle().getTauxTVA(),
	// df.getPrixReviens()));
	// }
	// } else {
	// df.setPrixReviens(df.getArticle().getPrixReviensTTCEnCours());
	// df.setPrixReviensHT((int)
	// articleService.calculateHTForPrice(df.getArticle().getTauxTVA(),
	// df.getPrixReviens()));
	// }
	//
	// if (typeLigne == 1)
	// df.setTypeLigne(1);
	// if (typeLigne == 2)
	// df.setTypeLigne(2);
	//
	// System.out.println("insertion df");
	// df = clientService.createOrUpdateDetailAchat(df);
	// System.out.println("fin insertion df");
	// listDa.add(df);
	//
	// }
	//
	// for(DetailDocument da : listDa){
	// ef.setValeurPF((int)(ef.getValeurPF() + (da.getPrixReviens()*
	// da.getQte())));
	// ef.setValeurPFHT((int)(ef.getValeurPFHT() + (da.getPrixReviensHT()*
	// da.getQte())));
	// ef.setValeurPVHT((int)(ef.getValeurPVHT() + (da.getPrixVenteHT()*
	// da.getQte())));
	// }
	//
	// ef.setDetailAchat(listDa);
	// ef = clientService.createOrUpdateEnteteAchat(ef);
	//
	//
	// if (compte != null) {
	// compte.setSoldeDuCompte(compte.getSoldeDuCompte() - ef.getValeurPV());
	// List<EnteteAchat> listEm = null;
	// if (compte.getFactures() == null) {
	// listEm = new ArrayList<EnteteAchat>();
	// listEm.add(ef);
	// } else {
	// listEm = compte.getAchats();
	// listEm.add(ef);
	// }
	// compte.setAchats(listEm);
	// compte = clientService.createOrUpdateCompteClient(compte);
	// }
	//
	// }
	// if (TypeTicket == 3) {
	// Reglement rgl = new Reglement();
	// rgl.setDateReglement(rs.getDate("DateTicket"));
	// rgl.setMontantReglement(rs.getFloat("MontantTotal"));
	// rgl.setObservation("REGLEMENT COMPTE A TERMES ");
	// rgl = clientService.createOrUpdateReglement(rgl);
	// if (compte != null) {
	// compte.setSoldeDuCompte(compte.getSoldeDuCompte() +
	// rgl.getMontantReglement());
	// List<Reglement> listRgl = null;
	// if (compte.getReglements() == null) {
	// listRgl = new ArrayList<Reglement>();
	// listRgl.add(rgl);
	// } else {
	// listRgl = compte.getReglements();
	// listRgl.add(rgl);
	// }
	// compte.setReglements(listRgl);
	// compte = clientService.createOrUpdateCompteClient(compte);
	// }
	// }
	//
	// }
	//
	// tx.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// tx.rollback();
	// }
	//
	//
	//
	// }

}
