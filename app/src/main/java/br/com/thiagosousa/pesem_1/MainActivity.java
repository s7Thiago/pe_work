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

import br.com.thiagosousa.pesem_1.objetos.Ocorrencia;
import br.com.thiagosousa.pesem_1.useful.UtilActivity;

public class MainActivity extends UtilActivity implements View.OnClickListener {
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
    int virtual[] = new int[tam], contVirtual = 0;
    int presencial[] = new int[tam], contPresencial = 0;
    int diferenca[] = new int[tam];
    int soma = 0;
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
                        ocorrencia.setDescricao("Presencial");

                        //Armazena o valor recebido no vetor de presencial
                        presencial[contPresencial] = Integer.valueOf(dado);

                        alternador = 1;
                        contPresencial++;
                    } else {
                        ocorrencia = new Ocorrencia(Integer.valueOf(dado));
                        ocorrencia.setDescricao("Virtual");

                        //Armazena o valor recebido no vetor de virtual
                        virtual[contVirtual] = Integer.valueOf(dado);

                        alternador = 0;
                        contVirtual++;
                    }

                    ocorrenciasList.add(ocorrencia);
                    entradaPrincipal.setText("");
                    adapter.notifyDataSetChanged();

                } else {
                    entradaPrincipal.setError("Este campo não apresenta o resultado esperado\n\n Por favor, insira um valor numérico");
                }

                break;
            case R.id.botao_calcular:

                //Faz as diferenças entre o virtual e o presencial e armazena cada uma no vetor
                for(int i = 0; i < tam; i++) {
                 diferenca[i] = virtual[i] - presencial[i];
                }

                //Efetua a soma das diferenças acumuladas anteriormente no vetor de diferenças
                for(int i = 0; i < tam; i++) {
                    soma += diferenca[i];
                }

                //Configurando a media
                media = (double) soma/comprimento;

                //Calculando a variancia amostral
                for(int i = 0; i < tam; i++) {
                    somaVar += Math.pow((diferenca[i] - media), 2);
                }

                //Configurando a variancia
                somaVar = somaVar/tam;

                String calculos = "media dos valores: " + media
                        + "\nVariância amostral: " + somaVar;

                mostrarMsg(calculos);

                break;
        }
    }
}