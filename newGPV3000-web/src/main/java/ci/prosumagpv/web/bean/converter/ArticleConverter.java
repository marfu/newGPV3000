package ci.prosumagpv.web.bean.converter;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ci.prosuma.prosumagpv.entity.Article;
import ci.prosuma.prosumagpv.entity.pk.ArticleMagRefPK;
import ci.prosumagpv.web.bean.security.SecurityBean;
import ci.prosumagpv.web.bean.util.FactoryBean;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="articleConverter")
public class ArticleConverter implements Converter {

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

	public ArticleConverter() {
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
				return new FactoryBean().getArticleService().getArticle(new ArticleMagRefPK(securityBean.getMag(), arg2));

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null && arg2 instanceof Article) {
			Article pvt = (Article) arg2;
			if (pvt.getCodeArticle() != null
					&& !pvt.getCodeArticle().trim().equals("")) {
				return  pvt.getCodeArticle();
			}
			return null;

		} else {
			return null;
		}
	}

}
