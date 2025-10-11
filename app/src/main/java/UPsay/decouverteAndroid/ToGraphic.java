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

    /* Constructeur de la classe ToGraphic */
    public ToGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timerHandler.postDelayed(updateTimerThread, 10);
    }

    // Overridable functions
    @Override public void onDraw (Canvas canvas) {
        @SuppressLint("DrawAllocation") Paint p = new Paint();

        // Je met une limite à textSize pour éviter qu'il prenne tout l'écran
        if (textSize > 100) textSize = 100;
        p.setColor(Color.BLACK);
        p.setStyle(android.graphics.Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(), p);
        p.setColor(Color.GREEN);
        p.setTextSize(textSize);
        p.setTextAlign(android.graphics.Paint.Align.CENTER);
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
