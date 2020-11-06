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
        this.stage = new Stage();
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

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(this.FXMLdirectory));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // scene.getStylesheets().add(getClass().getResource("Ventana1CSS.css").toExternalForm());

        this.stage.initModality(this.modality);
        this.stage.setScene(scene);
        this.setStage(stage);
        this.stage.show();
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
