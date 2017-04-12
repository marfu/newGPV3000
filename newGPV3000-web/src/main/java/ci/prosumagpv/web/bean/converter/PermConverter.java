package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ci.prosuma.prosumagpv.entity.security.Permission;
import ci.prosumagpv.web.bean.util.FactoryBean;

@FacesConverter(value = "permConverter")
public class PermConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			Long id = Long.parseLong(arg2);
			if (id != null && id > 0) {
				return new FactoryBean().getSecurityService().getPermissionsById(id);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {

			Permission perm = (Permission) arg2;
			if (perm.getPermissionId() > 0) {
				return "" + perm.getPermissionId();
			}
			return null;

		} else {
			return null;
		}
	}

}
