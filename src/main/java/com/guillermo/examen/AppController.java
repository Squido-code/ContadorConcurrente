package com.guillermo.examen;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Guillermo Suarez
 */

public class AppController implements Initializable {
    public TextField tfInicial,tfFinal;
    public Label lbCompletado,lbEstado,lbProgreso;
    public ProgressBar pgProgreso;
    public Button btIniciar,btPausar,btDetener;
    int valorInicial,valorFinal;
    private Cuenta hilo;

    @FXML
    public void iniciar(Event event){

        //comprobamos que el valor inical es mayor que el final
        valorInicial = Integer.parseInt(tfInicial.getText());
        valorFinal = Integer.parseInt(tfFinal.getText());

        if(valorInicial>valorFinal){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Aviso");
            alerta.setContentText("El valor inicial no puede superar al valor final");
            alerta.showAndWait();
            return;
        }
        //creamos el hilo con la cuenta
        hilo = new Cuenta(valorInicial,valorFinal);

        pgProgreso.progressProperty()
                .bind(hilo.progressProperty());
        pgProgreso.getProgress();
        hilo.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                    lbCompletado.setText(newValue+" de "+ valorFinal);
                    double progreso = (Integer.parseInt(newValue)-valorInicial)* 100/(valorFinal-valorInicial);
                    lbProgreso.setText(String.valueOf(progreso) +  " %");
                });


        hilo.setOnSucceeded((WorkerStateEvent event2 ) -> {
            if(!hilo.isDetener()){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Informacion");
                info.setContentText("La cuentra atras ha finalizado");
                info.showAndWait();
                pgProgreso.progressProperty()
                        .unbind();
                pgProgreso.setProgress(0);
                lbCompletado.setText("");
                lbProgreso.setText("");
            }
        });
        //comprobamos que no este detenido y los reiniciamos
        if(hilo.isDetener()){
            hilo.setDetener(false);
        }

        new Thread(hilo).start();

    }
    @FXML
    public void inicioCuentaAtras (){

        valorInicial = Integer.parseInt(tfInicial.getText());
        valorFinal = Integer.parseInt(tfFinal.getText());

        if(valorInicial<valorFinal){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Aviso");
            alerta.setContentText("El valor final no puede superar al valor inicial");
            alerta.showAndWait();
            return;
        }
        //creamos el hilo con la cuenta
        hilo = new Cuenta(valorInicial,valorFinal);

        pgProgreso.progressProperty()
                .bind(hilo.progressProperty());
        pgProgreso.getProgress();
        hilo.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                    lbCompletado.setText(newValue+" de "+ valorFinal);
                    double progreso = (Integer.parseInt(newValue)-valorInicial)* 100/(valorFinal-valorInicial);
                    lbProgreso.setText(String.valueOf(progreso) +  " %");
                });


        hilo.setOnSucceeded((WorkerStateEvent event2 ) -> {
            if(!hilo.isDetener()){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Informacion");
                info.setContentText("La cuentra atras ha finalizado");
                info.showAndWait();
                pgProgreso.progressProperty()
                        .unbind();
                pgProgreso.setProgress(0);
                lbCompletado.setText("");
                lbProgreso.setText("");
            }
        });
        //comprobamos que no este detenido y los reiniciamos
        if(hilo.isDetener()){
            hilo.setDetener(false);
        }

        new Thread(hilo).start();
    }
    @FXML
    public void pausar(Event event){

        if(btPausar.getText() == "pausar"){
            hilo.setPausado(true);
            btPausar.setText("continuar");
            lbEstado.setText("Cuenta pausada");
        }else {
            btPausar.setText("pausar");
            hilo.setPausado(false);
            lbEstado.setText("");
        }
    }
    @FXML
    public void detener(Event event){
        hilo.setDetener(true);
        pgProgreso.progressProperty()
                .unbind();
        pgProgreso.setProgress(0);
        lbCompletado.setText("");
        lbProgreso.setText("");

        if(btPausar.getText() == "continuar"){
            btPausar.setText("pausar");
            lbEstado.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btPausar.setText("pausar");//por alguna razon no coge bien el texto y tengo que editarlo aqui.
    }
}
