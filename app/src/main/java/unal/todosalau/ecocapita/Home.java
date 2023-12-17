package unal.todosalau.ecocapita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView exit, statistics, recomen, regism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        exit = findViewById(R.id.imagSalir);
        regism= findViewById(R.id.imagRM);
        statistics= findViewById(R.id.imagEsta);
        recomen= findViewById(R.id.imagReco);


        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        Intent salir=new Intent(getApplicationContext(), MainActivity.class);

        Intent statistics_view=new Intent(getApplicationContext(), StatisticActivity2.class);
        statistics_view.putExtra("idUser",idUser);

        Intent recomendacion= new Intent(getApplicationContext(), Recomendaciones.class);

        Intent registerView=new Intent(getApplicationContext(), Materiales.class);
        registerView.putExtra("idUser",idUser);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(salir);
            }
        });
        regism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerView);
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(statistics_view);
            }
        });

        recomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recomendacion);
            }
        });



    }
    }
