//package ci.prosuma.prosumagpv.metier.util.mdb;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//import javax.naming.InitialContext;
//
//import org.jboss.logging.Logger;
//
//import ci.prosuma.prosumagpv.entity.dto.FactureDTO;
//import ci.prosuma.prosumagpv.entity.dto.VersionDTO;
//import ci.prosuma.prosumagpv.metier.service.IMouvementService;
//import ci.prosuma.prosumagpv.metier.service.IVersionService;
//
//@MessageDriven(mappedName = "queue/GPV3000APPQueue", activationConfig = {
//		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/GPV3000APPQueue") })
//public class DataExchangeMDB implements MessageListener {
//
//	@SuppressWarnings("unused")
//	private static final Logger log = Logger.getLogger(DataExchangeMDB.class);
//
//	@SuppressWarnings("unused")
//	@Override
//	public void onMessage(Message arg0) {
//		if (arg0 instanceof ObjectMessage) {
//			VersionDTO vd = null;
//			try {
//				ObjectMessage om = (ObjectMessage) arg0;
//				if (om.getObject() != null
//						&& om.getObject() instanceof VersionDTO) {
//					processVersionMessage((VersionDTO) om.getObject());
//				}
//				if (om.getObject() != null
//						&& om.getObject() instanceof FactureDTO) {
//					processFactureMessage((FactureDTO) om.getObject());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void processVersionMessage(VersionDTO vd) {
//		try {
//			InitialContext initialContext = new InitialContext();
//			IVersionService versionService = (IVersionService) initialContext
//					.lookup("GPV3000Ear/VersionServiceImpl/local");
//
//			if (vd.getTYPE().equals("SEC")) {
//				versionService.updateSecteur(vd.getLine());
//			}
//			if (vd.getTYPE().equals("RAY")) {
//				versionService.updateRayon(vd.getLine());
//			}
//			if (vd.getTYPE().equals("FAM")) {
//				versionService.updateFamille(vd.getLine());
//			}
//			if (vd.getTYPE().equals("SFA")) {
//				versionService.updateSousFamille(vd.getLine());
//			}
//			if (vd.getTYPE().equals("IFL")) {
//				versionService.updateIFL(vd.getLine());
//			}
//			if (vd.getTYPE().equals("TTV")) {
//				versionService.updateTTV(vd.getLine());
//			}
//			if (vd.getTYPE().equals("TTT")) {
//				versionService.updateTTT(vd.getLine());
//			}
//			if (vd.getTYPE().equals("OMV")) {
//				versionService.updateOMV(vd.getLine());
//			}
//			if (vd.getTYPE().equals("CMV")) {
//				versionService.updateCMV(vd.getLine());
//			}
//			if (vd.getTYPE().equals("CAC")) {
//				versionService.updateCAC(vd.getLine());
//			}
//			if (vd.getTYPE().equals("RGL")) {
//				versionService.updateRGL(vd.getLine());
//			}
//			if (vd.getTYPE().equals("DEP")) {
//				versionService.updateDEP(vd.getLine());
//			}
//			if (vd.getTYPE().equals("BF")) {
//				versionService.updateFournisseur(vd.getLine());
//			}
//			if (vd.getTYPE().equals("BT")) {
//				versionService.updateThemePromo(vd.getLine(), vd.getPvt());
//			}
//			if (vd.getTYPE().equals("BAA")) {
//				versionService.updateArticle(vd.getLine(), vd.getPvt());
//			}
//			if (vd.getTYPE().equals("BAB")) {
//				versionService.updateCodeBarre(vd.getLine(), vd.getPvt());
//			}
//			if (vd.getTYPE().equals("BAC")) {
//				versionService.updateLienArticleFournisseur(vd.getLine(),
//						vd.getPvt());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void processFactureMessage(FactureDTO fact) {
//		try {
//			System.out.println("new facture");
//			InitialContext initialContext = new InitialContext();
//			IMouvementService mouvementService = (IMouvementService) initialContext
//					.lookup("GPV3000Ear/MouvementServiceImpl/local");
//			mouvementService.createMouvementReceptionFromFact(
//					fact.getCodeMag(), fact.getLineList(), "GPV3000");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
