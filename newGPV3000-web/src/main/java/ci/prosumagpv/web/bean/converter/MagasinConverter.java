/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosumagpv.web.bean.converter;

import ci.prosuma.prosumagpv.entity.PointDeVente;
import ci.prosuma.prosumagpv.metier.dao.mysql.IPointDeVenteDAO;
import ci.prosumagpv.web.bean.util.FactoryBean;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author tagsergi
 */
@FacesConverter(value = "magasinConverter")
public class MagasinConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            try {
                return new FactoryBean().getPointDeVenteService().getPVT(value);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof PointDeVente) {
            PointDeVente pvt = (PointDeVente) value;
            if (pvt.getPvtCode() != null && !pvt.getPvtCode().trim().equals("")) {
                return pvt.getPvtCode();
            }
            return null;
        } else {
            return null;
        }
    }

}
