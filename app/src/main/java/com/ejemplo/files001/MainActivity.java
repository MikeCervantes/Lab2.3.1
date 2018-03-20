package com.ejemplo.files001;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    Button botonSave,botonLoad;
    static final int READ_BLOCK_SIZE = 100;
    boolean sdDisponible=false;
    boolean sdEscritura=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.editText);
        botonSave=(Button) findViewById(R.id.btnSave);
        botonLoad=(Button) findViewById(R.id.btnLoad);
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible=true;
            sdEscritura=true;
        }else if(estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible=true;
            sdEscritura=false;
        }else{
            sdDisponible=false;
            sdEscritura=false;
        }

        botonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textBox.getText().toString();

                if(sdDisponible&&sdEscritura){
                    try{
                        File ruta=Environment.getExternalStorageDirectory();
                        File f=new File(ruta.getAbsolutePath(),"fichero.txt");
                        OutputStreamWriter fout=new OutputStreamWriter(new FileOutputStream(f));
                        fout.write(str);
                        fout.close();
                        Toast.makeText(MainActivity.this,"Guardado Exitoso", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Log.e("Ficheros","Error al guardar texto en la tarjeta SD");
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Tarjeta SD no disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdDisponible){
                    try{
                        File ruta=Environment.getExternalStorageDirectory();
                        File f=new File(ruta.getAbsolutePath(),"fichero.txt");
                        BufferedReader fin=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                        String texto=fin.readLine();
                        textBox.setText(texto);
                        fin.close();
                    }catch(Exception e){
                        Log.e("Ficheros","Error al leer fichero desde la tarjeta SD");
                    }
                }
            }
        });
    }

    /*public void onClickSave(View view) {
        /*String str = textBox.getText().toString();

        if(sdDisponible&&sdEscritura){
            try{
                File ruta=Environment.getExternalStorageDirectory();
                File f=new File(ruta.getAbsolutePath(),"fichero.txt");
                OutputStreamWriter fout=new OutputStreamWriter(new FileOutputStream(f));
                fout.write(str);
                fout.close();
                Toast.makeText(MainActivity.this,"Guardado Exitoso", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.e("Ficheros","Error al guardar texto en la tarjeta SD");
            }
        }else{
            Toast.makeText(MainActivity.this,"Tarjeta SD no disponible", Toast.LENGTH_SHORT).show();
        }
        try {
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            //---write the string to the file---
            try {
                osw.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            osw.flush();
            osw.close();
            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
            textBox.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void onClickLoad(View view) {
        if(sdDisponible){
            try{
                File ruta=Environment.getExternalStorageDirectory();
                File f=new File(ruta.getAbsolutePath(),"fichero.txt");
                BufferedReader fin=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String texto=fin.readLine();
                textBox.setText(texto);
                fin.close();
            }catch(Exception e){
                Log.e("Ficheros","Error al leer fichero desde la tarjeta SD");
            }
        }
        try {
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            textBox.setText(s);
            Toast.makeText(getBaseContext(), "File loaded successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/
}


