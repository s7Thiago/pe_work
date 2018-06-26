package br.com.thiagosousa.pesem_1;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import objetos.Ocorrencia;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SCREENTAG = "evento da MainActivity";

    //views
    private ListView listaPrincipal;
    private TextInputEditText entradaPrincipal;
    private ArrayList<Ocorrencia> ocorrenciasList;
    private ArrayAdapter<Ocorrencia> adapter;
    private Button adicionar;
    private Button calcular;

    //variaveis para os calculos
    int tam = 167, comprimento = 168, alternador = 1;
    int virtual[] = new int[tam];
    int presencial[] = new int[tam];
    int diferenca[] = new int[tam];
    double media, somatorio = 0, variancia, somaVar;
    Ocorrencia ocorrencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(true);

        adicionar.setOnClickListener(this);
        calcular.setOnClickListener(this);

        ocorrenciasList = new ArrayList<Ocorrencia>();

        adapter = new ArrayAdapter<Ocorrencia>(getBaseContext(), android.R.layout.simple_list_item_1, ocorrenciasList);
        listaPrincipal.setAdapter(adapter);


    }

    public void initViews(boolean init) {
        if (init) {
            listaPrincipal = findViewById(R.id.listaPrincipal);
            entradaPrincipal = findViewById(R.id.entradaDeTextoPrincipal);
            adicionar = findViewById(R.id.botao_adicionar);
            calcular = findViewById(R.id.botao_calcular);
        } else {
            Log.w(SCREENTAG, "O recurso de carregamento dos componentes gráficos está desativado");
        }
    }

    //Eventos de clique dos elementos visuais
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botao_adicionar:
                Log.w(SCREENTAG, "O botão adicionar de MainActivity foi pressionado");

                String dado = entradaPrincipal.getText().toString();

                //Por regra. a informação recebida do campo deve ter mais de 0 caractere
                if (dado.length() > 0) {

                    if (alternador == 0) {
                        ocorrencia = new Ocorrencia(Integer.valueOf(dado));
                        ocorrencia.setDescricao("Virtual");
                        alternador = 1;
                    } else {
                        ocorrencia = new Ocorrencia(Integer.valueOf(dado));
                        ocorrencia.setDescricao("Presencial");
                        alternador = 0;
                    }

                    ocorrenciasList.add(ocorrencia);
                    entradaPrincipal.setText("");
                    adapter.notifyDataSetChanged();

                } else {
                    entradaPrincipal.setError("Este campo não apresenta o resultado esperado\n Por favor, inssira um valor numérico");
                }

                break;
            case R.id.botao_calcular:


                break;
        }
    }
}
