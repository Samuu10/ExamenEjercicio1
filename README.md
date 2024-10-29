# Aplicación de Gestión de Tareas

## Objetivo

La aplicación de Gestión de Tareas está diseñada para ayudar a los usuarios a gestionar sus tareas pendientes y completadas de manera eficiente.  
Los usuarios pueden agregar, ver, marcar como hechas, desmarcar y eliminar tareas.  
La aplicación también guarda las listas de tareas utilizando `SharedPreferences`, asegurando que las listas persistan incluso después de cerrar la aplicación o reiniciar el dispositivo.

## Descripción de Clases Java

### `ActividadPrincipal.java`

La actividad principal de la aplicación. Inicializa los componentes de la interfaz de usuario, maneja la adición de nuevas tareas, y permite la navegación a la actividad de tareas hechas. Utiliza `RepositorioTarea` para gestionar la lista de tareas pendientes.

### `AdaptadorTarea.java`

Adaptador para gestionar la lista de tareas en un `RecyclerView`. Asigna los datos de la tarea a los elementos de la interfaz de usuario en cada ítem de la lista y maneja eventos de clic en los ítems para marcar tareas como hechas o eliminarlas.

### `Tarea.java`

Representa una tarea con un nombre y un estado de si está hecha o no. Contiene los atributos `nombre` y `hecha` con sus respectivos getters y setters.

### `RepositorioTarea.java`

Gestiona las listas de tareas pendientes y hechas, y maneja la persistencia utilizando `SharedPreferences`. Añade, marca, desmarca y elimina tareas de las listas, y guarda y carga las listas de tareas desde `SharedPreferences`.

### `ActividadTareasHechas.java`

La actividad que muestra las tareas completadas. Inicializa los componentes de la interfaz de usuario, maneja la desmarcación y eliminación de tareas hechas. Utiliza `RepositorioTarea` para gestionar la lista de tareas hechas.

## Descripción de Archivos XML

- `actividad_principal.xml`: Diseño para la actividad principal.
- `actividad_tareas_hechas.xml`: Diseño para la actividad de tareas hechas.
- `dialogo_tarea.xml`: Diseño para el diálogo de agregar una nueva tarea.
- `item_tarea.xml`: Diseño para cada ítem en la lista de tareas.

### Link al repositorio: https://github.com/Samuu10/ExamenEjercicio1.git
