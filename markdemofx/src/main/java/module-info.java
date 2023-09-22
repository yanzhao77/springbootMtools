module com.example.markdemofx {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires MathParser.org.mXparser;

    opens com.example.markdemofx to javafx.fxml;
    exports com.example.markdemofx;
}