package Ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowsClass {
    protected Stage stage;
    protected WindowsClass previus;




    public WindowsClass(){
        this.stage = new Stage();
    }

    /**
     * Carga todos los elementos de la interfaz
     *
     */


    public void build(Parent root) throws IOException {
        Scene scene = new Scene(root);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.show();
    }


    public void show() throws IOException {
        this.stage.show();
    }

    public void setPrevius(WindowsClass win){
        this.previus = win;

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
