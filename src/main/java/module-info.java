module com.hotelbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;

    opens com.hotelbooking to javafx.fxml;
    opens com.hotelbooking.controller to javafx.fxml;
    opens com.hotelbooking.models to javafx.fxml;

    exports com.hotelbooking;
}