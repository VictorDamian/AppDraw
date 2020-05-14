package com.example.dantes.GoDraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.UUID;
import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton negro;
    ImageButton morado;
    ImageButton rosa;
    ImageButton azulF;
    ImageButton gris;
    ImageButton zul;
    private Lienzo lienzo;
    //pinceles
    float ppequeño;
    float psmediano;
    float pgrande;
    float pdefecto;
    ImageButton lapiz;
    ImageButton añadir;
    ImageButton Guardar;
    ImageButton Borrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlace
        negro=(ImageButton)findViewById(R.id.negro);
        azulF=(ImageButton)findViewById(R.id.azulFu);
        morado=(ImageButton)findViewById(R.id.morado);
        rosa=(ImageButton)findViewById(R.id.rosa);
        gris=(ImageButton)findViewById(R.id.gris);
        zul=(ImageButton)findViewById(R.id.azzul);

        lapiz=(ImageButton)findViewById(R.id.draw_btn);
        añadir=(ImageButton)findViewById(R.id.new_btn);
        Guardar=(ImageButton)findViewById(R.id.save_btn);
        Borrar=(ImageButton)findViewById(R.id.erase_btn);


        negro.setOnClickListener(this);
        azulF.setOnClickListener(this);
        morado.setOnClickListener(this);
        rosa.setOnClickListener(this);
        gris.setOnClickListener(this);
        zul.setOnClickListener(this);

        lapiz.setOnClickListener(this);
        añadir.setOnClickListener(this);
        Guardar.setOnClickListener(this);
        Borrar.setOnClickListener(this);


        //enlace
        lienzo=(Lienzo)findViewById(R.id.lienzo);
        ppequeño=5;
        psmediano=10;
        pgrande=15;
        pdefecto=ppequeño;

    }
    //evento boton
    @Override
    public void onClick(View v) {
        String color=null;
        switch (v.getId()){
            case R.id.negro:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;
            case R.id.morado:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;
            case R.id.azulFu:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;
            case R.id.azzul:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;
            case R.id.gris:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;
            case R.id.rosa:
                color=v.getTag().toString();//tag
                lienzo.setColor(color);//pasar color
                break;

            case R.id.new_btn:
                AlertDialog.Builder nDialogo=new AlertDialog.Builder(this);
                nDialogo.setTitle("Nuevo dibujo");
                nDialogo.setMessage("¿Desea comenzar un nuevo dibujo? (se perdera el dibujo actual)");
                nDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lienzo.nLienzo();
                        dialog.dismiss();
                    }
                });
                nDialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                nDialogo.show();
                break;
            case R.id.draw_btn:
                //mensaje
                final Dialog tamañopin=new Dialog(this);
                tamañopin.setTitle("tamaño del pincel:");
                //enlace con tamaño pincel
                tamañopin.setContentView(R.layout.brush_chooser);
                //Listener para tamaño de los Btn
                tamañopin.show();//dialogo
                ImageButton smallBtn = (ImageButton)tamañopin.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(false);
                        lienzo.setTamañoPunto(ppequeño);
                        //lienzo.setBrushSize(smallBrush);
                        tamañopin.dismiss();//cerrar
                    }
                });

                ImageButton mediumBtn = (ImageButton)tamañopin.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(false);
                        lienzo.setTamañoPunto(psmediano);
                        //lienzo.setBrushSize(smallBrush);
                        tamañopin.dismiss();
                    }
                });

                ImageButton largeBtn = (ImageButton)tamañopin.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(false);
                        lienzo.setTamañoPunto(pgrande);
                        //lienzo.setBrushSize(smallBrush);
                        tamañopin.dismiss();
                    }
                });
                break;
            case R.id.erase_btn:

                final Dialog tamañoborr=new Dialog(this);
                tamañoborr.setTitle("tamaño del Borrador:");
                //enlace con tamaño pincel
                tamañoborr.setContentView(R.layout.brush_chooser);
                //Listener para tamaño de los Btn
                tamañoborr.show();//dialogo
                ImageButton smallBtnBorr = (ImageButton)tamañoborr.findViewById(R.id.small_brush);
                smallBtnBorr.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(true);
                        lienzo.setTamañoPunto(ppequeño);
                        //lienzo.setBrushSize(smallBrush);
                        tamañoborr.dismiss();//cerrar
                    }
                });

                ImageButton mediumBtnBorr = (ImageButton)tamañoborr.findViewById(R.id.medium_brush);
                mediumBtnBorr.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(true);
                        lienzo.setTamañoPunto(psmediano);
                        //lienzo.setBrushSize(smallBrush);
                        tamañoborr.dismiss();
                    }
                });

                ImageButton largeBtnBorr = (ImageButton)tamañoborr.findViewById(R.id.large_brush);
                largeBtnBorr.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setErase(true);
                        lienzo.setTamañoPunto(pgrande);
                        //lienzo.setBrushSize(smallBrush);
                        tamañoborr.dismiss();
                    }
                });
                break;
            case R.id.save_btn:
                //save drawing
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //save drawing
                        lienzo.setDrawingCacheEnabled(true);
                        //attempt to save
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        //feedback
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        lienzo.destroyDrawingCache();
                    }
                });
                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                saveDialog.show();

        }
    }
}