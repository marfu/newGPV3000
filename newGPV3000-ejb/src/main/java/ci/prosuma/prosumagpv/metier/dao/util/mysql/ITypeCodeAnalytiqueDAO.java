package ci.prosuma.prosumagpv.metier.dao.util.mysql;

import java.util.List;

import ci.prosuma.prosumagpv.entity.util.CodeAnalytique;

public interface ITypeCodeAnalytiqueDAO {
	
	
	public CodeAnalytique  createOrUpdateCodeAnalytique(CodeAnalytique ei);
	
	public CodeAnalytique  getCodeAnalytique(String code);
	
	public boolean removeCodeAnalytique(CodeAnalytique ei);
	
	public List<CodeAnalytique> getAllCodeAnalytique();
	
	


	
	

}
