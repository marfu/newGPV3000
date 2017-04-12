package ci.prosumagpv.web.mobile.entities;

import java.io.Serializable;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;


/**
 * @author PROPHYL.COM
 */

@SuppressWarnings("serial")
public class ArticleMobile implements Serializable {
	
	public String codeArticle;
	
	public String designation;
	
	public long gisement;
	
	public String prixVente;
	
	public int error;

	public String stockTh;
	
	public ArticleMobile() {
	}

	public String toJSON() {
	    // Define an external an a nexted JSONObjects
	    JSONObject outer = new JSONObject();
	    JSONObject inner = new JSONObject();
	    // Now generate the JSON output
	    try {
	        outer.put("articleMobile", inner); // the outer object name
	        inner.put("codeArticle", codeArticle); // a name/value pair
	        inner.put("designation", designation); // a name/value pair
	        inner.put("prixVente", prixVente); // a name/value pair
	        inner.put("stockTh", stockTh);
	        inner.put("error", error); // a name/value pair
	        inner.put("gisement", gisement); // a name/value pair
	    } catch (JSONException ex) {
	        ex.printStackTrace();
	    }
	    return outer.toString(); // return the JSON text
	}
	
	public void fromJSON(String ji) {
	    try {
	        JSONObject outer = new JSONObject(ji); // the outer objet
	        if (outer != null) {
	            // Get the inner object and parse out the data
	            JSONObject inner = outer.getJSONObject("articleMobile");
	            if (inner != null) {
	                // Parse the name/value pairs
	                codeArticle = inner.getString("codeArticle");
	                designation = inner.getString("designation");
	                prixVente = inner.getString("prixVente");
	                stockTh = inner.getString("stockTh");
	                error = inner.getInt("error");
	                gisement = inner.getLong("gisement");
	            }
	        }
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	}
	
	
	public void dump(){
		System.out.println("art: " + codeArticle);
		System.out.println("des: " + designation);
		System.out.println("pv: " + prixVente);
		System.out.println("stock: " + stockTh);
		System.out.println("error: " + error);
		System.out.println("gisement: " + gisement);
	   
	}

	

}
