//package ci.prosuma.prosumagpv.metier.service.impl;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.entity.client.CompteClient;
//import ci.prosuma.prosumagpv.entity.client.DetailDocument;
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosuma.prosumagpv.entity.client.Incident;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDocument;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IClientDAO;
//import ci.prosuma.prosumagpv.metier.dao.mysql.ICompteClientDAO;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IDetailDocumentDAO;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IEnteteDocumentDAO;
//import ci.prosuma.prosumagpv.metier.dao.mysql.IIncidentDAO;
//import ci.prosuma.prosumagpv.metier.service.IClientService;
//
//@Stateless
//@Local(IClientService.class)
//public class ClientServiceImpl implements IClientService, Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@EJB
//	private IClientDAO clientDAO;
//	
//	@EJB
//	private ICompteClientDAO  compteClientDAO;
//	
//	@EJB
//	private IEnteteDocumentDAO  enteteDocumentDAO;
//	
//	@EJB
//	private IDetailDocumentDAO  detailDocumentDAO;
//	
//	@EJB
//	private IIncidentDAO  incidentDAO;
//	
//	
//
//	@Override
//	public Client getClient(long id) {
//		return clientDAO.getClient(id);
//	}
//
//	@Override
//	public Client createOrUpdateClient(Client c) {
//		return clientDAO.createOrUpdateClient(c);
//	}
//
//	@Override
//	public List<Client> getAllClients() {
//		return clientDAO.getAllClients();
//	}
//
//	@Override
//	public DetailDocument getDetailDocument(long id) {
//		return detailDocumentDAO.getDetailDocument(id);
//	}
//
//	@Override
//	public DetailDocument createOrUpdateDetailDocument(DetailDocument c) {
//		return detailDocumentDAO.createOrUpdateDetailDocument(c);
//	}
//
//	@Override
//	public EnteteDocument getEnteteDocument(long id) {
//		return enteteDocumentDAO.getEnteteDocument(id);
//	}
//
//	@Override
//	public EnteteDocument createOrUpdateEnteteDocument(EnteteDocument c) {
//		return enteteDocumentDAO.createOrUpdateEnteteDocument(c);
//	}
//
//	
//
//	@Override
//	public Incident getIncident(long id) {
//		return incidentDAO.getIncident(id);
//	}
//
//	@Override
//	public Incident createOrUpdateIncident(Incident c) {
//		return incidentDAO.createOrUpdateIncident(c);
//	}
//
//	@Override
//	public CompteClient getCompteClient(long id) {
//		return compteClientDAO.getCompteClient(id);
//	}
//
//	@Override
//	public CompteClient createOrUpdateCompteClient(CompteClient c) {
//		return compteClientDAO.createOrUpdateCompteClient(c);
//	}
//
//	@Override
//	public List<CompteClient> getAllCompteClients() {
//		return compteClientDAO.getAllCompteClients();
//	}
//
//	
//	@Override
//	public List<EnteteDocument> getAllDocumentsForClient(long id) {
//		return compteClientDAO.getAllDocumentsForClient(id);
//	}
//	
//	@Override
//	public List<EnteteDocument> getAllDocumentsForClientAndType(long id,TypeDocument docType) {
//		return compteClientDAO.getAllDocumentsForClientAndType(id,docType);
//	}
//
//	@Override
//	public List<Incident> getAllIncidentsForClient(long id) {
//		return compteClientDAO.getAllIncidentsForClient(id);
//	}
//
//
//	
//	@Override
//	public CompteClient getCompteClientByNumClient(long num) {
//		return compteClientDAO.getCompteClientByNumClient(num);
//	}
//
//
//
//
//	@Override
//	public List<Client> getAllAyantDroitForClient(long id) {
//		return compteClientDAO.getAllAyantDroitForClient(id);
//	}
//
//
//	
//	
//	
//
//	
//	
//
//	
//
//}
