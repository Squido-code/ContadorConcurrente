package com.guillermo.examen;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;

/**
 * @author Guillermo Suarez
 */

public class Cuenta extends Task<Integer> {
    private int valorInicial,valorFinal;
    private boolean pausado;
    private boolean detener;

    public Cuenta(int valorInicial, int valorFinal) {
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.pausado = false;
        this.detener = false;
    }

    @Override
    protected Integer call() throws Exception {
        if(valorInicial<valorFinal){
            for (int i = valorInicial; i<=valorFinal; i++){
                //pausar el proceso
                while (pausado){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //detener el proceso
                if(detener){
                    break;
                }
                //calculos para la barra de progreso y el indicador de la cuenta
                int proporcion = valorFinal-valorInicial;
                double completado = (i-valorInicial)/(double)proporcion;

                updateProgress(completado,1);
                updateMessage(String.valueOf(i));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        }else {
            for (int i = valorInicial; i >= valorFinal; i--) {
                //pausar el proceso
                while (pausado) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //detener el proceso
                if (detener) {
                    break;
                }
                //calculos para la barra de progreso y el indicador de la cuenta
                int proporcion = valorFinal - valorInicial;
                double completado = (i - valorInicial) / (double) proporcion;

                updateProgress(completado, 1);
                updateMessage(String.valueOf(i));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public void setDetener(boolean detener) {
        this.detener = detener;
    }

    public boolean isDetener() {
        return detener;
    }
}
