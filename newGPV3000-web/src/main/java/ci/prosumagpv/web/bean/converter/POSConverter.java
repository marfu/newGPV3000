package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosumagpv.web.bean.util.FactoryBean;

@FacesConverter(value = "posConverter")
public class POSConverter implements Converter {
	
	
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {

			if (arg2 != null) {
				return  new FactoryBean().getPointDeVenteService().getPVT(arg2);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			PointDeVente pvt = (PointDeVente) arg2;
			if (pvt.getPvtCode() != null && !pvt.getPvtCode().trim().equals("")) {
				return  pvt.getPvtCode();
			}
			return null;

		} else {
			return null;
		}
	}

}
