1. **Gestión de Clientes y Vehículos**  
   "Gestión de Clientes y Vehículos" es una aplicación de escritorio desarrollada en Java con Swing, siguiendo el patrón MVC,
   diseñada para gestionar clientes y sus vehículos. Permite agregar, editar y eliminar tanto personas como vehículos, además de asociar vehículos a clientes
   (una persona puede tener varios vehículos, pero cada vehículo pertenece a una sola persona). Incluye una interfaz intuitiva y soporte para bases de datos,
   ideal para aprender sobre relaciones uno a muchos y desarrollo orientado a objetos.

3. **Funcionalidades**  
   - Agrega, edita y elimina clientes (personas) con información detallada como nombre, dirección y contacto.  
   - Gestiona vehículos con datos como matrícula, marca, modelo y año, con opción de edición y eliminación.  
   - Asocia vehículos a clientes de forma flexible (una persona puede tener múltiples vehículos, pero un vehículo solo tiene un dueño).  
   - Interfaz moderna con botones personalizados y navegación sencilla para una experiencia de usuario optimizada.  
   - Almacenamiento de datos persistente utilizando SQLite, con soporte para bases de datos internas y externas.

4. **Tecnologías**  
   - Lenguaje: Java (Swing para GUI)  
   - Patrones: MVC, DAO, Service, DTO  
   - Base de datos: SQLite (incluye una base de datos interna y soporta una externa para exportación o migración)  
   - Interfaz: Swing con componentes personalizados  
   - Entornos compatibles: Funciona en IDEs como Eclipse, IntelliJ IDEA y NetBeans, con configuraciones adaptables.

5. **Requisitos Previos**  
   - Java Development Kit (JDK) 8 o superior instalado.  
   - Un IDE compatible (Eclipse, IntelliJ IDEA, NetBeans, o similar).  
   - Dependencias: Driver de SQLite y bibliotecas de Swing (incluidas en el repositorio).  
   - Git para clonar el repositorio.  
   - Sistema operativo: Compatible con Windows, macOS y Linux.

6. **Instalación**  
   - Clona el repositorio:  
     ```bash
     git clone https://github.com/jettsus/GestionClientesVehiculos.git
