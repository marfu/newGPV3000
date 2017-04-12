package ci.prosumagpv.web.mobile.entities;

import java.io.Serializable;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;


@SuppressWarnings("serial")
public class InventaireMobile implements Serializable {
	
	public String numInventaire;
	
	public String observation;
	
	public long gisementDebut;
	
	public long gisementFin;
	public  String classification;
	
	public InventaireMobile() {
	}

	public String toJSON() {
	    // Define an external an a nexted JSONObjects
	    JSONObject outer = new JSONObject();
	    JSONObject inner = new JSONObject();
	    // Now generate the JSON output
	    try {
	        outer.put("inventaireMobile", inner); // the outer object name
	        inner.put("numInventaire", numInventaire); // a name/value pair
	        inner.put("observation", observation); // a name/value pair
	        inner.put("gisementDebut", gisementDebut); // a name/value pair
	        inner.put("gisementFin", gisementFin); // a name/value pair
	        inner.put("classification", classification); // a name/value pair
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
	            JSONObject inner = outer.getJSONObject("inventaireMobile");
	            if (inner != null) {
	                // Parse the name/value pairs
	                numInventaire = inner.getString("numInventaire");
	                observation = inner.getString("observation");
	                gisementDebut = inner.getLong("gisementDebut");
	                gisementFin = inner.getLong("gisementFin");
	                classification = inner.getString("classification");
	            }
	        }
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	}
	
	
	public void dump(){
		System.out.println("num: " + numInventaire);
		System.out.println("obs: " + observation);
		System.out.println("gis: " + gisementDebut);
		System.out.println("gis2: " + gisementFin);
		System.out.println("ray: " + classification);
	   
	}

	
	

}
