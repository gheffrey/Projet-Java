package gestion.inscription.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestion.inscription.App;
import gestion.inscription.core.Fabrique;
import gestion.inscription.core.Validator;
import gestion.inscription.entities.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController implements Initializable {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    //on récupère la variable error sur la page de connexion
    @FXML
    Text lblError,lblErrorLogin,lblErrorPassword;

    @FXML
    TextField txtLogin;

    @FXML
    PasswordField txtPassword;

    @FXML
    Button btnConnexion;

    SimpleBooleanProperty smpl=new SimpleBooleanProperty(false);


    @Override
    public void initialize(URL location, ResourceBundle ressources){
        lblError.setVisible(false);
        lblErrorLogin.setVisible(false);
        lblErrorPassword.setVisible(false);
            txtLogin.textProperty().addListener((obv,old,newV)->{
                if(newV.isEmpty()){
                    lblErrorLogin.setVisible(true);
                }
                else{
                    if(!Validator.isEmail(newV)){
                        lblErrorLogin.setText("veuillez saisir un email");
                        lblErrorLogin.setVisible(true);
                    }else{
                        smpl.set(txtPassword.getText().isEmpty() );
                        lblErrorLogin.setVisible(false);
                    }
                }
            });
        
            txtPassword.textProperty().addListener((obv,old,newV)->{
                if(newV.isEmpty()){
                    lblErrorPassword.setVisible(true);
                }else{
                      smpl.set(!Validator.isEmail(txtLogin.getText()));
                     lblErrorPassword.setVisible(false);  
                }
            });

        //on active le bouton si les champs sont remplis;
               btnConnexion.disableProperty().bind(smpl);
           

    }

    public static User user; 


    //Les fonctions déclanchées par une action comme 'appuyer sur un bouton'
    //sont généralement appelées handle
    public void handleConnexion() throws IOException{

        String login = txtLogin.getText().trim();
        String password = txtPassword.getText().trim();

        user = Fabrique.getService().seConnecter(login, password);
        
        if(user==null){
            lblError.setVisible(true);
        }else{
            lblError.setVisible(false);
            App.setRoot("home");
        }
    
}
}