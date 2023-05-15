module com.bingofx.bingofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.bingofx.bingofx to javafx.fxml;
    exports com.bingofx.bingofx;
}