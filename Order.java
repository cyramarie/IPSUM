package sample;

public class Order { //this class is for the TABLE
    private int Qty;
    private String drinkName;
    private int price;
    private double carbs;
    private double protein;
    private double calories;


    public Order(int quantity, String name, int price, double carbs, double protein, double calories ){
        this.Qty=quantity;
        this.drinkName=name;
        this.price=price;
        this.carbs=carbs;
        this.protein=protein;
        this.calories=calories;
    }

    public String getDrinkName(){
        return drinkName;
    }

    public int getQty(){
        return  Qty;
    }
    public int getPrice(){
        return price;
    }
    public double getCarbs(){
        return carbs;
    }

    public double getProtein(){
        return protein;
    }

    public double getCalories(){
        return calories;
    }
}
