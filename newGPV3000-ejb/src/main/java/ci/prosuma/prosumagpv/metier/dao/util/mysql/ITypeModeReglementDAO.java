package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.ModeReglement;

public interface ITypeModeReglementDAO {
	
	
	public ModeReglement  createOrUpdateModeReglement(ModeReglement ei);
	
	public ModeReglement  getModeReglement(String code);
	
	public boolean removeModeReglement(ModeReglement ei);
	
	public List<ModeReglement> getAllModeReglement();
	
	


	
	

}
