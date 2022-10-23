package gestion.inscription.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gestion.inscription.core.Fabrique;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClasseController implements Initializable{
    @FXML
    TableView<Classe> tblcClasse=new TableView<>();
    @FXML
    TableColumn<Classe,String> tblcId = new TableColumn<>();
    @FXML
    TableColumn<Classe,String> tblcLibelle = new TableColumn<>();
    @FXML
    TextField txtLibelle;
    @FXML
    Button btnAddClasse;

    //Pour convertir les résultats de la bd (liste/tableau)
    //en observableList pour les vues fxml
    private ObservableList obClasses = FXCollections.observableList(Fabrique.getService().lister_classe());
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(ConnexionController.user.getRole()==Role.AC){
           btnAddClasse.setDisable(true);
        }
        // TODO Auto-generated method stub
       //Pour associer les données vues - codes
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        tblcClasse.setItems(obClasses);
    }

    public void handleCreerClasse(){
        //récupération des données
        String libelle = txtLibelle.getText().trim();
        
        //on créé et insère la nouvele classe
        Classe classe = Fabrique.getService().creerClasse(new Classe(libelle));

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Gestion d'inscription ISM");
        alert.setContentText("Classe ajoutée !");
        alert.show();

        //MAJ des données et de l'affichage
        obClasses.add(classe);

        //On vide les champ de text
        txtLibelle.clear();
    }
}
