package br.com.thiagosousa.pesem_1.useful;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class UtilActivity extends AppCompatActivity {

    public void mostrarMsg(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

}
