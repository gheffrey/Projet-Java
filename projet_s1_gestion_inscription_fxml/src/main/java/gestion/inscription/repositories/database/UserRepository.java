package gestion.inscription.repositories.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import gestion.inscription.core.MySqlDB;
import gestion.inscription.entities.Role;
import gestion.inscription.entities.User;
import gestion.inscription.repositories.IUserRepository;

public class UserRepository extends MySqlDB implements IUserRepository {

    @Override
    public User findUserByLoginAndPassword(String login, String password){
        
        final String SQL_CONNECT = "SELECT * FROM `user` WHERE `login` LIKE ? AND `mdp` LIKE ?";
        
        User user=null;

            this.ouvrirConnexionBD();
            try {
                  ps= conn.prepareStatement(SQL_CONNECT);
                  ps.setString(1, login); 
                  ps.setString(2, password); 
                  ResultSet rs=ps.executeQuery();
                  if(rs.next()){
                    user=new User(
                        rs.getInt("id"), 
                        rs.getString("login"),
                        rs.getString("mdp"),
                        rs.getString("nom_complet") ,
                        rs.getString("role").compareTo("AC")==0? Role.AC:Role.RP  );
                      
                      /*** 
                        if(rs.getString("role").compareTo("Client")!=0){
                           Agence agence =new Agence(rs.getInt("agence_id"));
                           user.setAgence(agence);
                      }
                  }
                  */
    
                }  } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
            this.fermerConnexionBD();
            return user;
    }
    
}
