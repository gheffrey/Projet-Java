package gestion.inscription.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gestion.inscription.core.Fabrique;
import gestion.inscription.entities.Classe;
import gestion.inscription.entities.Etudiant;
import gestion.inscription.entities.Inscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InscriptionController implements Initializable {
    @FXML
    private TableColumn<Classe, String> tblcClasse;

    @FXML
    private TableColumn<String, String> tblcDate;

    @FXML
    private TableColumn<Etudiant, String> tblcEtudiant;

    @FXML
    private TableView<Inscription> tblvInscription;

    @FXML
    private TextField txtAnnee;

    private ObservableList obInscription = FXCollections.observableList(Fabrique.getService().lister_inscription());


    

    public void handleSearchInscription() {

        String annee = txtAnnee.getText().trim();
        obInscription = FXCollections.observableList(Fabrique.getService().filtreParAn(annee));
        
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date_inscription"));
        tblcClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tblcEtudiant.setCellValueFactory(new PropertyValueFactory<>("etudiant"));


        tblvInscription.setItems(obInscription);
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date_inscription"));
        tblcClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tblcEtudiant.setCellValueFactory(new PropertyValueFactory<>("etudiant"));


        tblvInscription.setItems(obInscription);
        
    }


}
