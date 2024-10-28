package com.example.examenejercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Clase que representa la actividad principal de la aplicación
public class ActividadPrincipal extends AppCompatActivity implements AdaptadorTarea.OnTareaClickListener {

    //Variables
    private AdaptadorTarea adapterTarea;
    private RepositorioTarea repositorioTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        repositorioTarea = RepositorioTarea.getInstancia(this);
        List<Tarea> tareasPendientes = repositorioTarea.getTareasPendientes();

        adapterTarea = new AdaptadorTarea(tareasPendientes, this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_tareas_hechas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTarea);

        Button btnAgregarTarea = findViewById(R.id.boton_anadir_tarea);
        Button btnVerTareasHechas = findViewById(R.id.boton_tareas_hechas);

        btnAgregarTarea.setOnClickListener(v -> mostrarDialogoAñadirTarea());

        btnVerTareasHechas.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadPrincipal.this, ActividadTareasHechas.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterTarea.notifyDataSetChanged();
    }

    //Método para mostrar un diálogo para añadir una nueva tarea
    private void mostrarDialogoAñadirTarea() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_tarea, null);
        EditText editTextTitle = view.findViewById(R.id.añadirTarea);

        builder.setView(view)
            .setTitle("Añadir Tarea")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Añadir", (dialog, id) -> {
            String nombreTarea = editTextTitle.getText().toString();
            if (!nombreTarea.isEmpty()) {
                    repositorioTarea.agregarTareaPendiente(new Tarea(nombreTarea));
                    adapterTarea.notifyDataSetChanged();
                }
            });

        builder.create().show();
    }

    //Método para marcar una tarea como hecha
    @Override
    public void onTareaHecha(int position, boolean isChecked) {
        if (isChecked) {
            new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Estás seguro de que quieres marcar esta tarea como completada?")
                .setPositiveButton("Confirmar", (dialog, which) -> {
                    repositorioTarea.marcarTareaHecha(position);
                    adapterTarea.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    adapterTarea.notifyItemChanged(position);
                })
                .show();
        }
    }

    //Método para eliminar una tarea de la lista principal de tareas pendientes
    @Override
    public void onTareaEliminada(int position) {
        new AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
            .setPositiveButton("Confirmar", (dialog, which) -> {
                repositorioTarea.eliminarTareaPendiente(position);
                adapterTarea.notifyDataSetChanged();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }
}