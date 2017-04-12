package ci.prosuma.prosumagpv.entity.generators;

import ci.prosuma.prosumagpv.entity.stock.EnteteInventaire;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.SequenceGenerator;

/**
 *
 * @author tagsergi
 */
public class InventaireIdGenerator extends SequenceGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {

        Connection connection = ssci.connection();

        try {
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(InventaireIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            EnteteInventaire ent = (EnteteInventaire) o;
            ResultSet rs = statement.executeQuery("select INVENTAIRE_ENTETE_PK from INVENTAIRE_ENTETE where CODE_MAGASIN_FK='"+ent.getPvt().getPvtCode()+
                    "' order by INVENTAIRE_ENTETE_PK desc limit 0,1");
            if (rs.next()) {
                int id = rs.getInt(1) + 1;
                return id;
            }else{
                return 1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(InventaireIdGenerator.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

}
