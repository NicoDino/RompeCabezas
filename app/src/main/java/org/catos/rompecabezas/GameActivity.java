package org.catos.rompecabezas;

/**
 * Created by ecko on 08/09/16.
 */

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends Activity {
    MediaPlayer musica;
    MediaPlayer win;
    Switch sound;

    private TextView textMov;
    private Button vacio;

    private int[] tablero = new int[16]; //Estructura de control
    private int contMov =0; //Contador de contMov

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.acticity_game);
        this.sound = (Switch) findViewById(R.id.switch3);
        this.sound.setChecked(true);
        this.sound.setShowText(true);
        this.musica= MediaPlayer.create(GameActivity.this, R.raw.happy3friends);
        this.musica.setLooping(true);
        this.musica.start();
        //Localizar los controles
        TextView nombre = (TextView) findViewById(R.id.nombre);
        this.textMov = (TextView) findViewById(R.id.cont);
        this.vacio = (Button) findViewById(R.id.b16);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String namePlayer = bundle.getString("NOMBRE");

        //Construimos el mensaje a1 mostrar
        nombre.setText("Jugador: " + namePlayer);
        this.textMov.setText("Movimientos: " + this.contMov);

        //Construimos el tablero
        this.armarTablero();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void actionSwitch(View v){
        if(this.sound.getShowText()) {
            this.musica.stop();
            this.musica.release();
            this.sound.setShowText(false);
        }
        else {
            this.musica= MediaPlayer.create(GameActivity.this, R.raw.happy3friends);
            this.musica.setLooping(true);
            this.musica.start();
            this.sound.setShowText(true);
        }
    }

    private boolean verificarMovimiento(int movido,int vacio){
        boolean valido=false;
        if( (movido == vacio + 4 ) ){
            if((vacio+4)< 16){
                valido=true;
            }
        }
        if( (movido == vacio - 4 ) ){
            if((vacio-4)>-1){
                valido=true;
            }
        }
        if((movido == vacio -1)){
            if((vacio-1)!=3 && (vacio-1)!=7 && (vacio-1)!=11 && (vacio-1)!=-1){
                valido=true;
            }
        }
        if((movido == vacio +1)){
            if((vacio+1)!=12 && (vacio+1)!=8 && (vacio+1)!=4 && (vacio+1)!=16){
                valido=true;
            }
        }
        return valido;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void mover(View v) {
        int auxT, auxV, aux;

        //Obtenemos las pocisiones del tablero del boton qe se presionó (auxT) y el boton vacio (auxV)
        auxT = Integer.parseInt(((Button) v).getText().toString())-1;
        auxV = Integer.parseInt(vacio.getText().toString())-1;

        if(verificarMovimiento(auxT,auxV)) { //Se debe controlar que el movimiento esta permitido(Botonoes vecinos)
            //Proyectamos el movimiento en el arreglo de control (tablero)
            aux = this.tablero[auxT];
            this.tablero[auxT] = this.tablero[auxV];
            this.tablero[auxV] = aux;

            //Proyectamos el movimiento en la vista seteando los backgrounds de los botones.
            vacio.setBackground(v.getBackground());
            v.setBackgroundColor(Color.GRAY);
            vacio = (Button) v;

            //Aumento en 1 el contador de contMov.
            this.contMov++;
            this.textMov.setText("Movimientos: " + this.contMov);

            /*            //Print para debug (BORRAR)
            System.out.println("TEXT" + ((Button) v).getText());
            for (int i = 0; i < 16; i++) {
                System.out.print(this.tablero[i] + "-");
            }
            System.out.println("----------------------------------------");
            System.out.println(this.estaOrdenado());
            System.out.println("----------------------------------------");*/

            if (this.estaOrdenado()) {
                this.musica.stop();
                this.musica.release();
                this.win = MediaPlayer.create(GameActivity.this, R.raw.win);
                this.win.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("---  GG izi  ---")
                        .setTitle("You WIN!!")
                        .setCancelable(false)
                        .setNeutralButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel(); //En vez de cancelar debería pasar a la vista de Rankings
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    //devuelve true si la estructura de control esta ordenada
    private boolean estaOrdenado() {
        int leng = this.tablero.length;
        boolean ordenado = true;
        for (int i = 0; i < (leng - 1) && ordenado; i++) {
            if (this.tablero[i] > this.tablero[i + 1]) {
                ordenado = false;
            }
        }
        return ordenado;
    }

    /*El siguiente método llena de manera aleatoria la estructura de control con los valores
      correspondientes y asigna los backgrounds a los botones del view*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void armarTablero() {
        ArrayList list = new ArrayList(15);
        int i, r = 0, aux = 0, id, idI;
        //lleno la lista con 15 enteros
        for (i = 1; i < 16; i++) {
            list.add(i);
        }
        /*Leno el tablero con una pocision aleatoria dela lista que anterior (list).
         Además asigno un fragmento de imagen a cada boton en el mismo recorrido
         exceptuando el boton 16*/
        Random rdm = new Random();
        for (i = 0; i < 15; i++) {
            r = rdm.nextInt(15 - i);
            aux = (int) list.get(r);
            list.remove(r);
            this.tablero[i] = aux;
            /*obtengo el id del i-esimo boton y seteo su background con el fragmento
            de imagen que corresponda*/
            id = this.getResources().getIdentifier("b" + (i + 1), "id", this.getPackageName());
            idI = this.getResources().getIdentifier("a" + aux, "mipmap", this.getPackageName());
            Button b = (Button) findViewById(id);
            b.setBackground(this.getResources().getDrawable(idI, null));
        }
        //El ultimo boton es el vacío, x lo tanto no tiene fragmento de imagen
        this.tablero[15]=16;
        vacio.setBackgroundColor(Color.GRAY);
    }
}