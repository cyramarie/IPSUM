package sample;


import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

// For libraries for generating receipt
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class MenuPanel extends Juices implements Initializable {

    Juices js = new Juices(); //Create object of the Juices class
    int i; //number of orders in the table

    /**the following variables hold values that enter the database**/
    int[] qty1 = new int[20];
    public String[] name1 = new String[20];
    double[] carb1 = new double[20];
    double[] protein1 = new double[20];
    double[] calories1 = new double[20];
    int[] price1 = new int[20];
    int sumPrice1;
    double sumProtein1;
    double sumCarbs1;
    double sumCalories1;
    int ordernumber = 1, plateNum = 1;
    String bankName ="none", paymentMethod, accountName="none", accountNumber="none";


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddToPlate;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDrink1;

    @FXML
    private Button btnDrink2;

    @FXML
    private Button btnDrink3;

    @FXML
    private Button btnDrink4;

    @FXML
    private Button btnDrink5;

    @FXML
    private Button btnDrink6;

    @FXML
    private Button btnDrink7;

    @FXML
    private Button btnDrink8;

    @FXML
    private Button btnDrink9;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnVoid;


    @FXML
    private ChoiceBox<String> chSize;
    String[] chSize1 = {"Small --100mL", "Medium --200mL", "Large --300mL"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //for the table
        chSize.getItems().addAll(chSize1);
        calories.setCellValueFactory(new PropertyValueFactory<Order, Double>("calories"));
        protein.setCellValueFactory(new PropertyValueFactory<Order, Double>("protein"));
        carbs.setCellValueFactory(new PropertyValueFactory<Order, Double>("carbs"));
        drinkName.setCellValueFactory(new PropertyValueFactory<Order, String>("drinkName"));
        price.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
        Qty.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Qty"));

    }

    @FXML
    private TextField txtCalories;

    @FXML
    private TextField txtCarbs;

    @FXML
    private TextField txtFat;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtFiber;

    @FXML
    private TextField txtIngredients;

    @FXML
    private TextField txtProtein;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtTotalBill;

    @FXML
    private TextField txtTotalCalories;

    @FXML
    private TextField txtTotalNetCarb;

    @FXML
    private TextField txtTotalProtein;

    @FXML
    private TableColumn<Order, Double> calories;

    @FXML
    private TableColumn<Order, Double> carbs;

    @FXML
    private TableColumn<Order, String> drinkName;

    @FXML
    private TableColumn<Order, Integer> price;

    @FXML
    private TableColumn<Order, Double> protein;
    @FXML
    private TableColumn<Order, Integer> Qty;

    @FXML
    private TableView<Order> table;

    // Here to get information on receipt
    @FXML
    void AddToPlate(ActionEvent event) {
        js.checkDrink();
        js.finalOrder(chSize.getValue());

        //for the table
        Order order = new Order(js.Qty, js.drinkName + " @ " + js.sizeName, js.price, js.carbs, js.protein, js.calories);
        ObservableList<Order> orders = table.getItems();
        orders.add(order);
        table.setItems(orders);

        i++; //increment number of orders every click of Add to Plate Button
        getSum();
    }

    @FXML
    void Back(ActionEvent event) { //Return or switch to Main Panel
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?");
        if (result == JOptionPane.YES_OPTION) {backToMainPanel();}
    }
    public void backToMainPanel(){
        try {
            Stage nextScene = (Stage) btnBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            nextScene.setTitle("IPSUM");
            nextScene.setScene(new Scene(root)); // set the second scene as the sample2.fxml in which its controller is the Controller2 Class
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    void Clear(ActionEvent event) { //Clear the orders in the table
        i = 0;
        table.getItems().clear();
        getSum();
    }

    @FXML
    void Void(ActionEvent event) { //Method to remove selected items in the table
        try {
            int selectedID = table.getSelectionModel().getSelectedIndex();
            table.getItems().remove(selectedID);
            i--;
            getSum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select  what order to remove", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void Minus(ActionEvent event) { //Method to reduce quantity
        js.Qty--;
        if (js.Qty < 1) { //if quantity became 0, AddToPlate button will be disabled
            js.Qty = 0;
            btnAddToPlate.setDisable(true);
        }
        txtQty.setText(" " + js.Qty);
    }

    @FXML
    void Plus(ActionEvent event) { //Method to increase quantity
        js.Qty++;
        txtQty.setText(" " + js.Qty);
        if (js.Qty > 0) {
            btnAddToPlate.setDisable(false);
        }
    }


    public void setDefault() { //default values
        chSize.setValue("Small --100mL");
        js.Qty = 1;
        txtQty.setText("" + js.Qty);
        btnAdd.setDisable(false);
        btnMinus.setDisable(false);
        btnAddToPlate.setDisable(false);
    }



    public void setDrink() { //set values in text boxes
        js.checkDrink();
        txtCalories.setText("" + js.calories);
        txtCarbs.setText("" + js.carbs);
        txtFat.setText("" + js.fat);
        txtFiber.setText("" + js.fiber);
        txtIngredients.setText("" + js.ingredients);
        txtProtein.setText("" + js.protein);
        txtName.setText("" + js.drinkName);
    }




    public void getSum() { //WILL GET THE TOTAL VALUE OF EACH COLUMN IN THE TABLE
        js.sumPrice = 0;
        js.sumProtein = 0;
        js.sumCarbs = 0;
        js.sumCalories = 0;
        for (int x = 0; x < i; x++) {
            js.sumPrice = js.sumPrice + Integer.valueOf(String.valueOf(table.getColumns().get(5).getCellObservableValue(x).getValue()));
            js.sumCalories = js.sumCalories + Double.valueOf(String.valueOf(table.getColumns().get(4).getCellObservableValue(x).getValue()));
            js.sumProtein = js.sumProtein + Double.valueOf(String.valueOf(table.getColumns().get(3).getCellObservableValue(x).getValue()));
            js.sumCarbs = js.sumCarbs + Double.valueOf(String.valueOf(table.getColumns().get(2).getCellObservableValue(x).getValue()));
        }
        sumCalories1=js.sumCalories;
        sumCarbs1=js.sumCarbs;
        sumPrice1=js.sumPrice;
        sumProtein1=js.sumProtein;
        txtTotalBill.setText("" + js.sumPrice);
        txtTotalProtein.setText("" + js.sumProtein);
        txtTotalNetCarb.setText("" + js.sumCarbs);
        txtTotalCalories.setText("" + js.sumCalories);
    }



    public void payment(){ //GET PAYMENT DETAILS

        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.orange);
        UI.put("Panel.background", Color.orange);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));

        String[] option ={ "CASH", "CARD"};
        String[] bank ={"BDO", "BPI", "LBP", "Metrobank", "PNB", "LANDBANK", "RCBC", "China Bank","DBP", "UnionBank"};
        int paymentMethodTemp = JOptionPane.showOptionDialog(null, "CHOOSE PAYMENT METHOD", "PAYMENT METHOD ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if (paymentMethodTemp==1){
            paymentMethod ="CARD";
            int bankNameTemp = JOptionPane.showOptionDialog(null, "CHOOSE YOUR BANK", "BANK", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, bank, bank[0]);
            bankName = bank[bankNameTemp];
            accountName = JOptionPane.showInputDialog("Enter Account Name:");
            accountNumber = JOptionPane.showInputDialog("Enter Account Number");
        }
        else{
            paymentMethod = "CASH";
        }
    }

// Here to get information on receipt

    @FXML
    void Done(ActionEvent event) {
        try {
            if (i > 0) { //if there is an order in the TableView
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you of your order?");
                if (result == JOptionPane.YES_OPTION) {
                    tableToArray(); // get the values from tableview and store it in arrays
                    payment(); //get payment details
                    orderNumGenerator(); //retrieve orderNumber or current order (Database)
                    insertDataOrderTable(); //insertion in orders table (Database)
                    insertDataOrderDetailsTable(); //insertion in orderdetails table(Database)
                    generateReceipt();
                    backToMainPanel(); //back to main panel
                }
            } else {
                //No orders in the tableView
                JOptionPane.showMessageDialog(null, "PLEASE SELECT AN ITEM TO ORDER!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    /**
     METHODS TO RETRIEVED INFORMATION IN JUICE CLASS REGARDING THE SELECTED DRINK
     ***/
    @FXML
    void drinkEight(ActionEvent event) {
        js.drink = "drink8";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkFive(ActionEvent event) {
        js.drink = "drink5";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkFour(ActionEvent event) {
        js.drink = "drink4";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkNine(ActionEvent event) {
        js.drink = "drink9";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkOne(ActionEvent event) {
        js.drink = "drink1";
        setDrink();
        setDefault();

    }

    @FXML
    void drinkSeven(ActionEvent event) {
        js.drink = "drink7";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkSix(ActionEvent event) {
        js.drink = "drink6";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkThree(ActionEvent event) {
        js.drink = "drink3";
        setDrink();
        setDefault();
    }

    @FXML
    void drinkTwo(ActionEvent event) {
        js.drink = "drink2";
        setDrink();
        setDefault();

    }



    /****
     Get the values in each column in tableView and store it in its corresponding arrays
     ****/

    // Here to get information in receipt
    public void tableToArray() {
        for (int x = 0; x < i; x++) {
            price1[x] = Integer.valueOf(String.valueOf(table.getColumns().get(5).getCellObservableValue(x).getValue()));
            calories1[x] = Double.valueOf(String.valueOf(table.getColumns().get(4).getCellObservableValue(x).getValue()));
            protein1[x] = Double.valueOf(String.valueOf(table.getColumns().get(3).getCellObservableValue(x).getValue()));
            carb1[x] = Double.valueOf(String.valueOf(table.getColumns().get(2).getCellObservableValue(x).getValue()));
            name1[x] = String.valueOf(table.getColumns().get(1).getCellObservableValue(x).getValue());
            qty1[x] = Integer.valueOf(String.valueOf(table.getColumns().get(0).getCellObservableValue(x).getValue()));
        }

    }


    /********
     DATABASE PART
     ********/

    public void orderNumGenerator(){ // Get the OrderNumber in Database of the current order
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ipsum", "root", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(orderNumber) FROM orders");
            rs.next();

            ordernumber = rs.getInt("MAX(orderNumber)") + 1;

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public void insertDataOrderDetailsTable() throws SQLException, ClassNotFoundException { //INSERT IN ORDERDETAILS TABLE

        try {
            for (int x = 0; x < i; x++) {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ipsum", "root", "");
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO orderdetails (plateNo, orderNo, qty, onPlate, netCarb, protein, calories, price)" + "VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,plateNum);
                preparedStatement.setInt(2, ordernumber);
                preparedStatement.setInt(3, qty1[x]);
                preparedStatement.setString(4, name1[x]);
                preparedStatement.setDouble(5, carb1[x]);
                preparedStatement.setDouble(6, protein1[x]);
                preparedStatement.setDouble(7, calories1[x]);
                preparedStatement.setInt(8, price1[x]);
                preparedStatement.executeUpdate();
                plateNum++;
                connection.close();
            }
            JOptionPane.showMessageDialog(null, "Order Recorded!\nYour Order No.: " +ordernumber);
            JOptionPane.showMessageDialog(null, "Please get your receipt. \nYour order will be confirmed after payment in the cashier. THANK YOU!");
        } catch (Exception e) {
            e.printStackTrace();

        }}

    public void insertDataOrderTable() throws SQLException, ClassNotFoundException { //INSERT in ORDERS TABLE

        try {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(d);


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ipsum", "root", "");
            Statement statement = connection.createStatement();
            String mysql = "INSERT INTO orders (orderNumber, totalBill, totalCarb, totalProtein, totalCalories, Date, paymentMode, bankName, accountName, accountNumber)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(mysql);
            preparedStatement.setInt(1, ordernumber);
            preparedStatement.setInt(2, sumPrice1);
            preparedStatement.setDouble(3, sumCarbs1);
            preparedStatement.setDouble(4, sumProtein1);
            preparedStatement.setDouble(5, sumCalories1);
            preparedStatement.setString(6, date);
            preparedStatement.setString(7, paymentMethod);
            preparedStatement.setString(8, bankName);
            preparedStatement.setString(9, accountName);
            preparedStatement.setString(10, accountNumber);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }}





    // RECEIPT PART



    Double totalAmount=0.0;
    //Double cash=0.0;
    //Double balance=0.0;

    Double bHeight=10.0;

    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();
    ArrayList<String> calorie = new ArrayList<>();

    // Formatting of the receipt (page width and height)
    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8.0);
        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0,0, width, height); //height- cm_to_pp(1)

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    // Converters
    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }


    // Main Receipt Function
    private void generateReceipt() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }




    public class BillPrintable implements Printable {
        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)
                throws PrinterException
        {

            // Restructure name, quantity, and price to ArrayList<String>
            for (int x = 0; x < i; x++) {
                itemName.add(String.valueOf(table.getColumns().get(1).getCellObservableValue(x).getValue()));
                itemPrice.add(String.valueOf(table.getColumns().get(5).getCellObservableValue(x).getValue()));
                quantity.add(String.valueOf(table.getColumns().get(0).getCellObservableValue(x).getValue()));
                subtotal.add( String.valueOf(Integer.valueOf(String.valueOf(table.getColumns().get(5).getCellObservableValue(x).getValue())) ) );
                calorie.add(String.valueOf(table.getColumns().get(4).getCellObservableValue(x).getValue()));
            }
            int r = i;


            ImageIcon icon=new ImageIcon("src\\sample\\logoMain.png");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());



                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                try{
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    DecimalFormat df = new DecimalFormat("0.00");
                    // int headerRectHeighta=40;


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawImage(icon.getImage(), 40, 20, 162, 54, null);y+=yShift+50;
                    g2d.drawString("------------------------------------------",12,y);y+=yShift;
                    g2d.drawString("                ADET Project              ",12,y);y+=yShift;
                    g2d.drawString("         No 00000 Address Line One     ",12,y);y+=yShift;
                    g2d.drawString("            Address Line 02 PUP     ",12,y);y+=yShift;
                    g2d.drawString("            Order Number:   " + ordernumber + " ",12,y);y+=yShift;
                    g2d.drawString("        www.facebook.com/******** ",12,y);y+=yShift;
                    g2d.drawString("             +9*********      ",12,y);y+=yShift;
                    g2d.drawString("------------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString(" Item Name                        Price",10,y);y+=yShift;
                    g2d.drawString("------------------------------------------",10,y);y+=headerRectHeight;

                    for(int s=0; s<r; s++)
                    {
                        g2d.drawString(" "+itemName.get(s)+"                            ",10,y);y+=yShift;
                        g2d.drawString("      "+quantity.get(s)+" * "+ df.format((Integer.valueOf(itemPrice.get(s))/Integer.valueOf(quantity.get(s)))),10,y);  g2d.drawString("P"+subtotal.get(s),195,y);y+=yShift;

                    }

                    g2d.drawString("------------------------------------------",10,y);y+=yShift;
                    g2d.drawString("T O T A L:             " + "           P" + df.format(Double.parseDouble(txtTotalBill.getText())) + " ",10,y);y+=yShift;
                    g2d.drawString("------------------------------------------",10,y);y+=yShift;
                    g2d.drawString("                                          ",10,y);y+=yShift;
                    g2d.drawString("          NUTRITIONAL INFORMATION         ",12,y);y+=yShift;
                    g2d.drawString("                                          ",10,y);y+=yShift;
                    g2d.drawString("Total Carbohydrates              " + df.format(sumCarbs1)+"g",10,y);y+=yShift;
                    g2d.drawString("Total Proteins                   " + df.format(sumProtein1)+"g",10,y);y+=yShift;
                    g2d.drawString("Total Calories                   " + df.format(sumCalories1)+"g",10,y);y+=yShift;
                    g2d.drawString("                                          ",10,y);y+=yShift;
                    g2d.drawString("------------------------------------------",10,y);y+=yShift;



                    //g2d.drawString(" Cash      :                 "+txtcash.getText()+"   ",10,y);y+=yShift;
                    //g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    //g2d.drawString(" Balance   :                 "+txtbalance.getText()+"   ",10,y);y+=yShift;

                    g2d.drawString("******************************************",10,y);y+=yShift;
                    g2d.drawString("          THANK YOU COME AGAIN           ",10,y);y+=yShift;
                    g2d.drawString("******************************************",10,y);y+=yShift;
                    g2d.drawString("                SOFTWARE BY:              ",10,y);y+=yShift;
                    g2d.drawString("           BALDEROSA, CYRA MARIE          ",10,y);y+=yShift;
                    g2d.drawString("              CERVANTES, VERN            ",10,y);y+=yShift;
                    g2d.drawString("           SAMPAGA, CLARISSA MAE          ",10,y);y+=yShift;
                    g2d.drawString("                         ",10,y);y+=yShift;


                }
                catch(Exception e){
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }









// RECEIPT

}