package com.example.parcial_am_acn4b_catero_moris;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUsuario;
    private TextView textViewRango;
    private TextView textViewRol;
    private TextView textViewAgente;
    private Button buttonModificar;
    private Button buttonHistorial;
    private Button buttonCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewUsuario = findViewById(R.id.username);
        textViewRango = findViewById(R.id.rangoact);
        textViewRol = findViewById(R.id.rolmain);
        textViewAgente = findViewById(R.id.agentmain);
        buttonModificar = findViewById(R.id.modifuser);

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarTextos();
            }
        });

        buttonHistorial = findViewById(R.id.button8);
        buttonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
                startActivity(intent);
            }
        });

        buttonCerrarSesion = findViewById(R.id.button9);
        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cambiarTextos() {
        textViewUsuario.setText("GhostWarrior");
        textViewRango.setText("Diamante III");
        textViewRol.setText("Centinela");
        textViewAgente.setText("Killjoy");
    }
}
