package Ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowsClass {
    protected Stage stage;
    protected String FXMLdirectory;
    protected Modality modality;
    protected boolean EverPriority;

    public WindowsClass(String FXMLdirectory) {
        this.FXMLdirectory = FXMLdirectory;
        this.modality = Modality.WINDOW_MODAL;

    }

    public WindowsClass(){
        this.stage = new Stage();
    }

    /**
     * Carga todos los elementos de la interfaz
     *
     */


    public void show(Parent root) throws IOException {
        Scene scene = new Scene(root);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.show();
    }


    public void build() throws IOException {

    }


    public boolean isEverPriority() {
        return EverPriority;
    }

    public void setEverPriority(boolean everPriority) {
        EverPriority = everPriority;
         if(EverPriority==true){
             this.modality = Modality.APPLICATION_MODAL;
             this.stage.initModality(this.modality);
         }
         else{
             this.modality = Modality.WINDOW_MODAL;
             this.stage.initModality(this.modality); }
    }

    /**
     * Cierra  la ventana
     */
    public void close(){
        this.stage.close();
    }

    /**
     * Indicar el stage de la clase
     *
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage(){
        return this.stage;
    }
}
