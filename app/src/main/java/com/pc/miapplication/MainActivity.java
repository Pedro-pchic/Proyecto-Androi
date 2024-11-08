package com.pc.miapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnPersona;
    private Button btnProducto;
    private Button btnOrdenes;
    private Button btnInformes;
    private Button btnConfiguraciones;
    private FirebaseAuth firebaseAuth; // Declaración de FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        bindUI();
        evento();
    }

    private void iniciarSesion(String email, String password) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    MainActivity.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void bindUI() {
        btnPersona = findViewById(R.id.btnPersona);
        btnProducto = findViewById(R.id.btnProducto);
        btnConfiguraciones = findViewById(R.id.btnConfiguraciones);
        btnInformes = findViewById(R.id.btnInformes);
        btnOrdenes = findViewById(R.id.btnOrdenes);
    }

    private void evento() {
        btnPersona.setOnClickListener(view -> {
            iniciarSesion("pchicu@miumg.edu.gt", "pedro123");
            Intent intent = new Intent(MainActivity.this, PersonaActivity.class);
            startActivity(intent);
        });

        btnProducto.setOnClickListener(view -> {
            iniciarSesion("pchicu@miumg.edu.gt", "pedro123");
            Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
            startActivity(intent);
        });

        btnConfiguraciones.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
            startActivity(intent);
        });

        btnInformes.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, InformesActivity.class);
            startActivity(intent);
        });

        btnOrdenes.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrdenesActivity.class);
            startActivity(intent);
        });
    }
}

