module com.mycompany.exameninterfaces2evloren {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;

    opens com.mycompany.exameninterfaces2evloren to javafx.fxml;
    exports com.mycompany.exameninterfaces2evloren;
}
