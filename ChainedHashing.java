/*
    This program uses the method "Chained Hasing" and is
    made to easy see what a chained hashtable is and how
    it is affected by different parameters.
*/

//package application;

import java.text.DecimalFormat;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class ChainedHashing extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }//main
    private double x;
    private double y;
    private int resizeWindow = -1;
    private double dx;
    private double dy;
    private boolean resizeable = true;

    Label lblOutput = new Label();
    TextFlow tflOutput = new TextFlow();
    Text tStoredNumbers = new Text();
    int arrayLength;
    int number;
    LinkedList llStoredNumbers = new LinkedList();
    SingleLinkedList chain[];
    Label lblLoadFactor = new Label("Hashed values: -\n"
            + "Load factor: -\n"
            + "Average load factor(index): -");
    Button btGenerateHashtable = new Button("Generate hash table");

    @Override
    public void start(Stage primaryStage)
    {
        //Main layout------------------------------
        Scene scnScene;

        Stage stgStage = new Stage();
        stgStage.initStyle(StageStyle.TRANSPARENT);

        //Panes
        BorderPane bpMain = new BorderPane();

        GridPane gpMenu = new GridPane();
        GridPane gpInsert = new GridPane();
        GridPane gpOutput = new GridPane();

        ScrollPane spOutput = new ScrollPane();
        ScrollPane spStoredNumbers = new ScrollPane();

        //Boxes
        HBox hbArray = new HBox();
        HBox hbGenerate = new HBox();
        HBox hbValues = new HBox();
        HBox hbHash = new HBox();
        HBox hbClear = new HBox();
        HBox hbTitleBarRight = new HBox();

        VBox vbStoredNumbers = new VBox();
        VBox vbHashtableTitle = new VBox();

        //Buttons
        Button btInsertValues = new Button("Insert values");
        Button btOKGenerate = new Button("OK");
        Button btClearStoredValues = new Button("Clear values");
        MenuButton mbMenuButton = new MenuButton();
        //Images
        Image iClose = new Image(getClass().getResourceAsStream("PowerS.png"));
        Image iMinimize = new Image(getClass().getResourceAsStream("MinimizeS.png"));
        Image iMaximize = new Image(getClass().getResourceAsStream("MaximizeS.png"));

        ImageView ivClose = new ImageView();
        ImageView ivMinimize = new ImageView();
        ImageView ivMaximize = new ImageView();

        //Constraints
        ColumnConstraints column = new ColumnConstraints();
        RowConstraints row = new RowConstraints();

        //MenuItems
        MenuItem miRandom = new MenuItem();
        MenuItem miAscending = new MenuItem();

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        Menu menuView = new Menu("View");

        MenuItem miExit = new MenuItem ("Exit");
        MenuItem miShowHideStored = new MenuItem ("Show/hide stored values");

        //Texts and labels
        Label lblArrayLength = new Label("Table size:");
        Label lblStoredNumbersHeader = new Label("Stored values (0):");
        Label lblHashtable = new Label("Hashtable:");
        Label lblGenerate = new Label("Generate ");
        Label lblnumbers = new Label("values. ");

        String fancytext = "-fx-font: 25px Tahoma;"
                        + "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, silver 25%, black 50%, silver 75%);"
                        + "-fx-stroke: black;-fx-stroke-width: 1";

        String sRandom = "random";
        String sAscending = "ascending";

        TextField tfArrayLength = new TextField("5");
        TextField tfGenerate = new TextField("20");

        Text txtTitle = new Text("Chained Hashing");
        txtTitle.setStyle(fancytext);

        //Tooltips
        Tooltip ttRandom = new Tooltip("Choose how to generate numbers");

        //Setup
        bpMain.setTop(gpMenu);
        bpMain.setRight(vbStoredNumbers);
        bpMain.setCenter(gpOutput);
        bpMain.setStyle("-fx-border-radius: 15;"
                        + "-fx-background-radius: 15;"
                        + "-fx-border-color: linear-gradient(from 0% 0% to 100% 200%, repeat, black 0%, Silver 25%, black 50%, silver 75%);"
                        + "-fx-border-width: 3;"
                        + "-fx-border-style: solid");

        column.setPercentWidth(100);

        hbGenerate.setAlignment(Pos.CENTER);
        hbHash.setAlignment(Pos.CENTER);
        hbClear.setAlignment(Pos.CENTER);
        hbArray.setAlignment(Pos.CENTER);
        hbTitleBarRight.alignmentProperty().set(Pos.CENTER_RIGHT);
        hbTitleBarRight.setPrefHeight(25);
        hbValues.setAlignment(Pos.CENTER);

        ivClose.setImage(iClose);
        ivClose.setFitHeight(20);
        ivClose.setFitWidth(20);
        ivClose.setSmooth(false);

        ivMaximize.setImage(iMaximize);
        ivMaximize.setFitHeight(20);
        ivMaximize.setFitWidth(20);

        ivMinimize.setImage(iMinimize);
        ivMinimize.setFitHeight(20);
        ivMinimize.setFitWidth(20);

        lblArrayLength.setMinWidth(140);
        lblArrayLength.setAlignment(Pos.CENTER_RIGHT);
        lblStoredNumbersHeader.setMinWidth(140);
        lblStoredNumbersHeader.setAlignment(Pos.CENTER_RIGHT);
        lblHashtable.setAlignment(Pos.CENTER);
        lblLoadFactor.setWrapText(true);

        mbMenuButton.setPrefWidth(90);
        mbMenuButton.setMinWidth(50);
        mbMenuButton.setText(sRandom);
        mbMenuButton.setStyle("-fx-background-radius: 30;\n"
                            +"-fx-background-color: #c3c4c4,"
                            +"linear-gradient(#d6d6d6 50%, white 100%),"
                            +"radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
                            +"-fx-background-insets: 0,1,1;\n"
                            +"-fx-text-fill: black;\n"
                            +"-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 )");

        miRandom.setText(sRandom);
        miAscending.setText(sAscending);

        row.setPercentHeight(100);

        spOutput.setFitToWidth(true);
        spOutput.setStyle("-fx-border-radius: 15;"
                        + "-fx-background-radius: 15;"
                        + "-fx-border-color: transparent;\n"
                        + "-fx-border-insets: 5;"
                        + "-fx-border-width: 3;");
        spStoredNumbers.setPrefWidth(200);

        scnScene = new Scene(bpMain);
        scnScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        scnScene.setFill(null);

        stgStage.setScene(scnScene);
        stgStage.setHeight(600);
        stgStage.setWidth(600);

        tfArrayLength.setPrefWidth(40);
        tfGenerate.setPrefWidth(50);
        Tooltip.install(mbMenuButton, ttRandom);
        tStoredNumbers.setWrappingWidth(185);
        vbHashtableTitle.getChildren().add(lblHashtable);
        vbHashtableTitle.setAlignment(Pos.CENTER);
        mbMenuButton.getItems().addAll(miRandom, miAscending);

        //Add
        gpMenu.add(txtTitle, 0, 0);
        gpMenu.add(hbTitleBarRight, 0, 0);
        gpMenu.add(menuBar, 0, 1);
        gpMenu.add(hbArray, 0, 2);
        gpMenu.add(hbGenerate, 0, 3);
        gpMenu.add(hbValues, 0, 4);
        gpMenu.add(hbHash, 0, 5);
        gpMenu.add(lblLoadFactor, 0, 6);
        gpMenu.getColumnConstraints().add(column);

        gpOutput.getChildren().add(spOutput);
        gpOutput.getRowConstraints().add(row);
        gpOutput.getColumnConstraints().add(column);

        hbArray.getChildren().addAll(lblArrayLength, tfArrayLength);

        hbClear.getChildren().add(btClearStoredValues);

        hbGenerate.getChildren().addAll(lblGenerate, tfGenerate, mbMenuButton, lblnumbers, btOKGenerate);

        hbHash.getChildren().add(btGenerateHashtable);

        hbTitleBarRight.getChildren().addAll(ivMinimize, ivMaximize, ivClose);

        hbValues.getChildren().addAll(btInsertValues);

        menuBar.getMenus().addAll(menuFile, menuView);

        menuFile.getItems().addAll(miExit);

        menuView.getItems().addAll(miShowHideStored);

        spOutput.prefHeightProperty().bind(gpOutput.prefHeightProperty());
        spOutput.setContent(tflOutput);

        spStoredNumbers.setContent(tStoredNumbers);

        vbStoredNumbers.getChildren().add(lblStoredNumbersHeader);
        vbStoredNumbers.getChildren().add(spStoredNumbers);
        vbStoredNumbers.getChildren().add(hbClear);

        //Insert Values Layout--------------------
        //Buttons
        Button btOKInsert = new Button("OK");
        Button btCancel = new Button("Cancel");

        //HBoxes
        HBox hblblInsert = new HBox();
        HBox hbInsert = new HBox();
        HBox hbButtons = new HBox();

        //Text Area
        TextArea txtfldValues = new TextArea();

        //Label
        Label lblInsert = new Label("Enter values to be stored\n"
                                + "(Separate with comma)");

        //Setup/add
        gpInsert.add(hblblInsert, 0, 2);
        gpInsert.add(hbInsert, 0, 3);
        gpInsert.add(hbButtons, 0, 4);
        gpInsert.getColumnConstraints().add(column);

        hbButtons.getChildren().addAll(btCancel, btOKInsert);
        hbButtons.setAlignment(Pos.CENTER);

        hbInsert.getChildren().add(txtfldValues);
        hbInsert.setAlignment(Pos.CENTER);

        hblblInsert.getChildren().add(lblInsert);
        hblblInsert.setAlignment(Pos.CENTER);

        txtfldValues.setPrefSize(150,50);
        txtfldValues.setWrapText(true);


        stgStage.show();

        //Listeners/events-------------------------
        mbMenuButton.setOnMouseEntered((event) ->
        {
            mbMenuButton.setStyle("-fx-background-radius: 30;"
                                + "-fx-background-color: #c3c4c4,"
                                + "linear-gradient(#d6d6d6 50%, black 100%),"
                                + "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"
                                + "-fx-background-insets: 0,1,1;\n"
                                + "-fx-text-fill: black;\n"
                                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        });
        mbMenuButton.setOnMouseExited((event) ->
        {
            mbMenuButton.setStyle("-fx-background-radius: 30;\n"
                                + "-fx-background-color: #c3c4c4,"
                                + "linear-gradient(#d6d6d6 50%, white 100%),"
                                + "radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
                                + "-fx-background-insets: 0,1,1;\n"
                                + "-fx-text-fill: black;\n"
                                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        });
        //Set Close "button" bigger if mouse entered
        ivClose.setOnMouseEntered((event) ->
        {
            ivClose.setFitHeight(25);
            ivClose.setFitWidth(25);
        });//ivClose.setOnMouseEntered

        //Set Close "button" to normal state if mouse exits
        ivClose.setOnMouseExited((event) ->
        {
            ivClose.setFitHeight(20);
            ivClose.setFitWidth(20);
        });//ivClose.setOnMouseExited

        //Exit program if Close "button" is clicked
        ivClose.setOnMouseReleased((event) ->
        {
            System.exit(0);
        });//ivClose.setOnMouseReleased

        //Set Minimize "button" bigger if mouse entered
        ivMinimize.setOnMouseEntered((event) ->
        {
            ivMinimize.setFitHeight(25);
            ivMinimize.setFitWidth(25);
        });//ivMinimize.setOnMouseEntered

        //Set Close "button" to normal state if mouse exits
        ivMinimize.setOnMouseExited((event) ->
        {
            ivMinimize.setFitHeight(20);
            ivMinimize.setFitWidth(20);
        });//ivMinimize.setOnMouseExited

        //Minimize program if "button" is clicked
        ivMinimize.setOnMouseReleased((event) ->
        {
            stgStage.setIconified(true);
        });//ivMinimize.setOnMouseReleased

        //Set Maximize "button" bigger if mouse entered
        ivMaximize.setOnMouseEntered((event) ->
        {
            ivMaximize.setFitHeight(25);
            ivMaximize.setFitWidth(25);
        });//ivMaximize.setOnMouseEntered

        //Set Maximize "button" to normal state if mouse exits
        ivMaximize.setOnMouseExited((event) ->
        {
            ivMaximize.setFitHeight(20);
            ivMaximize.setFitWidth(20);
        });//ivMaximize.setOnMouseExited

        //Maximize program or set back to last state
        //if Maximize button is clicked
        ivMaximize.setOnMouseReleased((event) ->
        {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            if (resizeable == true)
            {
                dy = stgStage.getHeight();
                dx = stgStage.getWidth();
                stgStage.setX(bounds.getMinX());
                stgStage.setY(bounds.getMinY());
                bpMain.getScene().getWindow().setWidth(bounds.getWidth());
                stgStage.setHeight(bounds.getHeight());
                resizeable = false;
            }//if
            else
            {
                bpMain.getScene().getWindow().setWidth(dx);
                bpMain.getScene().getWindow().setHeight(dy);
                resizeable = true;
                stgStage.centerOnScreen();
            }//else
        });//ivMaximize.setOnMouseReleased

        //Registers movement on bpMain
        //If pointer is on buttom or right edge
        //or between; change to resize-cursor
        //if not; default-cursor
        bpMain.setOnMouseMoved((event) ->
        {
            if (resizeable == true)
            {
                if (event.getX() > stgStage.getWidth() - 15
                         && event.getX() < stgStage.getWidth() + 10
                          && event.getY() > stgStage.getHeight() - 15
                         && event.getY() < stgStage.getHeight() + 10)
                    scnScene.setCursor(Cursor.SE_RESIZE);
                else if (event.getY() > stgStage.getHeight() - 10
                         && event.getY() < stgStage.getHeight() + 10)
                    scnScene.setCursor(Cursor.V_RESIZE);
                else if (event.getX() > stgStage.getWidth() - 10
                         && event.getX() < stgStage.getWidth() + 10)
                    scnScene.setCursor(Cursor.H_RESIZE);
                else
                    scnScene.setCursor(Cursor.DEFAULT);
            }//if
        });//bpMain.setOnMouseMoved

        //Register where mouse is pressed
        //if not maximized;
        //deside which resize to use on drag
        bpMain.setOnMousePressed((event) ->
        {
            if (resizeable == true)
            {
                if (event.getX() > stgStage.getWidth() - 15
                    && event.getX() < stgStage.getWidth() + 10
                    && event.getY() > stgStage.getHeight() - 15
                    && event.getY() < stgStage.getHeight() + 10)
                {
                     resizeWindow = 2;
                }//if

                 else if (event.getY() > stgStage.getHeight() - 10
                        && event.getY() < stgStage.getHeight() + 10)
                {
                    resizeWindow = 1;
                }//else if
                 else if (event.getX() > stgStage.getWidth() - 10
                        && event.getX() < stgStage.getWidth() + 10)
                 {
                     resizeWindow = 0;
                 } //else if
            }//if
        });//bpMain.setOnMousePressed

        //Based on mouse pressed, drag will resize program
        bpMain.setOnMouseDragged((event) ->
        {
             if (resizeWindow == 0)
            {
                bpMain.getScene().getWindow().setWidth(event.getX()+8);
                if (event.getX()+8 < 600)
                    bpMain.getScene().getWindow().setWidth(600);
            }//if
             else if (resizeWindow == 1)
            {
                bpMain.getScene().getWindow().setHeight(event.getY() +8);
                if (event.getY()+8 < 400)
                    bpMain.getScene().getWindow().setHeight(400);
            }//else if
            else if (resizeWindow == 2)
            {
                bpMain.getScene().getWindow().setWidth(event.getX());
                bpMain.getScene().getWindow().setHeight(event.getY());
                if (event.getX()+8 < 600)
                    bpMain.getScene().getWindow().setWidth(600);
                if (event.getY()+8 < 400)
                    bpMain.getScene().getWindow().setHeight(400);
            }//else if
        });//bpMain.setOnMouseDragged

        //When mouse is released;set default cursor
        bpMain.setOnMouseReleased((event) ->
        {
             scnScene.setCursor(Cursor.DEFAULT);
             resizeWindow = -1;//Window will not resize on drag
        });//bpMain.setOnMouseReleased

        //Register where poiner is when mous is pressed on "titlebar"
        hbTitleBarRight.setOnMousePressed((event) ->
        {
            x = menuBar.getScene().getWindow().getX() - event.getScreenX();
            y = menuBar.getScene().getWindow().getY() - event.getScreenY();
        });//hbTitleBarRight.setOnMousePressed

        //Replase window when mouse is dragged on "titlebar"
        hbTitleBarRight.setOnMouseDragged((event) ->
        {
            menuBar.getScene().getWindow().setX( event.getScreenX() + x );
            menuBar.getScene().getWindow().setY( event.getScreenY() + y);
        });//hbTitleBarRight.setOnMouseDragged


        //Listener for Show/hide stored values in menuBar
        miShowHideStored.setOnAction((event) ->
        {
            if (bpMain.getRight()==vbStoredNumbers)
                bpMain.setRight(null);
            else
                bpMain.setRight(vbStoredNumbers);
        });//miShowHideStored

        //Listener for "Exit" in menuBar
        //Exits program
        miExit.setOnAction((event) ->
        {
            System.exit(0);
        });//miExit

        //Listener for "ascending" choice
        //Sets mbMenuButton to show "ascending"
        miAscending.setOnAction((event) ->
        {
            mbMenuButton.setText(sAscending);
        });//miAscending

        //Listener for "random" choice
        //Sets mbMenuButton to show "random"
        miRandom.setOnAction((event) ->
        {
            mbMenuButton.setText(sRandom);
        });//miRandom

        //Listener for textfield values
        //Fire OK button if enter is pressed
        txtfldValues.setOnKeyPressed((event) ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                event.consume();
                btOKInsert.fire();
            }
        });//txtfldValues

        //Listener for Insert Value button in main menu
        //Switch to insert view
        btInsertValues.setOnAction((event) ->
        {
            //Set nodes to insertview
            bpMain.setTop(gpInsert);
            gpInsert.add(txtTitle, 0, 0);
            gpInsert.add(hbTitleBarRight, 0, 0);
            gpInsert.add(menuBar, 0, 1);
            gpInsert.add(lblLoadFactor, 0, 5);
        });//btValues Action

        //Listener for cancel button in insertview
        //Switch to main menu if fired
        btCancel.setOnAction((event) ->
        {
            //Set nodes back to main view
            bpMain.setTop(gpMenu);
            gpMenu.add(txtTitle, 0, 0);
            gpMenu.add(hbTitleBarRight, 0, 0);
            gpMenu.add(menuBar, 0, 1);
            gpMenu.add(lblLoadFactor, 0, 6);
        });//btCancel

        //Listener for OK button in insert view
        //Inserts values if input is ok
        btOKInsert.setOnAction((event) ->
        {
            String[] numbersVector;
            numbersVector = txtfldValues.getText().split(",");
            int tryX;
            boolean formatOK = true;

            try//Checks if input is ok
            {
                for (int x = 0; x <= numbersVector.length-1; x++)
                {
                        tryX = Integer.valueOf(numbersVector[x]);
                }//for
            }//try
            //Catch if input is not okay, then alert user
            catch (NumberFormatException nfe)
            {
                Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Field: Values to be stored\n"
                                    + "Something is wrong with your input. Please check!\n"
                                    + "(Example: 1,2,4,7,89,46)");
                alert.showAndWait();
                formatOK = false;
            }//catch
            //If all is okay, then add numbers to sllStoredNumbers
            if (formatOK == true)
            {
                for (int x = 0; x <= numbersVector.length-1; x++)
                {
                        llStoredNumbers.addLast(Integer.valueOf(numbersVector[x]));
                }
                //Set new text in tStoredNumbers ond switch to main menu
                tStoredNumbers.setText(llStoredNumbers.toString());

                //Set nodes back to main view
                bpMain.setTop(gpMenu);
                gpMenu.add(txtTitle, 0, 0);
                gpMenu.add(hbTitleBarRight, 0, 0);
                gpMenu.add(menuBar, 0, 1);
                gpMenu.add(lblLoadFactor, 0, 6);
                btGenerateHashtable.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
            }//if
            //Set new text in Stored Numbers header
            lblStoredNumbersHeader.setText("Stored values (" + llStoredNumbers.size() + "):");
        });//btOKInsert

        //When btGenerateHashtable is fired; check if "array-field" is OK
        //Then generate hashtable
        btGenerateHashtable.setOnAction((event) ->
        {
            boolean arrayLengthOK = true;
            try
            {
                    arrayLength = Integer.parseInt(tfArrayLength.getText());
            }//try
            catch (NumberFormatException nfe)//if not an integer set text to ""
            {
                    tfArrayLength.setText("");
            }//catch
            //Alert if tfArrayLength is not OK
            if (tfArrayLength.getText().equals("") || arrayLength<1)
            {
                Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Field: Length of array\n"
                                + "Please insert a natural number!");
                alert.showAndWait();
                arrayLengthOK = false;
            }
            //If all is OK; Start generating
            if (arrayLengthOK == true)
            {
                chain = new SingleLinkedList[arrayLength];
                for(int a = 0; a<=arrayLength-1 ; a++)
                {
                        chain[a] = new SingleLinkedList();
                }
                hashing();
                generateOutput();
            }//if
        });//btGenerateHashtable

        //When btOKGenerate is fired;
        //Check if tfGenerate is ok, then generate numbers
        //Generate random or ascending depending on choise in mbMenuButton
        btOKGenerate.setOnAction((event) ->
        {
            boolean numberOK = true;
            try
            {
                    number = Integer.parseInt(tfGenerate.getText());
            }//try
            catch (NumberFormatException nfe)//if not an integer set text to ""
            {
                tfGenerate.setText("");
                Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Field: Generate\n"
                                    + "Please insert a natural number!");
                alert.showAndWait();
                numberOK = false;
            }//catch
            if (numberOK == true)
            {
                if (mbMenuButton.getText().equals(sRandom))
                {
                    for (int counter = 0; counter <= number-1; counter++)
                    {
                            llStoredNumbers.addFirst((int)(Math.random() * 1000 + 1));
                    }
                }//if
                else
                {
                    for (int counter = 0; counter <= number-1; counter++)
                    {
                            llStoredNumbers.addLast(counter);
                    }
                    tStoredNumbers.setText(llStoredNumbers.toString());
                }//else
                    tStoredNumbers.setText(llStoredNumbers.toString());
            }//if
            lblStoredNumbersHeader.setText("Stored values (" + llStoredNumbers.size() + "):");
            btGenerateHashtable.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00)");
        });//btOKGenerate


        //Clear all stored values in sllStoredNumbers if clear button is fired
        btClearStoredValues.setOnAction((event) ->
        {
            llStoredNumbers.clear();
            tStoredNumbers.setText(llStoredNumbers.toString());
            lblStoredNumbersHeader.setText("Stored values (" + llStoredNumbers.size() + "):");
        });//btClearStoredValues
    }//start

    //Generates output
    private void generateOutput()
    {
        Double averageLoadFactor = 0.0;
        Double loadFactor = 0.0;
        int hashedValues = 0;

        Text titleText[] = new Text[arrayLength];
        Text link[] = new Text[arrayLength];

        //Is used to format numbers to have only two decimals
        DecimalFormat numberFormat = new DecimalFormat("#.##");

        tflOutput.getChildren().clear();

        //Goes trough all tables an generates a new outputtext for each
        //Also calculates loadfactors for each chain
        for (int x = 0; x <= arrayLength-1; x++)
        {
                titleText[x] = new Text();
                link[x] = new Text();

                hashedValues = hashedValues + chain[x].getElementCount();
                loadFactor = (double)(chain[x].getElementCount())/(llStoredNumbers.size());
                if (loadFactor.equals(Double.NaN))
                    loadFactor = 0.0;
                averageLoadFactor = averageLoadFactor + loadFactor;
                titleText[x].setText("Index: " + (x) + "\t"
                        + "Keys: " + chain[x].getElementCount()+ "\t"
                        + "Load factor: " + numberFormat.format(loadFactor)+"\n");
                titleText[x].setWrappingWidth(0);
                titleText[x].setStyle("-fx-font-weight: bold; -fx-font-size: 15");
                link[x].setText(chain[x].toString());
                tflOutput.getChildren().add(titleText[x]);
                tflOutput.getChildren().add(link[x]);
        }//for
        //calculate average loadfactor and display result in lblLoadFactor
        averageLoadFactor = averageLoadFactor/arrayLength;
        lblLoadFactor.setText("Hashed values: " + hashedValues
                + "\nLoad factor: " + numberFormat.format((double)(llStoredNumbers.size())/arrayLength)
                + "\nAverage load factor(index): " + numberFormat.format(averageLoadFactor));
                        }//generateOutput

    //Hashing metod
    public void hashing()
    {
        //Puts values into the corresponding chain based on arrayLength
        for (int x = 0; x <= llStoredNumbers.size()-1; x++)
        {
                chain[(Integer)llStoredNumbers.get(x) %arrayLength].addBeginning((Integer) llStoredNumbers.get(x));
        }//for
        btGenerateHashtable.setStyle(null);
    }//hashing
}//class Hashing