package ci.prosumagpv.web.bean.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import ci.prosuma.prosumagpv.entity.commande.EnteteBonCommandeFournisseur;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.EtatCommande;
import ci.prosuma.prosumagpv.entity.util.EnumerationParam.OrigineCommande;
import ci.prosuma.prosumagpv.metier.service.IBonCommandeService;
import ci.prosumagpv.web.bean.security.SecurityBean;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="dashboardBean")
@ViewScoped
public class DashBoardBean implements Serializable {

    private static final long serialVersionUID = 1L;

	@EJB
	private IBonCommandeService commandeService;

	@ManagedProperty(value = "#{securityBean}")
	private SecurityBean securityBean;

//	@ManagedProperty(value = "#{bonCommandeFournisseurBean}")
//	private BonCommandeFournisseurBean bonCommandeFournisseurBean;


	@SuppressWarnings("unused")
	private List<EnteteBonCommandeFournisseur> listReassort;
	@SuppressWarnings("unused")
	private List<EnteteBonCommandeFournisseur> listLD;
	@SuppressWarnings("unused")
	private List<EnteteBonCommandeFournisseur> listLDRecu;
	@SuppressWarnings("unused")
	private List<EnteteBonCommandeFournisseur> listLDPasse;

	private final DashboardModel model;

	private EnteteBonCommandeFournisseur selectedEBCF;

	private final int NB_HISTORIQUE_CMD = 20;

	public DashBoardBean() {
		listReassort = new ArrayList<>();
		listLD = new ArrayList<>();
		listLDRecu = new ArrayList<>();
		listLDPasse = new ArrayList<>();

		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();
		DashboardColumn column4 = new DefaultDashboardColumn();

		column1.addWidget("reassort");

		column2.addWidget("ld");

		column3.addWidget("reception");

		column4.addWidget("passee");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);
		model.addColumn(column4);
	}


	public SecurityBean getSecurityBean() {
		if (securityBean == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			securityBean = (SecurityBean) FacesContext.getCurrentInstance()
					.getApplication().getELResolver()
					.getValue(context.getELContext(), null, "securityBean");
		}
		return securityBean;
	}

	public void setSecurityBean(SecurityBean securityBean) {
		this.securityBean = securityBean;
	}


	public List<EnteteBonCommandeFournisseur> getListReassort() {
		return commandeService
				.getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
						getSecurityBean().getMag(), debutDuJour(), finDuJour(),
						OrigineCommande.CENTRALE, EtatCommande.TRANSMIT);
	}

	public List<EnteteBonCommandeFournisseur> getListLD() {
		return commandeService
				.getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
						getSecurityBean().getMag(), debutDuJour(), finDuJour(),
						OrigineCommande.LD, EtatCommande.VALIDER);
	}

	public List<EnteteBonCommandeFournisseur> getListLDRecu() {
		return commandeService
				.getAllEnteteBonCommandeFournisseurForMagAndDateRangeAndOrigAndEtat(
						getSecurityBean().getMag(), debutDuJour(), finDuJour(),
						OrigineCommande.LD, EtatCommande.TRANSMIT);
	}

	public List<EnteteBonCommandeFournisseur> getListLDPasse() {
		return commandeService
				.getLastCommandEnteteBonCommandeFournisseurForMag(
						getSecurityBean().getMag(), NB_HISTORIQUE_CMD,
						OrigineCommande.LD, EtatCommande.SAISIE);
	}

	@SuppressWarnings("static-access")
	private Date debutDuJour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(cal.get(cal.YEAR), cal.get(cal.MONTH),
				cal.get(cal.DAY_OF_MONTH), 00, 00, 00);
		// cal.set(cal.get(cal.YEAR), cal.get(0), cal.get(cal.DAY_OF_MONTH), 00,
		// 00, 00);
		return cal.getTime();

	}

	@SuppressWarnings("static-access")
	private Date finDuJour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(cal.get(cal.YEAR), cal.get(cal.MONTH),
				cal.get(cal.DAY_OF_MONTH), 23, 59, 59);
		// cal.set(cal.get(cal.YEAR), cal.get(12), cal.get(cal.DAY_OF_MONTH),
		// 23, 59, 59);
		return cal.getTime();
	}

	public void setListReassort(List<EnteteBonCommandeFournisseur> listReassort) {
		this.listReassort = listReassort;
	}

	public void setListLD(List<EnteteBonCommandeFournisseur> listLD) {
		this.listLD = listLD;
	}

	public void setListLDRecu(List<EnteteBonCommandeFournisseur> listLDRecu) {
		this.listLDRecu = listLDRecu;
	}

	public void setListLDPasse(List<EnteteBonCommandeFournisseur> listLDPasse) {
		this.listLDPasse = listLDPasse;
	}

	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex()
				+ ", Column index: " + event.getColumnIndex()
				+ ", Sender index: " + event.getSenderColumnIndex());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

	public EnteteBonCommandeFournisseur getSelectedEBCF() {
		return selectedEBCF;
	}

	public void setSelectedEBCF(EnteteBonCommandeFournisseur selectedEBCF) {
		this.selectedEBCF = selectedEBCF;
	}

}
