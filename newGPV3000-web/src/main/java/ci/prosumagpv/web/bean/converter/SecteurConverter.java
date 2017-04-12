package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ci.prosuma.prosumagpv.entity.util.Secteur;
import ci.prosumagpv.web.bean.util.FactoryBean;

@FacesConverter(value = "secteurConverter")
public class SecteurConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try {
			if (arg2 == null || arg2.trim().equals("")) {
				return null;
				
			}
			if(arg2 != null && arg2.equals("XXX")){
				return null;
			}
				return new FactoryBean().getClassificationService().getSecteur(arg2);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			if (arg2 instanceof String) {
				return null;
			}
			
			
			
			Secteur sect = (Secteur) arg2;
			if (sect.getCode() != null && !sect.getCode().equals("") && !sect.getCode().equals("XXX") ) {
				return "" + sect.getCode();
			}
			return null;

		} else {
			return null;
		}
	}

}
