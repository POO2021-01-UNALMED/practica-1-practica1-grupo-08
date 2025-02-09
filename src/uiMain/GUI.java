package uiMain;
import baseDatos.Deserializacion;
import baseDatos.Serializacion;
import gestorAplicacion.Funcionamiento.Habitacion;
import gestorAplicacion.Funcionamiento.Hotel;
import gestorAplicacion.Personal.Mucama;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/*Clase interfaz principal:
 *Esta clase hereda de Application, por tanto, implementa el m�todo start() que contiene todos los elementos
 *de la intefaz inicio y el m�todo main() que ejecuta el �nico Stage del sistema.
 **/


public class GUI extends Application {
	public static Stage ventana;
	//Escena para la interfaz inicio.
	public static Scene escena1;
	//Escena para la interfaz funcionalidades.
	Scene  escenaFun = new Funcionalidades().getEscenaFun();
	//Contiene la imagen principal del sistema.
	public static Label label;
	
	
	 public void start(Stage ventana) throws Exception{
		 	GUI.ventana = ventana;
		   	ventana.setTitle("Hotel");
		   			   	
		   	//Panel vertical para almacenar saludo e imagenes del hotel.
		   	VBox p1 = new VBox();
		   	Label saludo = new Label("Bienvenidos al hotel, deseamos disfrute su estad�a.\n Cualquier inquietud no dude en contactarse con nosotros.");
		   	saludo.setWrapText(true);
		   	saludo.setPrefSize(400, 200);
		   	Font tipoletra = new Font("Times New Roman", 25);
		   	saludo.setFont(tipoletra);
		   	saludo.setTextFill(Color.web("#873600"));
		   	saludo.setTextAlignment(TextAlignment.CENTER);
		   	saludo.setStyle("-fx-background-color: #FCF3CF ;");
		   	p1.setPadding(new Insets(10,15,10,15));
		   	p1.setSpacing(10);
		   	p1.setPrefSize(400, 550);
		   		   	
		   	//Panel para imagenes hotel y boton para ventana principal
		   	BorderPane pimagenes = new BorderPane();
		   	Button botonprincipal = new Button("Men� principal");

		   	botonprincipal.setStyle("-fx-font-size: 16;-fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;" );
		   	botonprincipal.setOnAction(new Eventos());

		   	
		   	pimagenes.setPrefSize(300, 300);
		   	//Etiqueta para agregar imagenes
		   	Image imagen = new Image(getClass().getResourceAsStream("images.jpg"),350,250,false,false);
		   	label = new Label("", new ImageView(imagen));
		   	pimagenes.setTop(label);
		   	pimagenes.setOnMouseEntered(new EventHandler<MouseEvent>() {
		   		int cont = 0;
		   		public void handle(MouseEvent e) {
				   	Image h1 = new Image(getClass().getResourceAsStream("hotel1.jpeg"),350,250,false,false);
				   	Image h2 = new Image(getClass().getResourceAsStream("hotel2.jpeg"),350,250,false,false);
				   	Image h3 = new Image(getClass().getResourceAsStream("hotel3.jpeg"),350,250,false,false);
				   	Image h4 = new Image(getClass().getResourceAsStream("hotel4.jpg"),350,250,false,false);
				   	Image h5 = new Image(getClass().getResourceAsStream("hotel5.jpeg"),350,250,false,false);				   	
		   			Image[] hv = {imagen, h1, h2, h3, h4, h5};
		   			for(int i = 0; i<4; i++ ) {
		   				int rd = (int) (Math.random() * 5+1);	
		   				
		   				if(label.getGraphic() == null) {
		   				label.setText("");
		   				label.setGraphic(new ImageView(imagen));
		   				}
		   				
		   				if(!label.getGraphic().equals(hv[rd])) {
		   					label.setGraphic(new ImageView(hv[rd]));
		   				}
		   				
		   		}
		   			}
		   	});
		   	p1.setStyle("-fx-background-color: #FCF3CF ;");
		   	pimagenes.setStyle("-fx-background-color:#FCF3CF ;");	   		   	
		   	pimagenes.setBottom(botonprincipal);
		   	p1.getChildren().addAll(saludo,pimagenes);
		   			   	
		   	//Panel vertical para almacenar hoja de vida y fotos de los integrantes del grupo
		   	//que se ir�n cambiando a medida en que se le da click.
		   	VBox p2 = new VBox();
		   	p2.setSpacing(5);
		   	p2.setPadding(new Insets(10,0,0,0));
		   	
		   	Label hojaVida = new Label();
		   	hojaVida.setText("Hojas de vida de los colaboradores.");
		   	hojaVida.setPrefSize(380, 200);
		   	hojaVida.setWrapText(true);
		   	hojaVida.setFont(new Font("Times New Roman", 25));		   	
		   	hojaVida.setStyle("-fx-font-alignment: center");
		   	hojaVida.setPadding(new Insets(0,0,0,10));
		   	hojaVida.setStyle("-fx-border-color: white;"); //Color del borde del label  	
		   	hojaVida.setTextFill(Color.web("#873600"));
		   	p2.getChildren().add(hojaVida);
		    p2.setStyle("-fx-background-color: #FCF3CF ;");
		   	p2.setPrefSize(400,550);
		  
		  //Grid para las im�genes
		   	GridPane fotos = new GridPane();
		   	fotos.setPrefSize(380, 200);
		   	fotos.setStyle("-fx-background-color:#FCF3CF ;");
		   	fotos.setVgap(5);
		   	fotos.setHgap(5);
		   	Image imagen1 = new Image(getClass().getResourceAsStream("hotel1.jpeg"), 187.5, 150, false, false);
		   	Label label1 = new Label("", new ImageView(imagen1)); 	
		   	Image imagen2 = new Image(getClass().getResourceAsStream("hotel2.jpeg"), 187.5, 150, false, false);
		   	Label label2= new Label("", new ImageView(imagen2));   	
		   	Image imagen3= new Image(getClass().getResourceAsStream("hotel3.jpeg"), 187.5, 150, false, false);
		   	Label label3 = new Label("", new ImageView(imagen3));	
		   	Image imagen4 = new Image(getClass().getResourceAsStream("hotel5.jpeg"), 187.5, 150, false, false);
		   	Label label4 = new Label("", new ImageView(imagen4));
		   	fotos.add(label1, 0, 0);
		   	fotos.add(label2, 1, 0);
		   	fotos.add(label3, 0, 1);
		   	fotos.add(label4, 1, 1); 
		   	p2.getChildren().add(fotos);
		   	
		   	p2.setOnMouseClicked(new EventHandler<MouseEvent>(){
		   		int cont = 0;	   					
		   		public void handle(MouseEvent e) {		   					   			
		   			String[] hv = {"h","                              Hoja de Vida \nNombres y apellidos: Ximena Casta�eda Ochoa \nEdad: 19 a�os \nCorreo: xcastaneda@unal.edu.co \nEstudiante de Estad�stica en la Universidad Nacional de Colombia cuarto semestre. \nSoy una persona responsable, atenta y de aprendizaje r�pido.", 
		   					"                              Hoja de Vida \nNombres y apellidos: Yojan Andr�s Alcaraz P�rez\nEdad: 19 a�os \nCorreo: yalcaraz@unal.edu.co \nEstudiante de Estad�stica en la Universidad Nacional de Colombia quinto semestre. \nSoy una persona responsable, respetuosa y dedicada.",
		   					"                              Hoja de Vida \nNombres y apellidos: Ver�nica Seguro Varela \nEdad: 21 a�os \nCorreo: vseguro@unal.edu.co \nEstudiante de Estad�stica en la Universidad Nacional de Colombia quinto semestre. \nSoy una persona proactiva, tolerante y responsable."};
		   			for(int i = 0; i<4; i++ ) {
		   				int rd = (int) (Math.random() * 3+1);	
		   				
		   				if(hv[rd] != hojaVida.getText()) {
		   					hojaVida.setText(hv[rd]);
		   					hojaVida.setFont(new Font("Times New Roman", 18));
		   					hojaVida.setTextAlignment(TextAlignment.JUSTIFY);
		   					hojaVida.setTextFill(Color.web("black"));		   					
		   					cont = rd;		   								   						
		   				}
		   				if (cont == 1) {
		   					fotos.getChildren().clear();
		   					Image imagen1 = new Image(getClass().getResourceAsStream("Ximena1.jpeg"), 187.5, 150, false, false);
		   				   	Label label1 = new Label("", new ImageView(imagen1)); 	
		   				   	Image imagen2 = new Image(getClass().getResourceAsStream("Ximena2.jpeg"), 187.5, 150, false, false);
		   				   	Label label2= new Label("", new ImageView(imagen2));   	
		   				   	Image imagen3= new Image(getClass().getResourceAsStream("Ximena4.jpeg"), 187.5, 150, false, false);
		   				   	Label label3 = new Label("", new ImageView(imagen3));	
		   				   	Image imagen4 = new Image(getClass().getResourceAsStream("Ximena3.jpeg"), 187.5, 150, false, false);
		   				   	Label label4 = new Label("", new ImageView(imagen4));
		   				   	fotos.add(label1, 0, 0);
		   				   	fotos.add(label2, 1, 0);
		   				   	fotos.add(label3, 0, 1);
		   				   	fotos.add(label4, 1, 1);
		   				    //p2.getChildren().add(fotos);
		   				}
		   				else if(cont == 2) {
		   					fotos.getChildren().clear();
		   					Image imagen1 = new Image(getClass().getResourceAsStream("Yojan1.jpeg"), 187.5, 150, false, false);
		   				   	Label label1 = new Label("", new ImageView(imagen1)); 	
		   				   	Image imagen2 = new Image(getClass().getResourceAsStream("Yojan2.jpeg"), 187.5, 150, false, false);
		   				   	Label label2= new Label("", new ImageView(imagen2));   	
		   				   	Image imagen3= new Image(getClass().getResourceAsStream("Yojan4.jpeg"), 187.5, 150, false, false);
		   				   	Label label3 = new Label("", new ImageView(imagen3));	
		   				   	Image imagen4 = new Image(getClass().getResourceAsStream("Yojan3.jpeg"), 187.5, 150, false, false);
		   				   	Label label4 = new Label("", new ImageView(imagen4));
		   				   	fotos.add(label1, 0, 0);
		   				   	fotos.add(label2, 1, 0);
		   				   	fotos.add(label3, 0, 1);
		   				   	fotos.add(label4, 1, 1);
		   				}
		   				else if(cont == 3) {
		   					fotos.getChildren().clear();
		   					Image imagen1 = new Image(getClass().getResourceAsStream("Veronica1.jpeg"), 187.5, 150, false, false);
		   				   	Label label1 = new Label("", new ImageView(imagen1)); 	
		   				   	Image imagen2 = new Image(getClass().getResourceAsStream("Veronica2.jpeg"), 187.5, 150, false, false);
		   				   	Label label2= new Label("", new ImageView(imagen2));   	
		   				   	Image imagen3= new Image(getClass().getResourceAsStream("Veronica4.jpeg"), 187.5, 150, false, false);
		   				   	Label label3 = new Label("", new ImageView(imagen3));	
		   				   	Image imagen4 = new Image(getClass().getResourceAsStream("Veronica3.jpeg"), 187.5, 150, false, false);
		   				   	Label label4 = new Label("", new ImageView(imagen4));
		   				   	fotos.add(label1, 0, 0);
		   				   	fotos.add(label2, 1, 0);
		   				   	fotos.add(label3, 0, 1);
		   				   	fotos.add(label4, 1, 1);
		   				}
		   			}
		   		}
		   	});
		   	   	
		   
		   	//Panel principal, contiene el saludo de bienvenida, el panel con las fotos y hojas de vidas y el panel con las fotos del sistema.
		   	BorderPane principal = new BorderPane();
		   	principal.setLeft(p1);
		   	principal.setRight(p2);
		   	MenuBar barramenu = new MenuBar();
		   	Menu inicio = new Menu("Inicio");
		   	barramenu.getMenus().add(inicio);
		   	principal.setTop(barramenu);
		   	principal.setStyle("-fx-background-color:#FCF3CF ;");
		   	MenuItem salir = new MenuItem("Salir");
		   	salir.setOnAction(new Eventos());
			MenuItem descripcion = new MenuItem("Descripci�n");
			descripcion.setOnAction(new Eventos());
			
			SeparatorMenuItem separador = new SeparatorMenuItem();
			inicio.getItems().addAll(salir,separador,descripcion);
		   		
		   	//Escena 1: Ventana de incio.
		   	escena1 = new Scene(principal,800,550);
		   	ventana.setResizable(false);
		   	ventana.setScene(escena1);
	        ventana.show();
	    }
	
	
	public static void main(String[] args) {
		//M�todos para deserializar los objetos previamente creados
		Deserializacion.deserializar();
		Habitacion.aumentarCapacidad();
		launch(args);
	
	}
	
	/*Retorna la escena de inicio cuando se requiere en otras clases para cambiar de escena o modificar 
	alguno de sus elementos.*/
	public Scene getEscenaGUI() {
		return escena1;
	}
	
	class Eventos implements EventHandler<ActionEvent>{
		
			public void handle(ActionEvent e) {
				Object control = e.getSource();
				if (control instanceof MenuItem) { 
					if(((MenuItem) control).getText().equals("Salir")) {
						Serializacion.serializacion();
						Platform.exit();
						}
					else if(((MenuItem) control).getText().equals("Descripci�n")) {
						label.setGraphic(null);
						label.setText("La aplicaci�n permite realizar las diferentes funciones que se presentan en el hotel,"
					+ "tales como el ingreso de los clientes, la selecci�n de la habitaci�n que se acomode a sus necesidades, "
					+ "la variedad de men�s que pueden elegir y las atracciones disponibles para disfrutar de la estad�a. "
					+ "\n" + "\n" 
					+ "Adem�s permite el acceso del personal encargado de estos procesos para un correcto funcionamiento del "
					+ "hotel. Entre ellos se destacan al administrador, encargado de pagar el salario de los empleados incluyendo "
					+ "las horas extras que estos validen; las mucamas, encargadas de mantener las habitaciones en orden y disponibles "
					+ "cuando se requiera  y el recepcionista, quien tiene el control de la entrada y salida de los clientes y del hotel "
					+ "en general.");
						label.setGraphic(null);
						label.setWrapText(true);
						label.setTextAlignment(TextAlignment.JUSTIFY);
						Font tipoletra = new Font("Times New Roman", 18);
						label.setFont(tipoletra);
					}
					}
				else if(control instanceof Button) {
					if(((Button) control).getText().equals("Men� principal")) {
							GUI.ventana.setScene(escenaFun);
						}
					}
	}
			
	
}
	
}
