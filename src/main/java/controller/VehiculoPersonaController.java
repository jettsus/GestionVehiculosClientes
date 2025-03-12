package controller;

import DTO.PersonaVehiculoDTO;
import view.Principal;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import service.servicePersona;
import service.servicePersonaVehiculo;
import service.serviceVehiculo;
import view.RegistroVehiculo;
import view.viewAsociarVehiculo;
import view.viewCrearVehiculo;

public class VehiculoPersonaController {

    private servicePersona servicePersona;
    private serviceVehiculo serviceVehiculo;
    private servicePersonaVehiculo personaVehiculoService;
    private Principal vistaPrincipal;
    private viewCrearVehiculo vistaCrearVehiculo;
    private RegistroVehiculo vistaRegistroVehiculo;
    private viewAsociarVehiculo vistaAsociarVehiculo;

    private int paginaActual = 1;  // Página inicial
    private final int tamanopagina = 10; // Número de resultados por página
    private boolean filtrosActivos = false; // Controla si los filtros están activos

    // Filtros almacenados para la paginación
    private String nombre;
    private String marca;
    private String modelo;
    private String genero;
    private Integer año;
    private Integer numVehiculos;

    public VehiculoPersonaController() {
        this.servicePersona = new servicePersona();
        this.serviceVehiculo = new serviceVehiculo();
        this.personaVehiculoService = new servicePersonaVehiculo();
    }

// Constructor para la vista principal - usando el constructor sin parámetros para inicializar servicios
    public VehiculoPersonaController(Principal vistaPrincipal) {
        this();  // Llama al constructor sin parámetros
        this.vistaPrincipal = vistaPrincipal;
    }

// Constructor para la vista de crear vehículo
    public VehiculoPersonaController(viewCrearVehiculo vistaCrearVehiculo) {
        this();  // Llama al constructor sin parámetros
        this.vistaCrearVehiculo = vistaCrearVehiculo;
    }

// Constructor para la vista de registro de vehículo
    public VehiculoPersonaController(RegistroVehiculo vistaRegistroVehiculo) {
        this();  // Llama al constructor sin parámetros
        this.vistaRegistroVehiculo = vistaRegistroVehiculo;
    }

// Constructor para la vista de asociar vehículo
    public VehiculoPersonaController(viewAsociarVehiculo vistaAsociarVehiculo) {
        this();  // Llama al constructor sin parámetros
        this.vistaAsociarVehiculo = vistaAsociarVehiculo;
    }

    //suma pagina y comprueba si filtros estan activos para hacerlo en funcion de ellos el paginado o no
    public void mostrarPaginaSiguiente() {
        paginaActual++;
        if (filtrosActivos) {
            actualizarTablaConFiltros();
        } else {
            mostrarPersonasConVehiculos(); // Usa datos sin filtrar
        }
        actualizarEstadoBotones();
    }

    //si es mayor a 1 resta pagina para volver y comprueba si filtros esta activo
    public void mostrarPaginaAnterior() {
        if (paginaActual > 1) {
            paginaActual--;
            if (filtrosActivos) {
                actualizarTablaConFiltros();
            } else {
                mostrarPersonasConVehiculos();// Usa datos sin filtrar
            }
            actualizarEstadoBotones();
        }
    }

    public void mostrarPersonasConVehiculos() {

        // Reinicia la lista de personas con vehículos desde la primera página
        List<PersonaVehiculoDTO> personasConVehiculos = personaVehiculoService.obtenerPersonasConVehiculos(paginaActual, tamanopagina);

        // Comprobar si la lista está vacía
        System.out.println("Número de registros devueltos: " + personasConVehiculos.size());

        DefaultTableModel modeloTabla = (DefaultTableModel) vistaPrincipal.getJTable1().getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla

        if (personasConVehiculos != null && !personasConVehiculos.isEmpty()) {
            for (PersonaVehiculoDTO personaVehiculo : personasConVehiculos) {
                int numeroPropietarios = personaVehiculoService.obtenerNumeroPropietariosPorVehiculo(personaVehiculo.getMatricula());
                Object[] fila = {
                    personaVehiculo.getNombre(),
                    personaVehiculo.getMatricula(),
                    personaVehiculo.getAño(),
                    personaVehiculo.getMarca(),
                    personaVehiculo.getModelo(),
                    numeroPropietarios
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(vistaPrincipal, "No hay registros disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        actualizarEstadoBotones(); // Actualiza los botones de paginación
    }

    private void actualizarEstadoBotones() {
        List<PersonaVehiculoDTO> resultadosPaginaActual;

        // Verificar si los filtros están activos o no
        if (filtrosActivos) {
            resultadosPaginaActual = personaVehiculoService.filtrarPersonasConVehiculos(
                    nombre, marca, modelo, genero, año, numVehiculos, paginaActual, tamanopagina);
        } else {
            resultadosPaginaActual = personaVehiculoService.obtenerPersonasConVehiculos(paginaActual, tamanopagina);
        }

        // Si no hay resultados en la página actual, retroceder a la página anterior si es posible
        if (resultadosPaginaActual.isEmpty() && paginaActual > 1) {
            paginaActual--;
            actualizarTablaConFiltros(); // Recargar la tabla con la página anterior
            return;
        }

        // Habilitar/deshabilitar el botón "Anterior"
        vistaPrincipal.getbanterior().setEnabled(paginaActual > 1);

        // Habilitar/deshabilitar el botón "Siguiente"
        vistaPrincipal.getbsiguiente().setEnabled(resultadosPaginaActual.size() == tamanopagina);
    }

    public void aplicarFiltros(String nombre, String marca, String modelo, String genero, Integer año, Integer numVehiculos) {
        // Reinicia la paginación a la primera página al aplicar nuevos filtros
        paginaActual = 1;

        // Almacenar filtros en variables de instancia
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.genero = genero;
        this.año = año;
        this.numVehiculos = numVehiculos;

        // Activa los filtros
        filtrosActivos = true;

        // Llama al método para actualizar la tabla con filtros en la primera página
        actualizarTablaConFiltros();
    }

    private void actualizarTablaConFiltros() {
        // Obtiene la lista filtrada de la página actual
        List<PersonaVehiculoDTO> listaFiltrada = personaVehiculoService.filtrarPersonasConVehiculos(
                nombre, marca, modelo, genero, año, numVehiculos, paginaActual, tamanopagina);

        // Actualizar la tabla en la vista con los resultados filtrados
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaPrincipal.getJTable1().getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar los nuevos datos

        for (PersonaVehiculoDTO personaVehiculo : listaFiltrada) {
            //obtengo el numero de propietarios en funcion de la matricula y lo recorro en un array para meterlo en una fila de la tabla
            int numeroPropietarios = personaVehiculoService.obtenerNumeroPropietariosPorVehiculo(personaVehiculo.getMatricula());
            Object[] fila = {
                personaVehiculo.getNombre(),
                personaVehiculo.getMatricula(),
                personaVehiculo.getAño(),
                personaVehiculo.getMarca(),
                personaVehiculo.getModelo(),
                numeroPropietarios
            };
            modeloTabla.addRow(fila);
        }

        // Actualiza el estado de los botones de navegación después de aplicar los filtros
        actualizarEstadoBotones();
    }

    //obtengo marcas para la vista crear vehiculo  metiendolo en una lista
    public void mostrarcbMarcaEnVistaCrearVehiculo() {
        try {

            List<String> marcas = personaVehiculoService.obtenerMarcas();

            vistaCrearVehiculo.getcbMarca().removeAllItems();

            //recorro las marcas y lo meto en el combox que lo recojo con el get propio
            for (String marca : marcas) {
                vistaCrearVehiculo.getcbMarca().addItem(marca);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //muestro modelos en funcion de la marca que tienen que tener relacion
    public void mostrarModelosPorMarcaVistaCrearVehiculo(String marcaSeleccionada) {
        try {

            // Llamamos al servicio para obtener los modelos de la marca seleccionada
            List<String> modelos = personaVehiculoService.obtenerModelosPorMarca(marcaSeleccionada);

            // Limpio  el ComboBox de modelos antes de agregar los nuevos
            vistaCrearVehiculo.getcbModelo().removeAllItems();

            //Si se encuentran modelos  se recorren y recoge el modelo y lo añade
            if (modelos != null && !modelos.isEmpty()) {
                for (String modelo : modelos) {
                    vistaCrearVehiculo.getcbModelo().addItem(modelo);
                }
            } else {
                System.out.println("No se encontraron modelos para la marca seleccionada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener los modelos de la marca: " + e.getMessage());
        }
    }

    // Método para cargar las marcas en el ComboBox de la vista
    public void mostrarcbMarcaEnVistaPrin() {
        try {
            // Asegúrate de que el método obtenerMarcas esté implementado en el servicio
            List<String> marcas = personaVehiculoService.obtenerMarcas();

            vistaPrincipal.getcbMarca().removeAllItems();
            vistaPrincipal.getcbMarca().addItem("Todos"); // Añadir "Todos" al inicio

            if (marcas != null && !marcas.isEmpty()) {
                for (String marca : marcas) {
                    vistaPrincipal.getcbMarca().addItem(marca);
                }
            } else {
                System.out.println("No se encontraron marcas en la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los modelos por marca seleccionada en el ComboBox de la vista
    public void mostrarModelosPorMarcaVistaPrincipal(String marcaSeleccionada) {
        try {
            // Verificamos que la marca seleccionada no sea "Todos"
            if (marcaSeleccionada.equals("Todos")) {
                vistaPrincipal.getcbModelo().removeAllItems();
                vistaPrincipal.getcbModelo().addItem("Todos");
                return;  // Si seleccionó "Todos", no cargamos modelos específicos.
            }

            // Llamamos al servicio para obtener los modelos de la marca seleccionada
            List<String> modelos = personaVehiculoService.obtenerModelosPorMarca(marcaSeleccionada);

            // Limpiamos el ComboBox de modelos antes de agregar los nuevos
            vistaPrincipal.getcbModelo().removeAllItems();
            vistaPrincipal.getcbModelo().addItem("Todos");  // Añadimos "Todos" al inicio

            // Si se han encontrado modelos, los agregamos al ComboBox
            if (modelos != null && !modelos.isEmpty()) {
                for (String modelo : modelos) {
                    vistaPrincipal.getcbModelo().addItem(modelo);
                }
            } else {
                // Si no se encontraron modelos, mostramos un mensaje en la consola
                System.out.println("No se encontraron modelos para la marca seleccionada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener los modelos de la marca: " + e.getMessage());
        }
    }

    //metodo para eliminar un vehiculo seleccionado
    public void eliminarVehiculoSeleccionado() {
        int filaSeleccionada = vistaPrincipal.getJTable1().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccione una fila para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener la matrícula del vehículo de la fila seleccionada
        String matricula = (String) vistaPrincipal.getJTable1().getValueAt(filaSeleccionada, 1);

        // Confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal, "¿Está seguro de que desea eliminar este vehículo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean vehiculoEliminado = personaVehiculoService.eliminarVehiculo(matricula);

            if (vehiculoEliminado) {
                // Eliminar la fila de la tabla en la interfaz de usuario
                ((javax.swing.table.DefaultTableModel) vistaPrincipal.getJTable1().getModel()).removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(vistaPrincipal, "Vehículo eliminado correctamente.", "Vehiculo eliminado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vistaPrincipal, "No se pudo eliminar el vehículo. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void mostrarDetallesVehiculo(String matricula) {
        //inicializo la ventana si no lo estaba
        if (vistaRegistroVehiculo == null) {
            vistaRegistroVehiculo = new RegistroVehiculo();
        }
        // Actualizo la matrícula en el JLabel
        vistaRegistroVehiculo.setMatriculaLabel(matricula);

        // Obtengo los detalles
        List<PersonaVehiculoDTO> detalles = personaVehiculoService.obtenerDetallesVehiculo(matricula);

        //limpio y actualizo la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) vistaRegistroVehiculo.getDetallesTable().getModel();
        modeloTabla.setRowCount(0); // Limpio tabla

        for (PersonaVehiculoDTO detalle : detalles) {
            modeloTabla.addRow(new Object[]{
                detalle.getNombre(),
                detalle.getGenero(),
                detalle.getFecha_inicio(),
                detalle.getFecha_fin()
            });
        }

        // Hago visible la ventana de detalles
        vistaRegistroVehiculo.setVisible(true);
    }

    // Método para obtener el ID de la persona a partir de su DNI
    public int obtenerIdPersonaPorDNI(String dni) {
        return servicePersona.obtenerIdPersonaPorDNI(dni);
    }

    // Método para procesar la asociación de un vehículo con una persona
    public boolean procesarAsociacionVehiculo(int filaPersona, int filaVehiculo, JTable tablaPersona, JTable tablaVehiculo) {
        try {
            // Obtener datos de la persona desde la tabla
            String dni = (String) tablaPersona.getValueAt(filaPersona, 0);
            String nombre = (String) tablaPersona.getValueAt(filaPersona, 1);

            // Manejar el valor de género adecuadamente para evitar ClassCastException
            Object generoObj = tablaPersona.getValueAt(filaPersona, 2);
            char genero;
            if (generoObj instanceof String) {
                // Si es un String, obtener el primer carácter
                genero = ((String) generoObj).charAt(0);
            } else if (generoObj instanceof Character) {
                // Si ya es un Character, utilizarlo directamente
                genero = (Character) generoObj;
            } else {
                // Si no es un valor válido, mostrar un mensaje de error y retornar false
                JOptionPane.showMessageDialog(null, "El valor del género no es válido. Debe ser un solo carácter ('H' o 'M').", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Obtener datos del vehículo desde la tabla
            String matricula = (String) tablaVehiculo.getValueAt(filaVehiculo, 0);
            int año = (int) tablaVehiculo.getValueAt(filaVehiculo, 1);
            String marca = (String) tablaVehiculo.getValueAt(filaVehiculo, 2);
            String modelo = (String) tablaVehiculo.getValueAt(filaVehiculo, 3);

            // Crear un objeto DTO con los datos seleccionados
            PersonaVehiculoDTO historico = new PersonaVehiculoDTO();
            historico.setDni(dni);
            historico.setNombre(nombre);
            historico.setGenero(genero);
            historico.setMatricula(matricula);
            historico.setAño(año);
            historico.setMarca(marca);
            historico.setModelo(modelo);
            historico.setFecha_inicio(new java.sql.Date(System.currentTimeMillis()));  // Fecha de inicio actual

            // Obtener el ID de la persona utilizando el servicio
            int idPersona = obtenerIdPersonaPorDNI(dni);
            if (idPersona == -1) {
                System.out.println("Error: No se pudo encontrar el ID de la persona con el DNI proporcionado.");
                return false;
            }
            historico.setId_persona(idPersona);

            // Obtener el ID del vehículo utilizando el servicio
            int idVehiculo = serviceVehiculo.obtenerIdVehiculoPorMatricula(matricula);
            if (idVehiculo == -1) {
                System.out.println("Error: No se pudo encontrar el ID del vehículo con la matrícula proporcionada.");
                return false;
            }
            historico.setId_vehiculo(idVehiculo);

            // Llamar al servicio para asociar el vehículo
            return personaVehiculoService.asociarVehiculoConTransaccion(historico);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void resetearFiltros() {
        filtrosActivos = false;
        nombre = null;
        marca = null;
        modelo = null;
        genero = null;
        año = null;
        numVehiculos = null;
    }

    public void reiniciarPaginacion() {
        this.paginaActual = 1; // Reiniciar la página actual
    }

}
