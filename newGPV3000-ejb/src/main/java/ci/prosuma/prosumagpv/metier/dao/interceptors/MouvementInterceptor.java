package ci.prosuma.prosumagpv.metier.dao.interceptors;

import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import ci.prosuma.prosumagpv.entity.stock.DetailMouvement;
import ci.prosuma.prosumagpv.entity.stock.EnteteMouvement;
import ci.prosuma.prosumagpv.entity.stock.StockArticle;
import ci.prosuma.prosumagpv.metier.dao.mysql.IStockArticleDAO;

public class MouvementInterceptor {

	@EJB
	private IStockArticleDAO stockDAO;

	@AroundInvoke
	public Object miseAjourStock(InvocationContext context) throws Exception {
		try {

			EnteteMouvement em = (EnteteMouvement) context.getParameters()[0];
			DetailMouvement dm ;
			for (Iterator<DetailMouvement> it = em.getMouvements().iterator(); it.hasNext();) {
				System.out.println("----------------------");
				dm = it.next();
				if ((dm.getArticle() == null)
						|| (dm.getArticle().getCodeArticle() == null)
						|| (dm.getArticle().getStock() == null)
						|| (dm.getArticle().getCodeArticle().equals("INCONNU"))) {
					continue;
				}

				if (null != dm.getArticle()) {
					System.out.println("ARTICLE SA "+ dm.getArticle().getCodeArticle());
					long stockId = dm.getArticle().getStock().getId();
					StockArticle sa = stockDAO.getStockArticle(stockId);

					if (em.getOrigineMouvement().getCode().equals("INVEN")) {

						if (dm.getSens() == 1) {
							System.out.println("SENS == 1 " + "|"
									+ sa.getQteComptable() + "|"
									+ dm.getQteMvt());
							sa.setQteComptable(dm.getQteMvt()+ sa.getQteComptable());
							sa.setDateDerniereEntrer(new Date(System
									.currentTimeMillis()));

						}
						if (dm.getSens() == -1) {
							System.out.println("SENS == -1 " + "|"
									+ sa.getQteComptable() + "|"
									+ dm.getQteMvt());
							sa.setQteComptable(sa.getQteComptable()
									+ dm.getQteMvt());
							sa.setDateDerniereSortie(new Date(System
									.currentTimeMillis()));

						}

					} else {
						if (dm.getSens() == 1) {
							System.out.println("---------------- sa dm " + sa
									+ " " + dm);
							System.out.println("SENS == 1 " + "|"
									+ sa.getQteComptable() + "|"
									+ dm.getQteMvt());
							sa.setQteComptable(sa.getQteComptable()
									+ dm.getQteMvt());
							sa.setDateDerniereEntrer(new Date(System
									.currentTimeMillis()));

						}
						if (dm.getSens() == -1) {
							System.out.println("SENS == -1 " + "|"
									+ sa.getQteComptable() + "|"
									+ dm.getQteMvt());
							sa.setQteComptable(sa.getQteComptable()
									- dm.getQteMvt());
							sa.setDateDerniereSortie(new Date(System
									.currentTimeMillis()));

						}
					}
					if (em.getOrigineMouvement().getCode().equals("INVEN"))
						sa.setDateDernierInventaire(new Date(System
								.currentTimeMillis()));

					// $F{sens} == 1 ? java.lang.Math.abs( $F{QTE_MOUVEMENTE} +
					// $F{QTE_PHYS_AVT_MOUV}) :
					// java.lang.Math.abs($F{QTE_PHYS_AVT_MOUV} +
					// $F{QTE_MOUVEMENTE})

					stockDAO.createOrUpdateStockArticle(sa);
                                        //dm.getArticle().setStock(sa);
				}

			}
			return context.proceed();

		} catch (Exception e) {
			throw e;
		}

	}
}
