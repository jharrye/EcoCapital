package unal.todosalau.ecocapita;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import unal.todosalau.ecocapita.models.User;

public class Register extends AppCompatActivity {
    EditText name,email,phone,password1,password2;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name= findViewById(R.id.TvNombreC);
        email=findViewById(R.id.edt1email);
        phone=findViewById(R.id.phone_User);
        password1= findViewById(R.id.Password_User1);
        password2=findViewById(R.id.editTextTextPassword2);
        register= findViewById(R.id.btnregistro);

        Intent registro= new Intent(getApplicationContext(), MainActivity.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateUser()){
                    User user=CreateUser();
                    saveUser(user);
                    Toast.makeText(getApplicationContext(),
                            "Registro Exitoso",Toast.LENGTH_LONG).show();
                    try {
                        sleep(500);
                        startActivity(registro);
                        finish();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Todos los campos deben estar diligenciados",Toast.LENGTH_LONG).show();
                }
            }

        });
    }
    public boolean validateUser(){
        boolean validate= true;

        if (name.getText().toString().isEmpty()){
            name.setBackgroundColor(Color.RED);
            validate=false;
        }
        if (email.getText().toString().isEmpty()){
            email.setBackgroundColor(Color.RED);
            validate=false;
        }
        if (phone.getText().toString().isEmpty()){
            phone.setBackgroundColor(Color.RED);
            validate=false;
        }
        password1.setBackgroundColor(Color.RED);
        if (password1.getText().toString().isEmpty()){
            validate=false;
        }
        if (password2.getText().toString().isEmpty()){
            password2.setBackgroundColor(Color.RED);
            validate=false;
        }
        if (!password1.getText().toString().equals(password2.getText().toString())){
            password1.setBackgroundColor(Color.RED);
            password2.setBackgroundColor(Color.RED);
            validate=false;
        }
        return validate;
    }

    public User CreateUser(){
        String id,nameUser,emailUser,phoneUser,password;

        nameUser= name. getText().toString();
        id=generateID(nameUser);
        emailUser=email.getText().toString();
        phoneUser=phone.getText().toString();
        password=password1.getText().toString();
        User user= new User(id,nameUser,emailUser,phoneUser,password);
        return user;
    }

    public String generateID(String name){
        String id="";
        for (int i=0;i<2;i++){
            int letra= (int) (Math.random()*name.length());
            int number= (int)(Math.random()*1000);
            id+=name.charAt(letra);
            id+=number;
        }
        return id;
    }
    public void saveUser(User user){
        File fileUser= new File(getFilesDir(),"user.txt");
        try {
            FileWriter writer=new FileWriter(fileUser,true);
            BufferedWriter bufferedWriter= new BufferedWriter(writer);
            bufferedWriter.write(
                    user.getID()+","+
                            user.getName()+","+
                            user.getEmail()+","+
                            user.getPhone()+","+
                            user.getPassword()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

}
