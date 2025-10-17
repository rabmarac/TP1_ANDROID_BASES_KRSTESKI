package UPsay.decouverteAndroid;

import androidx.annotation.Nullable;
import android.annotation.SuppressLint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

public class ToGraphic extends View {
    public float xText, yText, textSize;
    boolean is_on;

    Handler timerHandler = new Handler();
    Runnable updateTimerThread = new Runnable() {
        public void run(){
            textSize += 2f;
            timerHandler.postDelayed(this, 100);
            invalidate();
        }
    };

    /*
    OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            float dx, dy;
            float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            String direction;
            switch(event.getAction()) {

                case(MotionEvent.ACTION_DOWN):
                    x1 = event.getX();
                    y1 = event.getY();
                    Log.i("pacman", "appuyé");
                    break;
                case(MotionEvent.ACTION_UP): {
                    x2 = event.getX();
                    y2 = event.getY();
                    dx = x2-x1;
                    dy = y2-y1;
                    // Use dx and dy to determine the direction of the move
                    if(Math.abs(dx) > Math.abs(dy)) {
                        if(dx>0)
                            direction = "right";
                        else
                            direction = "left";
                    } else {
                        if(dy>0)
                            direction = "down";
                        else
                            direction = "up";
                    }
                    Log.i("pacman", "laché " + direction);
                    Log.i("pacman", "dx = " + dx +"; dy = " + dy);
                    break;
                }
            }
            invalidate();
            return true;
        }
    };
    */

    /* Constructeur de la classe ToGraphic */
    public ToGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timerHandler.postDelayed(updateTimerThread, 10);
        // setOnTouchListener(onTouchListener);
    }

    // Overridable functions
    @Override public void onDraw (Canvas canvas) {
        @SuppressLint("DrawAllocation") Paint p = new Paint();

        if (textSize > 100 ) textSize = 100;
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(), p);
        p.setColor(Color.GREEN);
        p.setTextSize(textSize);
        p.setTextAlign(Paint.Align.CENTER);
        String texte = "Bonjour MONDE";

        /* On créé le bitmap de l'image associer au jpg et on l'affiche avec drawBitmap */
        @SuppressLint("DrawAllocation") Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

        /* Permet de créer une boite en bas de l'application qui va se fermer automatiquement
        (j'ai ajouté un flag pour éviter de l'avoir à chaque fois que l'on clique sur la page) */
        if (!is_on) { Toast.makeText(getContext(), "Bienvenue sur mon application :)", Toast.LENGTH_SHORT).show(); is_on = true; }

        canvas.drawBitmap(b, 0, 0, p);
        canvas.drawText(texte, (float) getWidth() /2, (float) getHeight() /2, p);
        canvas.drawText(texte, xText, yText, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        xText = event.getX();
        yText = event.getY();
        invalidate();
        return false;
    }

    //Custom fonction
    public void SetXYText(float x, float y) { xText = x; yText = y; }
}