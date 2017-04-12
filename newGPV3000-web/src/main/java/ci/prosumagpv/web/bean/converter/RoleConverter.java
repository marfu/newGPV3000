package ci.prosumagpv.web.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ci.prosuma.prosumagpv.entity.security.Role;
import ci.prosumagpv.web.bean.util.FactoryBean;

@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			
			if (arg2 != null && !arg2.trim().equals("")) {
				return new FactoryBean().getSecurityService().getRoleById(arg2);
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

			Role role = (Role) arg2;
			if (role != null && role.getDesignation() != null) {
				return "" + role.getDesignation();
			}
			return null;

		} else {
			return null;
		}
	}

}
