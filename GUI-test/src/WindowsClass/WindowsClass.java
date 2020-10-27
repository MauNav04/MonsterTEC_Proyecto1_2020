package WindowsClass;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowsClass {
    protected Stage stage;
    protected String FXMLdirectory;
    protected boolean EverPriority;

    public WindowsClass(String FXMLdirectory) {
        this.stage = new Stage();
        this.FXMLdirectory = FXMLdirectory;
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
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        this.setStage(stage);
        stage.show();
    }

    protected boolean isEverPriority() {
        return EverPriority;
    }

    protected void setEverPriority(boolean everPriority) {
        EverPriority = everPriority;
         if(EverPriority==true){
             this.stage.initModality(Modality.APPLICATION_MODAL);
         }
         else{ this.stage.initModality(Modality.WINDOW_MODAL); }
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
}
