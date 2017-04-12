//package ci.prosuma.prosumagpv.metier.dao.mysql;
//
//import java.util.List;
//
//import ci.prosuma.prosumagpv.entity.client.Client;
//import ci.prosuma.prosumagpv.entity.client.CompteClient;
//import ci.prosuma.prosumagpv.entity.client.EnteteDocument;
//import ci.prosuma.prosumagpv.entity.client.Incident;
//import ci.prosuma.prosumagpv.entity.util.EnumerationParam.TypeDocument;
//
//public interface ICompteClientDAO {
//	
//	public CompteClient getCompteClient(long id);
//	
//	public CompteClient createOrUpdateCompteClient(CompteClient c);
//	
//	public List<CompteClient> getAllCompteClients();
//	public List<Client> getAllAyantDroitForClient(long id);
//	
//	public List<EnteteDocument> getAllDocumentsForClient(long id);
//	public List<EnteteDocument> getAllDocumentsForClientAndType(long id,TypeDocument docType);
//	public List<Incident> getAllIncidentsForClient(long id);
//
//	public CompteClient getCompteClientByNumClient(long num);
//
//}
