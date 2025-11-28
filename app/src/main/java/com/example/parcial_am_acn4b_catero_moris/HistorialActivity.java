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

public class HistorialActivity extends AppCompatActivity {

    private LinearLayout container;
    private Button buttonVolver;

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
        String[] historial = {
                "Partida 1: Mapa {Bind} Personaje {Viper} Resultado: Victoria",
                "Partida 2: Mapa {Bind} Personaje {Raze} Resultado: Derrota",
                "Partida 3: Mapa {Heaven} Personaje {Fade} Resultado: Victoria",
                "Partida 4: Mapa {Split} Personaje {Astra} Resultado: Victoria",
                "Partida 5: Mapa {Icebox} Personaje {Brimstone} Resultado: Derrota",
                "Partida 6: Mapa {Heaven} Personaje {Cypher} Resultado: Victoria",
                "Partida 7: Mapa {Lotus} Personaje {KAY/O} Resultado: Victoria",
                "Partida 8: Mapa {Fracture} Personaje {Omen} Resultado: Victoria",
                "Partida 9: Mapa {Ascent} Personaje {Skye} Resultado: Derrota",
                "Partida 10: Mapa {Heaven} Personaje {Breach} Resultado: Victoria",
                "Partida 11: Mapa {Icebox} Personaje {Killjoy} Resultado: Victoria",
                "Partida 12: Mapa {Split} Personaje {Reyna} Resultado: Derrota"
        };

        container.removeAllViews();

        for (String partida : historial) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(partida);
            container.addView(textView);

            View divider = new View(this);
            divider.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1));
            divider.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            container.addView(divider);
        }
    }
}

