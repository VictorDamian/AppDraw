package com.example.dantes.GoDraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;

public class Lienzo extends View {


    //para pintar
    private Path drawPath;
    //pincel para pintar
    private static Paint drawPaint;//acceso
    private Paint canvasPaint;
    //color inicial
    private int paintColor = 0xFFF0000;
    //lienzo
    private Canvas drawCanvas;
    //Guardado-------------------------------------
    private Bitmap canvasBitmap;

    static float TamañoPunto;

    private boolean borrar=false;
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        //area de pintado, conf
        //parametros
        drawPath=new Path();//movimiento de dedo
        drawPaint=new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }
    //tamaño de la vista y objetos
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap=Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas=new Canvas(canvasBitmap);
    }
    @Override
    //pintar, llenado desde el evento tocar
    //construccion del lienzo
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(canvasBitmap, 0,0,canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }
    //Eventos
    public boolean onTouchEvent(MotionEvent event){
        float touchX=event.getX();
        float touchY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                //pintado
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //repintar
        invalidate();
        return true;
    }
    //colocar color
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }
    //pasar tamaño
    public static void setTamañoPunto(float nTamaño){
        drawPaint.setStrokeWidth(nTamaño);
    }
    //metodo para borrar
    public void setErase(Boolean borrador){
        borrar=borrador;
        //borrado de canvas

        if(borrar){
            drawPaint.setColor(Color.WHITE);
        }
        else{
            drawPaint.setColor(paintColor);
        }
    }

    public void nLienzo(){
        drawCanvas.drawColor(0,PorterDuff.Mode.CLEAR);
        invalidate();
    }
}