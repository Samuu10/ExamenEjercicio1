package com.example.examenejercicio1;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Clase que representa la actividad de las tareas hechas
public class ActividadTareasHechas extends AppCompatActivity implements AdaptadorTarea.OnTareaClickListener{

    //Variables
    private AdaptadorTarea adapterTarea;
    private RepositorioTarea repositorioTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_tareas_hechas);

        repositorioTarea = RepositorioTarea.getInstancia(this);
        List<Tarea> tareasHechas = repositorioTarea.getTareasHechas();

        adapterTarea = new AdaptadorTarea(tareasHechas, this);

        //Configuración del RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tareas_hechas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTarea);

        //Botón para volver a la actividad principal de tareas pendientes
        Button btnVolver = findViewById(R.id.boton_volver);
        btnVolver.setOnClickListener(v -> finish());
    }

    //Método para desmarcar una tarea como hecha
    @Override
    public void onTareaHecha(int position, boolean isChecked) {
        if (!isChecked) {
            new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Estás seguro de que quieres desmarcar esta tarea como completada?")
                .setPositiveButton("Confirmar", (dialog, which) -> {
                    repositorioTarea.desmarcarTareaHecha(position);
                    adapterTarea.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    adapterTarea.notifyItemChanged(position);
                })
                .show();
        }
    }

    //Método para eliminar una tarea de la lista secundaria de tareas hechas
    @Override
    public void onTareaEliminada(int position) {
        new AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
            .setPositiveButton("Confirmar", (dialog, which) -> {
                repositorioTarea.eliminarTareaHecha(position);
                adapterTarea.notifyDataSetChanged();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }
}