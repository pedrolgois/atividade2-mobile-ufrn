package ufrn.imd.imdmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText loginEditText, senhaEditText;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = findViewById(R.id.login);
        senhaEditText = findViewById(R.id.senha);
        Button entrarButton = findViewById(R.id.entrarButton);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCredenciais()) {
                    iniciarMenuActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Credenciais inválidas. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarCredenciais() {
        String login = loginEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        if (sharedPreferences.contains("login") && sharedPreferences.contains("senha")) {
            return login.equals(sharedPreferences.getString("login", "")) && senha.equals(sharedPreferences.getString("senha", ""));
        } else {
            return login.equals("admin") && senha.equals("admin");
        }
    }

    private void iniciarMenuActivity() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void exibirDialogAlterarSenha(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alterar Senha");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_alterar_senha, null);
        builder.setView(dialogView);

        final EditText senhaAtualEditText = dialogView.findViewById(R.id.senhaAtual);
        final EditText novaSenhaEditText = dialogView.findViewById(R.id.novaSenha);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String senhaAtual = senhaAtualEditText.getText().toString();
                String novaSenha = novaSenhaEditText.getText().toString();

                if (verificarSenhaAtual(senhaAtual)) {
                    salvarNovaSenha(novaSenha);
                    Toast.makeText(getApplicationContext(), "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Senha atual incorreta. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private boolean verificarSenhaAtual(String senhaAtual) {
        String senhaSalva = sharedPreferences.getString("senha", "admin");
        return senhaAtual.equals(senhaSalva);
    }

    private void salvarNovaSenha(String novaSenha) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.contains("login")) {
            editor.putString("login", "admin");
        }
        editor.putString("senha", novaSenha);
        editor.apply();
    }
}