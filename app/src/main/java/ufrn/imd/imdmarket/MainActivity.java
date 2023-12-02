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
    private EditText loginEditText, passwordEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = findViewById(R.id.login);
        passwordEditText = findViewById(R.id.senha);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        Button loginButton = findViewById(R.id.entrarButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateLogin()) {
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(menuIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login inválido. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin() {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (sharedPreferences.contains("login") && sharedPreferences.contains("senha")) {
            return login.equals(sharedPreferences.getString("login", "")) && password.equals(sharedPreferences.getString("senha", ""));
        } else {
            return login.equals("admin") && password.equals("admin");
        }
    }

    public void showChangePasswordDialog(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Alterar Senha");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_alterar_senha, null);
        dialog.setView(dialogView);

        final EditText passwordEditText = dialogView.findViewById(R.id.senhaAtual);
        final EditText newPasswordEditText = dialogView.findViewById(R.id.novaSenha);

        dialog.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = passwordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                String savedPassword = sharedPreferences.getString("senha", "admin");
                if (password.equals(savedPassword)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (!sharedPreferences.contains("login")) {
                        editor.putString("login", "admin");
                    }
                    editor.putString("senha", newPassword);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Senha alterada!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Senha atual incorreta.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.create().show();
    }
}
