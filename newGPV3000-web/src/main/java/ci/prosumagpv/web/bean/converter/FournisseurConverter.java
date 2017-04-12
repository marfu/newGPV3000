package ci.prosumagpv.web.bean.converter;

import java.text.DecimalFormat;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ci.prosuma.prosumagpv.entity.Fournisseur;
import ci.prosumagpv.web.bean.security.SecurityBean;
import ci.prosumagpv.web.bean.util.FactoryBean;

@ManagedBean("fournisseurConverter")
@RequestScoped
public class FournisseurConverter implements Converter {

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

	public FournisseurConverter() {
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
				DecimalFormat dm = new DecimalFormat("000000");
				return new FactoryBean().getFournisseurService().getFournisseur(dm.format(Long.parseLong(arg2.substring(0, 6).trim())));

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null && arg2 instanceof Fournisseur) {
			Fournisseur pvt = (Fournisseur) arg2;
			if (pvt.getRefFournisseur() != null	&& !pvt.getRefFournisseur().trim().equals("")) {
				return  pvt.getRefFournisseur();
			}
			return null;

		} else {
			return null;
		}
	}

}
