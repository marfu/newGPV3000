package ci.prosumagpv.web.bean.converter;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ci.prosuma.prosumagpv.entity.Promo;
import ci.prosumagpv.web.bean.security.SecurityBean;
import ci.prosumagpv.web.bean.util.FactoryBean;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "promoConverter")
public class PromoConverter implements Converter {

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

	public PromoConverter() {
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
				return new FactoryBean().getPromoService().getPromo(arg2);

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null && arg2 instanceof Promo) {
			Promo pvt = (Promo) arg2;
			if (pvt.getLibelReduit() != null
					&& !pvt.getLibelReduit().trim().equals("")) {
				return  pvt.getLibelReduit();
			}
			return null;

		} else {
			return null;
		}
	}

}
