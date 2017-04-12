package ci.prosumagpv.web.mobile.entities;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;


/**
 * @author PROPHYL.COM
 */
public class InventaireListeMobile {
	
	public String mag;
	
	public String[]  listInv;
	
	public InventaireListeMobile() {
	}

	public InventaireListeMobile(String mag, String[] listInv) {
		super();
		this.mag = mag;
		this.listInv = listInv;
	}
	
	public String toJSON() {
	    // Define an external an a nexted JSONObjects
	    JSONObject outer = new JSONObject();
	    JSONObject inner = new JSONObject();
	    // Now generate the JSON output
	    try {
	        outer.put("inventaireListMobile", inner); // the outer object name
	        inner.put("mag", mag); // a name/value pair
	        JSONArray ja = new JSONArray();
	        for (int i=0; i<listInv.length; i++) {
	            ja.put(listInv[i]);
	        }
	        inner.put("listInv", ja);
	        inner.put("mag", mag); 
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
	            JSONObject inner = outer.getJSONObject("inventaireListMobile");
	            if (inner != null) {
	                // Parse the name/value pairs
	                mag = inner.getString("mag");
	                JSONArray ja = inner.getJSONArray("listInv");
	                if (ja != null) {
	                	listInv = new String[ja.length()];
	                    for (int i=0; i<ja.length(); i++) {
	                    	listInv[i] = (String) ja.get(i);
	                    }
	                }
	               
	            }
	        }
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	}
	
	
	public void dump(){
		System.out.println("mag: " + mag);
	    if (listInv != null) {
	        for (int i =0; i<listInv.length; i++) {
	            System.out.println("    tag [" + i + "]: " + listInv[i]);
	        }
	    }
	}

}
