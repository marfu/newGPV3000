package ci.prosumagpv.web.mobile.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.stock.DetailInventaire;
import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;
import ci.prosuma.prosumagpv.metier.service.IArticleService;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IInventaireService;
import ci.prosumagpv.web.mobile.entities.ArticleMobile;
import ci.prosumagpv.web.mobile.entities.InventaireListeMobile;
import ci.prosumagpv.web.mobile.entities.InventaireMobile;

/**
 * Servlet implementation class InventaireServlet
 */
@WebServlet("/InventaireServlet")
public class InventaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	private IInventaireService inventaireService;
	
	@EJB 
	private IGenCodeService  genCodeService;
	
	
	@EJB
	private IArticleService articleService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventaireServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String choice=request.getParameter("choice");
		
		
		if(choice != null  && choice.equals("inventaire")){
			String mag=request.getParameter("mag");
			try {
				List<EnteteInventaire> list = inventaireService.getAllEnteteInventaireForMagLancer(mag);
				 String[] tab = new String[list.size()];
				for (int i = 0 ; i < list.size(); i++) {
					tab[i] = list.get(i).getId()+" : "+list.get(i).getObservation();
				}
				PrintWriter writer  = response.getWriter();
				InventaireListeMobile  ilm = new InventaireListeMobile(mag, tab);
				writer.print(ilm.toJSON());
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if( choice != null &&  choice.equals("detailInv")){
			String id=request.getParameter("id");
			EnteteInventaire ei = inventaireService.getEnteteInventaire(Long.parseLong(id));
			PrintWriter writer  = response.getWriter();
			InventaireMobile  im = new InventaireMobile();
			
			im.gisementDebut = ei.getGisementDebut();
			im.gisementFin = ei.getGisementFin();
			im.numInventaire = ei.getId()+"";
			im.observation = ei.getObservation();
			im.dump();
			writer.print(im.toJSON());
			writer.close();
		}
		
		if(choice != null && choice.equals("info")){
			String cb = request.getParameter("cb");
			String mag = request.getParameter("mag");
			String num = request.getParameter("num");
			cb = cb.substring(0, cb.length() - 1);
			
			Article a = genCodeService.getArticleForGenCodeAndPvt(cb, mag);
			EnteteInventaire ei = inventaireService.getEnteteInventaire(Long.parseLong(num));
			
			PrintWriter writer  = response.getWriter();
			ArticleMobile  am = new ArticleMobile();
			boolean inside = false;
			if(a != null && ei.getGisementDebut() <= a.getCodeGisement() && a.getCodeGisement() <= ei.getGisementFin())
				inside = true;
			
			if(a != null && inside ){
				am.error  = 0;
				am.codeArticle = a.getCodeArticle();
				am.designation = a.getDesignation();
				am.gisement = a.getCodeGisement();
				am.prixVente = a.getPrixVenteTTCEnCours()+"";
				am.stockTh = a.getStock().getQteComptable()+"";
			}else if( a != null && !inside){
				am.error  = 1;
				am.codeArticle = a.getCodeArticle();
				am.designation = a.getDesignation();
				am.gisement = a.getCodeGisement();
				am.prixVente = a.getPrixVenteTTCEnCours()+"";
				am.stockTh = a.getStock().getQteComptable()+"";
			}else{
				am.error = 2;
				am.codeArticle = "000000000";
				am.gisement = 0;
				am.designation = "CODE BARRE INCONNU";
				am.prixVente = "0";
				am.stockTh = "0";
			}
			am.dump();
			writer.print(am.toJSON());
			writer.close();
		}
		
		if(choice != null && choice.equals("add")){
			String mag = request.getParameter("mag");
			String cb = request.getParameter("cb");
			String qte = request.getParameter("qte");
			String type = request.getParameter("type");
			String art = request.getParameter("art");
			String num = request.getParameter("num");
			String error = request.getParameter("error");
			String gis = request.getParameter("gis");
			
			System.out.println(mag+"|"+cb+"|"+qte+"|"+type+"|"+art+"|"+num+"|"+error+"|"+gis);
			
			int typeErreur = Integer.parseInt(error);
			if(typeErreur == 0){
				System.out.println("type erreur 0");
				DetailInventaire  di = inventaireService.getDetailForInventaireAndArticle(Long.parseLong(num), art);
				if(di != null){
					float mt = 0;
					if(type.toLowerCase().equals("reserve")){
						System.out.println("reserve");
						mt = di.getQteReserve()+Float.parseFloat(qte);
						di.setQteReserve(mt);
						inventaireService.createOrUpdateDetailInventaire(di);
					}
					if(type.toLowerCase().equals("magasin")){
						System.out.println("magasin");
						mt = di.getQteMagasin()+Float.parseFloat(qte);
						di.setQteMagasin(mt);
						inventaireService.createOrUpdateDetailInventaire(di);
					}
				}
			}
			
			
			
			
			
			
			
			
			
		}
		

		
		  
		
		
	}

}
