package gestion.inscription.repositories.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gestion.inscription.core.Config;
import gestion.inscription.core.MySqlDB;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.repositories.IEtudiantRepository;

public class EtudiantRepository extends MySqlDB implements IEtudiantRepository{
    private final String SQL_INSERT = "INSERT INTO `user` (`nom_complet`, `role`, `matricule`, `tuteur`) VALUES (?,?,?,?);";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM `user` WHERE `id`=? and role like 'Etudiant'";
    private final String SQL_SELECT_BY_MATRICULE = "SELECT * FROM `user` WHERE `matricule` LIKE ?";


    @Override
    public Etudiant insert(Etudiant e) {
        this.ouvrirConnexionBD();
        try {
            //String matricule = Config.getPrefixEtudiant()+""+Config.getSeqEtudiant();
            ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNom_complet());
            ps.setString(2,e.getRole().name());          
            //ps.setString(3, matricule);
            ps.setString(3, e.getMatricule());
            ps.setString( 4,e.getTuteur());
            ps.executeUpdate(); //insertion
            
            ResultSet rs = ps.getGeneratedKeys(); //généré le dernier id inséré
            if(rs.next()){
                e.setId(rs.getInt(1));
            }

        } catch (SQLException x) {
            x.printStackTrace();
        }
        
            this.fermerConnexionBD();
            return e;
    }


    @Override
    public Etudiant findById(int id) {
          
        Etudiant etudiant = null;

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_ID);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
                etudiant = new Etudiant(rs.getInt("id"), 
                                    rs.getString("nom_complet"),
                                    rs.getString("tuteur"));
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
        return etudiant;
    }


    @Override
    public Etudiant findByMatricule(String m) {
        Etudiant etudiant = null;

        this.ouvrirConnexionBD();
        try {
           ps = conn.prepareStatement(SQL_SELECT_BY_MATRICULE);
           ps.setString(1, m);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
                etudiant = new Etudiant(rs.getInt("id"),
                                rs.getString("nom_complet"),
                                rs.getString("tuteur"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
            this.fermerConnexionBD();
        return etudiant;
    }


    @Override
    public List<Etudiant> lister() {
        List<Etudiant> liste_etudiant = new ArrayList<>();

        this.ouvrirConnexionBD();
        
        try {

         // 3 - Executer la requête
         Statement stm = conn.createStatement();
         //c'est une requête non préparée
 
 // 4 - Récupérer les résultats
         ResultSet rs = stm.executeQuery("select * from user where role = 'Etudiant'");
         while(rs.next()){
             //rs chaque ligne de la table ou un enregistrment ou un tuple
             //on doit transformer la ligne en objet agence
                 // si la col 1 est un int ALORS rs.getInt(1) ou rs.getInt("id")
                 //si la col 2 est une chaîne ALORS rs.String(2) ou rs.getString("numero")
                 Etudiant etudiant = new Etudiant(rs.getInt("id"),
                                        rs.getString("nom_complet"),
                                        rs.getString("tuteur"));
                 liste_etudiant.add(etudiant);
             //requête select : relation(BD) vers un objet(JAVA)

         }

        } catch (SQLException e) {
            e.printStackTrace();
        }

 // 5 - Fermer la connexion
    this.fermerConnexionBD();

    return liste_etudiant;
    }
    
    

}
