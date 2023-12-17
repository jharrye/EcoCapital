package unal.todosalau.ecocapita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Recomendaciones extends AppCompatActivity {
    ImageView salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        setContentView(R.layout.activity_recomendaciones);

        salir = findViewById(R.id.imageViewExitrecom);

        Intent exit=new Intent(getApplicationContext(),Home.class);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(exit);
            }
        });
    }
}