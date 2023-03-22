package davi.nunes.ribeiro.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.btnEnviar); //encontra o botão na tela
        btnEnviar.setOnClickListener(new View.OnClickListener() { //cria ação para o click no botão
            @Override
            public void onClick(View view) {
                // encontrando textos na tela
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etAssunto = findViewById(R.id.etAssunto);
                EditText etTexto = findViewById(R.id.etTexto);

                // transformando textos em string
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); //cria intent que procura no celular aplicativos que podem realizar a ação "SENDTO"

                i.setData(Uri.parse("mailto:")); //limita as opções apenas para aplicativos de email

                String[] emails = new String[]{email}; //lista de emails de destinatários

                //preenchimento da intent
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try { // caso haja mais de um aplicativo de email, permite a escolha
                    startActivity(Intent.createChooser(i, "Escolha o app:"));
                }
                catch (ActivityNotFoundException e){ //caso não há nenhum aplicativo de email disponível no sistema, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa ação", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}