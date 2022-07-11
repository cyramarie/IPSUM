package sample;

import javax.swing.*;

public class Juices { //this class will have the data (nutritional value) of the chosen drink, also it will calculate the nutritional data and price according to chosen quantity and size

    String ingredients, drinkName;
    double calories, sumCalories=0;
    double carbs, sumCarbs=0;
    double fat;
    double protein, sumProtein=0;
    int s, m, l, price, sumPrice;
    String sizeName;
    double fiber;
    String size, drink;
    int Qty=0;


        public void finalOrder(String size){ //this method will calculate the total nutritional value and price according to quantity and size
            try{
                this.size=size;
        if(size.equalsIgnoreCase("Small --100mL")){
            sizeName="S--100mL";
            calories=calories*Qty;
            fat=fat*Qty;
            carbs=carbs*Qty;
            protein=protein*Qty;
            fiber=fiber*Qty;
            s=s*Qty;
            price=s;
        }
            else if(size.equalsIgnoreCase("Medium --200mL")){

            sizeName="M--200mL";
            calories=calories*2*Qty;
            fat=fat*2*Qty;
            carbs=carbs*2*Qty;
            protein=protein*2*Qty;
            fiber=fiber*2*Qty;
            m=m*Qty;
            price=m;
        }
        else if (size.equalsIgnoreCase("Large --300mL")){

            sizeName="L--300mL";
            calories=calories*3*Qty;
            fat=fat*3*Qty;
            carbs=carbs*3*Qty;
            protein=protein*3*Qty;
            fiber=fiber*3*Qty;
            l=l*Qty;
            price=l;
        }}
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

 //check what drink was chosen
        public void checkDrink(){

            try {

                if (drink.equalsIgnoreCase("drink1")) {
                    vibrantDelight();
                } else if (drink.equalsIgnoreCase("drink2")) {
                    perfectDetox();
                } else if (drink.equalsIgnoreCase("drink3")) {
                    vitMinTreats();
                } else if (drink.equalsIgnoreCase("drink4")) {
                    summerPotion();
                } else if (drink.equalsIgnoreCase("drink5")) {
                    stayInPink();
                } else if (drink.equalsIgnoreCase("drink6")) {
                    kalenPear();
                } else if (drink.equalsIgnoreCase("drink7")) {
                    tropicalMint();
                } else if (drink.equalsIgnoreCase("drink8")) {
                    ironBoost();
                } else if (drink.equalsIgnoreCase("drink9")) {
                    antiFlam();
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }



//Drinks nutritional value
    public void vibrantDelight(){
        ingredients="Carrots; Ginger";
        drinkName="Vibrant Delight";
        calories=91.0;
        fat=0.2;
        carbs=23.3;
        protein=3;
        fiber=2.5;
        s=120;
        m=200;
        l=280;
    }

    public void perfectDetox(){
        ingredients="Dill; Carrots; Lemon; Apple";
        drinkName="Perfect Detox";
        calories=70;
        fat=0.4;
        carbs=16.3;
        protein=1.0;
        fiber=4.1;
        s=100;
        m=180;
        l=260;
    }

    public void vitMinTreats(){
        ingredients="Apple; Beetroot; Carrots";
        drinkName="Vit & Min Treats";
        calories=126.2;
        fat=0.8;
        carbs=31.3;
        protein=1.4;
        fiber=7.0;
        s=130;
        m=210;
        l=290;
    }

    public void summerPotion(){
        ingredients="Cucumber; Apple; Ginger; ";
        drinkName="Summer Potion";
        calories=64;
        fat=0.5;
        carbs=14.1;
        protein=0.6;
        fiber=5.2;
        s=120;
        m=200;
        l=280;
    }

    public void stayInPink(){
        ingredients="Ginger; Beetroot; Pineapple; Pear";
        drinkName="Stay In Pink";
        calories=140.7;
        fat=0;
        carbs=34.0;
        protein=1;
        fiber=0.1;
        s=120;
        m=200;
        l=280;
    }

    public void kalenPear(){
        ingredients="Kale; Spinach; Pear; Lime; Celery; Cucumber";
        drinkName="Kalen Pear";
        calories=90;
        fat=0;
        carbs=16;
        protein=1.3;
        fiber=0.8;
        s=100;
        m=180;
        l=260;
    }

    public void tropicalMint(){
        ingredients="Lemon; Spinach; Pineapple; Mint; Celery; Cucumber";
        drinkName="Tropical Mint";
        calories=128;
        fat=0.7;
        carbs=22.2;
        protein=0.6;
        fiber=1.9;
        s=130;
        m=210;
        l=290;
    }

    public void ironBoost(){
        ingredients="Lime; Apple; Broccoli; Romaine Lettuce; Celery; Cucumber";
        drinkName="Iron Boost";
        calories=89;
        fat=0;
        carbs=15.3;
        protein=0.4;
        fiber=3.1;
        s=100;
        m=180;
        l=260;
    }

    public void antiFlam(){
        ingredients="Turmeric; Carrots; Ginger; Orange; Lemon; Celery";
        drinkName="Anti-Flam Tonic";
        calories=97.8;
        fat=0;
        carbs=25.3;
        protein=1.9;
        fiber=2.3;
        s=130;
        m=210;
        l=290;
    }
}
