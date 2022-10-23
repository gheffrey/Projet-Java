module gestion.inscription {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires mysql.connector.java;
    requires com.fasterxml.jackson.databind;


    opens gestion.inscription.controllers to javafx.fxml;
    exports gestion.inscription;
    exports gestion.inscription.entities;
}
