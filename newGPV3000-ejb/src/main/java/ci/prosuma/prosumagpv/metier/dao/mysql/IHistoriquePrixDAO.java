/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.prosuma.prosumagpv.metier.dao.mysql;

import ci.prosuma.prosumagpv.entity.HistoriquePrix;
import java.util.List;

/**
 *
 * @author tagsergi
 */
public interface IHistoriquePrixDAO {
    
    public void createHistorique(HistoriquePrix hp);
    
    public void updateHistorique(HistoriquePrix hp);
    
    public List<HistoriquePrix> getAllHistorique();
    
}
