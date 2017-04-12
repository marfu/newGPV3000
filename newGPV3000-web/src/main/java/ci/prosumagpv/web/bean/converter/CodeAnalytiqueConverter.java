package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ci.prosuma.prosumagpv.entity.util.CodeAnalytique;
import ci.prosumagpv.web.bean.util.FactoryBean;

@FacesConverter(value = "codeAnalytiqueConverter")
public class CodeAnalytiqueConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			if (arg2 == null || arg2.trim().equals("")) {
				return null;

			}
			if (arg2 != null && arg2.equals("XXX")) {
				return null;
			}
			return new FactoryBean().getClassificationService()
					.getCodeAnalytique(arg2);

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

			CodeAnalytique sect = (CodeAnalytique) arg2;
			if (sect.getCodeAnalytique() != null
					&& !sect.getCodeAnalytique().equals("")
					&& !sect.getCodeAnalytique().equals("XXX")) {
				return "" + sect.getCodeAnalytique();
			}
			return null;

		} else {
			return null;
		}
	}

}
