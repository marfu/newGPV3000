package ci.prosumagpv.web.mobile.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.metier.service.IArticleService;
import ci.prosuma.prosumagpv.metier.service.IGenCodeService;
import ci.prosuma.prosumagpv.metier.service.IInventaireService;
import ci.prosumagpv.web.mobile.entities.ArticleMobile;

/**
 * @author PROPHYL.COM
 */


/**
 * Servlet implementation class InventaireServlet
 */
@WebServlet("/ControleServlet")
public class ControleServlet extends HttpServlet {
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
    public ControleServlet() {
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
		
		
		if(choice != null && choice.equals("controle")){
			String cb = request.getParameter("cb");
			String mag = request.getParameter("mag");
			cb = cb.substring(0, cb.length() - 1);
			
			Article a = genCodeService.getArticleForGenCodeAndPvt(cb, mag);
			
			PrintWriter writer  = response.getWriter();
			ArticleMobile  am = new ArticleMobile();
			if(a != null  ){
				am.error  = 0;
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
	}

}
