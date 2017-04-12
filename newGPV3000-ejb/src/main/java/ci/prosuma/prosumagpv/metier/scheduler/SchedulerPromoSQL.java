//package ci.prosuma.prosumagpv.metier.scheduler;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.naming.InitialContext;
//
//import org.jboss.ejb3.annotation.ResourceAdapter;
//import org.jboss.logging.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import ci.prosuma.prosumagpv.entity.Article;
//import ci.prosuma.prosumagpv.entity.GenCode;
//import ci.prosuma.prosumagpv.entity.Promo;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.ModeGenCode;
//import ci.prosuma.prosumagpv.metier.service.IArticleService;
//import ci.prosuma.prosumagpv.metier.service.IPromoService;
//
//@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "0 0 01 1/1 * ? *") })
//@ResourceAdapter("quartz-ra.rar")
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//public class SchedulerPromoSQL implements Job {
//
//	private static Logger logger = Logger.getLogger(SchedulerPromoSQL.class);
//
//	public SchedulerPromoSQL() {
//		logger.debug("CONSTRUCTEUR TRAITEMENT PROMOTION");
//	}
//
//	@Override
//	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		logger.info("APPEL METHODE EXECUTE TRAITEMENT PROMOTION");
//		try {
//			InitialContext initialContext = new InitialContext();
//			IPromoService promoService = (IPromoService) initialContext.lookup("GPV3000Ear/PromoServiceImpl/local");
//			IArticleService articleService = (IArticleService) initialContext.lookup("GPV3000Ear/ArticleServiceImpl/local");
//
//			for (Promo p : promoService.getAllPromoForPVT("050")) {
//				Calendar dateDuJour = Calendar.getInstance();
//				dateDuJour.setTime(new Date(System.currentTimeMillis()));
//
//				Calendar dateDebutPromo = Calendar.getInstance();
//				dateDebutPromo.setTime(p.getDateDebutPromo());
//
//				Calendar dateFinPromo = Calendar.getInstance();
//				dateFinPromo.setTime(p.getDateFinPromo());
//				dateFinPromo.add(Calendar.DATE, 1);
//
//				Calendar dateDebutFacturation = Calendar.getInstance();
//				dateDebutFacturation.setTime(p.getDateDebutFacturation());
//
//				Calendar dateFinFacturation = Calendar.getInstance();
//				dateFinFacturation.setTime(p.getDateFinFacturation());
//				dateFinFacturation.add(Calendar.DATE, 1);
//
//				if (dateDuJour.getTime().after(dateDebutFacturation.getTime()) && dateDuJour.getTime().before(dateFinFacturation.getTime())) {
//					p.setEnFacturation(true);
//					p.setActif(true);
//				} else {
//					p.setEnFacturation(false);
//				}
//				if (dateDuJour.getTime().after(dateDebutPromo.getTime()) && dateDuJour.getTime().before(dateFinPromo.getTime())) {
//					p.setEnVente(true);
//					p.setActif(true);
//				} else {
//					p.setEnVente(false);
//				}
//
//				if (dateDuJour.getTime().after(dateFinPromo.getTime())) {
//					p.setActif(false);
//					p.setEnVente(false);
//					p.setEnFacturation(false);
//					for (Article a : articleService.getAllArticleForPromoAndPVT(p.getLibelReduit(), "050")) {
//						a.setPromo(null);
//						a.setPrixVentePromoTTC(0);
//						a.setPrixReviensPromoTTC(0);
//						a.setPrixModifier(true);
//						articleService.createOrUpdateArticle(a);
//						for (GenCode gc : a.getListGenCode()) {
//							if (gc.getModeGenCode().equals(ModeGenCode.PRINCIPAL)) {
//								gc.setPrixReviensTTCEnCours(a.getPrixReviensTTCEnCours());
//								gc.setPrixVenteTTCEnCours(a.getPrixVenteTTCEnCours());
//								articleService.createOrUpdateGenCode(gc);
//							}
//						}
//
//					}
//
//				}
//
//				promoService.createOrUpdatePromo(p);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//}
