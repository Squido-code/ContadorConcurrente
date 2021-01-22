package com.guillermo.examen;

import com.guillermo.examen.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Guillermo Suarez
 */

public class App extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(R.getUI("uiPrincipal.fxml"));
        loader.setController(new AppController());
        VBox vbox = loader.load();

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
    public static void main( String[] args )
    {
        launch();
    }
}
