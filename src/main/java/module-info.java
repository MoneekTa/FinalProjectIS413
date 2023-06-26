module com.denisemoneek.finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.denisemoneek.finalproject to javafx.fxml;
    exports com.denisemoneek.finalproject;
}