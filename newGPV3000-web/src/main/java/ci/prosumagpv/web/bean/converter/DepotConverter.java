package ci.prosumagpv.web.bean.converter;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ci.prosuma.prosumagpv.entity.stock.Depot;
import ci.prosumagpv.web.bean.security.SecurityBean;
import ci.prosumagpv.web.bean.util.FactoryBean;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="depotConverter")
public class DepotConverter implements Converter {

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

	public DepotConverter() {
		if (securityBean == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			securityBean = (SecurityBean) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(context.getELContext(), null, "securityBean");
		}
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.trim().equals("")) {
			return null;
		} else {
			try {
				return new FactoryBean().getDepotService().getDepot(Long.parseLong(arg2));

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null && arg2 instanceof Depot) {
			Depot pvt = (Depot) arg2;
			if (pvt.getId() >0 ) {
				return  pvt.getId()+"";
			}
			return null;

		} else {
			return null;
		}
	}

}
