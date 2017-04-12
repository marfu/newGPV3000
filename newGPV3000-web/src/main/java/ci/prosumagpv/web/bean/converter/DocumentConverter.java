//package ci.prosumagpv.web.bean.converter;
//
//import javax.annotation.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosumagpv.web.bean.util.FactoryBean;
//
//@ManagedBean("documentConverter")
//@RequestScoped
//public class DocumentConverter implements Converter {
//
//
//	public DocumentConverter() {
//	}
//
//	@Override
//	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
//		System.out.println("arg2"+arg2);
//		if (arg2 == null || arg2.trim().equals("") || arg2.trim().equals("null") ) {
//			return null;
//		} else {
//			try {
//				return new FactoryBean().getClientService().getEnteteDocument(Long.parseLong(arg2));
//
//			} catch (Exception exception) {
//				exception.printStackTrace();
//			}
//		}
//
//		return null;
//
//	}
//
//	@Override
//	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		if (arg2 != null && arg2 instanceof EnteteDocument) {
//			EnteteDocument ebcf = (EnteteDocument) arg2;
//			if (ebcf != null && ebcf.getId() > 0) {
//				return  ebcf.getId()+"";
//			}
//			return null;
//
//		} else {
//			return null;
//		}
//	}
//
//}
