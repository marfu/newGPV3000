package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosumagpv.web.bean.util.FactoryBean;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="ebcfConverter")
public class EBCFConverter implements Converter {


	public EBCFConverter() {
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.trim().equals("") || arg2.trim().equals("null")) {
			return null;
		} else {
			try {
				return new FactoryBean().getBonCommandeService().getEBCF(Long.parseLong(arg2));

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null && arg2 instanceof EnteteBonCommandeFournisseur) {
			EnteteBonCommandeFournisseur ebcf = (EnteteBonCommandeFournisseur) arg2;
			if (ebcf != null && ebcf.getId() > 0) {
				return  ebcf.getId()+"";
			}
			return null;

		} else {
			return null;
		}
	}

}
