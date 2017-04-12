package ci.prosumagpv.web.bean.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ci.prosuma.prosumagpv.metier.service.IArticleService;
import ci.prosuma.prosumagpv.metier.service.IBonCommandeService;
import ci.prosuma.prosumagpv.metier.service.IClassificationService;
import ci.prosuma.prosumagpv.metier.service.IDepotService;
import ci.prosuma.prosumagpv.metier.service.IFournisseurService;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IInventaireService;
import ci.prosuma.prosumagpv.metier.service.IMouvementService;
import ci.prosuma.prosumagpv.metier.service.IPointDeVenteService;
import ci.prosuma.prosumagpv.metier.service.IPromoService;
import ci.prosuma.prosumagpv.metier.service.ISecurityService;
import ci.prosuma.prosumagpv.metier.service.IStockArticleService;

public class FactoryBean {

	private Context context;
//
//	private String earName;

	public FactoryBean() {

		try {
			context = new InitialContext();
			//earName = (String) context.lookup("java:module/");
		} catch (NamingException e) {
			e.printStackTrace();

		}

	}

	@SuppressWarnings("unchecked")
	protected <T> T getLocalService(Class<T> c, String name) throws NamingException {

		String uri = "java:module/"+name;

		return (T) context.lookup(uri);

	}

	public ISecurityService getSecurityService() {
		try {
			return getLocalService(ISecurityService.class, "SecurityServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
//	public IClientService getClientService() {
//		try {
//			return getLocalService(IClientService.class, "ClientServiceImpl");
//		} catch (NamingException e) {
//			throw new RuntimeException(e);
//		}
//	}
	public IInventaireService getInventaireService() {
		try {
			return getLocalService(IInventaireService.class, "InventaireServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IGenCodeService getGenCodeService() {
		try {
			return getLocalService(IGenCodeService.class, "GenCodeServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public IArticleService getArticleService() {
		try {
			return getLocalService(IArticleService.class, "ArticleServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public IBonCommandeService getBonCommandeService() {
		try {
			return getLocalService(IBonCommandeService.class, "BonCommandeServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IClassificationService getClassificationService() {
		try {
			return getLocalService(IClassificationService.class, "ClassificationServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IDepotService getDepotService() {
		try {
			return getLocalService(IDepotService.class, "DepotServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IFournisseurService getFournisseurService() {
		try {
			return getLocalService(IFournisseurService.class, "FournisseurServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IMouvementService getMouvementService() {
		try {
			return getLocalService(IMouvementService.class, "MouvementServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IPointDeVenteService getPointDeVenteService() {
		try {
			return getLocalService(IPointDeVenteService.class, "PointDeVenteServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IPromoService getPromoService() {
		try {
			return getLocalService(IPromoService.class, "PromoServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	public IStockArticleService getStockArticleService() {
		try {
			return getLocalService(IStockArticleService.class, "StockArticleServiceImpl");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
        
       

	

}
