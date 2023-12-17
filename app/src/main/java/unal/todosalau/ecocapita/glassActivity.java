package unal.todosalau.ecocapita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import unal.todosalau.ecocapita.models.glass;

public class glassActivity extends AppCompatActivity {
    ImageView salir;
    EditText quantity, price;
    Spinner months;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidrio);

        salir = findViewById(R.id.imagSalirMvidrio);
        quantity= findViewById(R.id.costoVidrio);
        price = findViewById(R.id.pesodVidrio);
        months = findViewById(R.id.spinnerMonthVidrio);
        register = findViewById(R.id.btnRe3);

        Intent receive = getIntent();
        String idUser = receive.getStringExtra("idUser");


        salir = findViewById(R.id.imagSalirMvidrio);

        Intent exit = new Intent(getApplicationContext(), Materiales.class);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(exit);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (price.getText().toString().isEmpty() ||
                        quantity.getText().toString().isEmpty() ||
                        months.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Todos los Campos deben diligenciarse", Toast.LENGTH_LONG).show();
                } else {
                    int quantityVidrio = Integer.parseInt(quantity.getText().toString());
                    int priceVidrio = Integer.parseInt(price.getText().toString());
                    String monthglass = months.getSelectedItem().toString();
                    String serial = idUser + monthglass;
                    glass materialglass = new glass(serial, priceVidrio, quantityVidrio, monthglass, idUser);
                    registerglass(materialglass);
                    Toast.makeText(getApplicationContext(), "Registro exitoso",
                            Toast.LENGTH_LONG).show();
                    cleanView();

                }

            }
        });

    }

    public void registerglass(glass material) {
        File glassFile = new File(getFilesDir(), "glass.txt");

        try {
            FileWriter writer = new FileWriter(glassFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(
                    material.getSERIAL() + "," +
                            material.getQuantity() + "," +
                            material.getPrice() + "," +
                            material.getMonth() + "," +
                            material.getIdUser()


            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cleanView(){
        quantity.setText("");
        price.setText("");
        months.setSelection(0);
    }




}









