//package ci.prosuma.prosumagpv.metier.service;
//
//import java.util.List;
//
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.entity.client.CompteClient;
//import ci.prosuma.prosumagpv.entity.client.DetailDocument;
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosuma.prosumagpv.entity.client.Incident;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDocument;
//
//public interface IClientService {
//
//	// COMPTE CLIENT
//	public Client getClient(long id);
//
//	public Client createOrUpdateClient(Client c);
//
//	public List<Client> getAllClients();
//
//	public List<Client> getAllAyantDroitForClient(long id);
//
//	public List<Incident> getAllIncidentsForClient(long id);
//
//	// ACHATS
//	public DetailDocument getDetailDocument(long id);
//
//	public DetailDocument createOrUpdateDetailDocument(DetailDocument c);
//
//	// INCIDENTS
//	public Incident getIncident(long id);
//
//	public Incident createOrUpdateIncident(Incident c);
//
//	// COMPTE CLIENT
//	public CompteClient getCompteClient(long id);
//
//	public CompteClient createOrUpdateCompteClient(CompteClient c);
//
//	public List<CompteClient> getAllCompteClients();
//
//	public List<EnteteDocument> getAllDocumentsForClient(long id);
//
//	public List<EnteteDocument> getAllDocumentsForClientAndType(long id, TypeDocument docType);
//
//	public CompteClient getCompteClientByNumClient(long num);
//
//	public EnteteDocument getEnteteDocument(long id);
//
//	public EnteteDocument createOrUpdateEnteteDocument(EnteteDocument c);
//
//}
