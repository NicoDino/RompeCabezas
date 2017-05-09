package org.catos.rompecabezas;

/**
 * Created by ecko on 08/09/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    private TextView nombre;
    private Button vacio;
    private int[] tablero = new int[16];
    private GridLayout l;

    protected void onCreate(Bundle saveInstanceState) {
        l = (GridLayout) findViewById(R.id.grid);
        super.onCreate(saveInstanceState);
        setContentView(R.layout.acticity_game);
        //Localizar los controles
        this.nombre = (TextView) findViewById(R.id.nombre);
        this.vacio = (Button) findViewById(R.id.b16);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String n = bundle.getString("NOMBRE");

        //Construimos el mensaje a1 mostrar
        this.nombre.setText("Player: " + n);

        //Construimos el tablero
        this.armarTablero();
        for (int c = 0; c < 16; c++) {
            System.out.print(this.tablero[c] + "-");
        }
        System.out.println("");
        /*Mientras no se arme el tablero el juego continúa
        while(!armado){
            Defino el setOnClickListener para cada uno de los botones o puedo
            Definir uno mas general? -> Ver el xml!!
        }*/

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void mover(View v) {
        int auxV, auxB, i;
        auxV = Integer.parseInt(((Button) v).getText().toString());
        auxB = Integer.parseInt(((Button) vacio).getText().toString());
        vacio.setBackground(v.getBackground());
        v.setBackgroundColor(Color.GRAY);
        vacio = (Button) v;
        boolean entrar=true;
        System.out.println("TEXT" + ((Button) v).getText());

        for (i = 0; i < 16; i++) {
            if (this.tablero[i] == auxB) {
                System.out.println("Movimiento :" + this.tablero[i] + "por" + auxV);
                this.tablero[i] = auxV;
                entrar= false;
            }
            if (this.tablero[i] == auxV && entrar) {
                System.out.println("Movimiento :" + this.tablero[i] + "por" + auxB);
                this.tablero[i] = auxB;
            }
            entrar= true;
        }
        for (i = 0; i < 16; i++) {
            System.out.print(this.tablero[i] + "-");
        }
        //Falta mover los elementos del tablero antes de verificar si estaOrdenado
        System.out.println("----------------------------------------");
        System.out.println(this.estaOrdenado());
        System.out.println("----------------------------------------");
        if (this.estaOrdenado()) {
            //Mostrar mensaje WIN. En nueva actividad o como sea.
        }
    }

    public boolean estaOrdenado() {
        int leng = this.tablero.length;
        boolean ordenado = true;
        for (int i = 0; i < (leng - 1) && ordenado; i++) {
            if (this.tablero[i] > this.tablero[i + 1]) {
                ordenado = false;
            }
        }
        return ordenado;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void armarTablero() {
        ArrayList list = new ArrayList(15);
        int i, r = 0, aux = 0;
        //lleno la lista con 15 enteros
        for (i = 1; i < 16; i++) {
            list.add(i);
        }
        /**Leno el tablero con una pocision aleatoria dela lista que armé.
         Además asigno un fragmento de imagen a cada boton en el mismo recorrido**/
        Random rdm = new Random();
        for (i = 0; i < 16; i++) {
            if (i != 15) {
                r = rdm.nextInt(15 - i);
                aux = (int) list.get(r);
                list.remove(r);
                this.tablero[i] = aux;
            }
            if(i==15){
                this.tablero[i]=16;
            }

            //obtengo el id de cada boton
            int id = this.getResources().getIdentifier("b" + (i + 1), "id", this.getPackageName());
            // seteo su background con el fragmento de imagen que corresponda
            int idi = this.getResources().getIdentifier("a" + aux, "mipmap", this.getPackageName());
            Button b = (Button) findViewById(id);
            b.setBackground(this.getResources().getDrawable(idi, null));
        }

        System.out.println("TABLERO INICIAL");
        for (i = 0; i < 16; i++) {
            System.out.print(this.tablero[i] + "-");
        }
        System.out.println("");

    }
}