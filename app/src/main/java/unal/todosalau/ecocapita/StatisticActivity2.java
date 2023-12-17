package unal.todosalau.ecocapita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import unal.todosalau.ecocapita.models.cardboard;
import unal.todosalau.ecocapita.models.copper;
import unal.todosalau.ecocapita.models.glass;
import unal.todosalau.ecocapita.models.plastic;

public class StatisticActivity2 extends AppCompatActivity {
    TextView total_cardboard, total_copper, total_glass, total_Plastic, total_pay, max_carton_month,
            max_copper_month,max_glass_month,max_plastic_month, max_carton_quantity,
            max_copper_quantity,max_glass_quantity,max_plastic_quantity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic2);

        total_cardboard = findViewById(R.id.textViewTotalCarton);
        total_Plastic = findViewById(R.id.textViewTotalPlastic);
        total_copper = findViewById(R.id.textViewTotalCobre);
        total_glass = findViewById(R.id.textViewTotalV);
        total_pay = findViewById(R.id.textViewTotalPay);
        max_carton_month = findViewById(R.id.textViewMonthMaxCarton);
        max_copper_month = findViewById(R.id.textViewMonthMaxCobre);
        max_glass_month = findViewById(R.id.textViewMonthMaxVidrio);
        max_carton_quantity = findViewById(R.id.textViewMaxCartonQuantity);
        max_copper_quantity = findViewById(R.id.textViewMaxCobreQuantity);
        max_glass_quantity = findViewById(R.id.textViewMaxVidrioQuantity);
        max_plastic_month = findViewById(R.id.textViewMonthMaxPlastico);
        max_plastic_quantity = findViewById(R.id.textViewMaxPlasticQuantity);

        Intent receive = getIntent();
        String idUser = receive.getStringExtra("idUser");

        File cardboard = new File(getFilesDir(), "cardboard.txt");
        File copper = new File(getFilesDir(), "copper.txt");
        File glass = new File(getFilesDir(), "glass.txt");
        File plastic = new File(getFilesDir(), "plastic.txt");

        ArrayList<cardboard> list_cardboard = listcardboard(cardboard, idUser);
        ArrayList<copper> list_copper = listcopper(copper, idUser);
        ArrayList<glass> list_glass = listglass(glass, idUser);
        ArrayList<plastic> list_plastic = listplastic(plastic, idUser);

        total_consume_cardboard(list_cardboard);
        total_consume_copper(list_copper);
        total_consume_glass(list_glass);
        total_consume_plastic(list_plastic);

        int total = totalPaycardboard(list_cardboard) + totalPayplastic(list_plastic)
                + totalPaycopper(list_copper) + totalPayglass(list_glass);
        total_pay.setText("$ " + total);

    }

    public int totalPaycardboard(ArrayList<cardboard> list) {
        int pay = 0;
        for (cardboard i : list) {
            pay += i.getPrice();
        }
        return pay;
    }

    public int totalPayplastic(ArrayList<plastic> list) {
        int pay = 0;
        for (plastic i : list) {
            pay += i.getPrice();
        }
        return pay;
    }
    public int totalPaycopper(ArrayList<copper> list) {
        int pay = 0;
        for (copper i : list) {
            pay += i.getPrice();
        }
        return pay;
    }
    public int totalPayglass(ArrayList<glass> list) {
        int pay = 0;
        for (glass i : list) {
            pay += i.getPrice();
        }
        return pay;
    }

    public void total_consume_cardboard(ArrayList<cardboard> list) {
        int total = 0;
        String month = "";
        int max = 0;
        for (cardboard i : list) {
            total += i.getQuantity();
            if (max < i.getQuantity()) {
                max = i.getQuantity();
                month = i.getMonth();
            }
        }
        total_cardboard.setText(total + " L");
        max_carton_quantity.setText(max + " L");
        max_carton_month.setText(month);
    }

    public void total_consume_plastic(ArrayList<plastic> list) {
        int total = 0;
        String month = "";
        int max = 0;
        for (plastic i : list) {
            total += i.getQuantity();
            if (max < i.getQuantity()) {
                max = i.getQuantity();
                month = i.getMonth();
            }
        }
        total_Plastic.setText(total + " KWh");
        max_plastic_quantity.setText(max + " KWh");
        max_plastic_month.setText(month);
    }
    public void total_consume_copper(ArrayList<copper> list) {
        int total = 0;
        String month = "";
        int max = 0;
        for (copper i : list) {
            total += i.getQuantity();
            if (max < i.getQuantity()) {
                max = i.getQuantity();
                month = i.getMonth();
            }
        }
        total_copper.setText(total + " L");
        max_copper_quantity.setText(max + " L");
        max_copper_month.setText(month);
    }
    public void total_consume_glass(ArrayList<glass> list) {
        int total = 0;
        String month = "";
        int max = 0;
        for (glass i : list) {
            total += i.getQuantity();
            if (max < i.getQuantity()) {
                max = i.getQuantity();
                month = i.getMonth();
            }
        }
        total_glass.setText(total + " L");
        max_glass_quantity.setText(max + " L");
        max_glass_month.setText(month);
    }


    public ArrayList<cardboard> listcardboard(File cardboard, String user) {
        ArrayList<cardboard> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(cardboard);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String cadena;
            while ((cadena = bufferedReader.readLine()) != null) {
                String[] data = cadena.split(",");
                if (data[4].equals(user)) {
                    String serial = data[0];
                    int quantity = Integer.parseInt(data[1]);
                    int price = Integer.parseInt(data[2]);
                    String month = data[3];
                    String idUser = data[4];

                    cardboard obj = new cardboard(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<plastic> listplastic(File plastic, String user) {
        ArrayList<plastic> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(plastic);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String cadena;
            while ((cadena = bufferedReader.readLine()) != null) {
                String[] data = cadena.split(",");
                if (data[4].equals(user)) {
                    String serial = data[0];
                    int quantity = Integer.parseInt(data[1]);
                    int price = Integer.parseInt(data[2]);
                    String month = data[3];
                    String idUser = data[4];

                    plastic obj = new plastic(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<copper> listcopper(File copper, String user) {
        ArrayList<copper> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(copper);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String cadena;
            while ((cadena = bufferedReader.readLine()) != null) {
                String[] data = cadena.split(",");
                if (data[4].equals(user)) {
                    String serial = data[0];
                    int quantity = Integer.parseInt(data[1]);
                    int price = Integer.parseInt(data[2]);
                    String month = data[3];
                    String idUser = data[4];

                    copper obj = new copper(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<glass> listglass(File glass, String user) {
        ArrayList<glass> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(glass);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String cadena;
            while ((cadena = bufferedReader.readLine()) != null) {
                String[] data = cadena.split(",");
                if (data[4].equals(user)) {
                    String serial = data[0];
                    int quantity = Integer.parseInt(data[1]);
                    int price = Integer.parseInt(data[2]);
                    String month = data[3];
                    String idUser = data[4];

                    glass obj = new glass(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}



