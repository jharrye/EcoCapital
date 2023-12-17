package unal.todosalau.ecocapita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import unal.todosalau.ecocapita.models.glass;

public class Materiales extends AppCompatActivity {
    ImageView salirm;
    Button carton, cobre, vidrio, plastico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiales);

        salirm = findViewById(R.id.imagSalirM);
        carton = findViewById(R.id.btnC);
        vidrio = findViewById(R.id.btnV);
        plastico = findViewById(R.id.btnP);
        cobre = findViewById(R.id.btnCo);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        Intent exit= new Intent(getApplicationContext(), Home.class);

        Intent registercardboard= new Intent(getApplicationContext(), Carton.class);
        registercardboard.putExtra("idUser",idUser);

        Intent Vid= new Intent(getApplicationContext(), glassActivity.class);
        Vid.putExtra("idUser",idUser)
        ;
        Intent registerplastic= new Intent(getApplicationContext(), PlasticActivity.class);
        registerplastic.putExtra("idUser",idUser);

        Intent Cobr= new Intent(getApplicationContext(), Cobre.class);
        Cobr.putExtra("idUser",idUser);

        salirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(exit);
            }
        });
        carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registercardboard);
            }
        });

        vidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Vid);
            }
        });

        plastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerplastic);
            }
        });

        cobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Cobr);

            }
        });


    }
}