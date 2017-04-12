package ci.prosuma.prosumagpv.metier.service;

import java.util.Date;

import ci.prosuma.prosumagpv.entity.dto.StatDem;

public interface IStatDemService {

	public StatDem createOrUpdateStatDem(StatDem a);

	public StatDem getStatDem(long id);

	/*
	 * public boolean process(int type, Date dateDebut, Date dateFin, long
	 * gisementDebut, long gisementFin, String pvtCode, String secteur, String
	 * rayon, List<TypeMouvementStock> filtreLivrer, List<TypeMouvementStock>
	 * filtreCasse, List<TypeMouvementStock> filtreDemarq);
	 */
	public boolean process(int type, Date dateDebut, Date dateFin,
			long gisementDebut, long gisementFin, String pvtCode,
			String secteur, String rayon, int typeProduit);

}
