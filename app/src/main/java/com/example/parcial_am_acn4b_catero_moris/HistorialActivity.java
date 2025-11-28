package com.example.parcial_am_acn4b_catero_moris;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public class HistorialActivity extends AppCompatActivity {

    private LinearLayout container;
    private Button buttonVolver;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.historial), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnHistorial = findViewById(R.id.btnhistorial);
        container = findViewById(R.id.linearlayoutscroll);

        db = FirebaseFirestore.getInstance();

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarHistorial();
            }
        });

        buttonVolver = findViewById(R.id.button10);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cargarHistorial() {
        container.removeAllViews();

        db.collection("Partidas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String mapa = document.getString("Mapa");
                                String personaje = document.getString("Personaje");
                                String resultado = document.getString("Resultado");

                                String partida = "Partida " + count + ": Mapa {" + mapa + "} Personaje {" + personaje + "} Resultado: " + resultado;

                                TextView textView = new TextView(HistorialActivity.this);
                                textView.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView.setText(partida);
                                container.addView(textView);

                                View divider = new View(HistorialActivity.this);
                                divider.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        1));
                                divider.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                                container.addView(divider);

                                count++;
                            }
                        } else {
                            TextView errorView = new TextView(HistorialActivity.this);
                            errorView.setText("Error al cargar el historial: " + task.getException().getMessage());
                            container.addView(errorView);
                        }
                    }
                });
    }
}

