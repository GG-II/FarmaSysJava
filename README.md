# JavaFarmaSys

**JavaFarmaSys** es un sistema de gestión para farmacias desarrollado en Java. Permite controlar ventas, compras, inventario de medicamentos, clientes y proveedores, facilitando la administración eficiente de una farmacia.

## Características principales

- Gestión de clientes, proveedores, medicamentos, compras y ventas.
- Interfaz gráfica intuitiva desarrollada en Java Swing.
- Búsqueda avanzada y filtrado de registros.
- Atajos de teclado para una experiencia de usuario más dinámica.
- Visualización de resultados en tablas para una mejor gestión de la información.
- Arquitectura modular siguiendo el patrón MVC (Modelo-Vista-Controlador).
- Acceso a base de datos mediante DAOs.

## Estructura del proyecto
src/
├── dao/ # Acceso a datos (Data Access Objects)
├── main/ # Clase principal y conexión a la base de datos
├── model/ # Clases de entidades (Cliente, Medicina, etc.)
├── resources/ # Imágenes y recursos gráficos
└── vistas/ # Interfaces gráficas (formularios y pantallas)


## Instalación y ejecución

1. Clona el repositorio:
git clone https://github.com/tu-usuario/JavaFarmaSys.git

2. Abre el proyecto en tu IDE favorito (por ejemplo, NetBeans o IntelliJ IDEA).
3. Configura la conexión a la base de datos en el archivo `conexionBD.java`.
4. Ejecuta la clase principal `MainFrame.java`.

## Uso de ramas

- `main`: Rama principal y estable.
- `develop`: Rama de desarrollo donde se integran nuevas funcionalidades.
- `feature/*`: Ramas para nuevas funcionalidades (por ejemplo, `feature/enter-key-shortcuts`).
- `bugfix/*`: Ramas para corrección de errores.
- `release/*`: Ramas para preparar lanzamientos.
- `hotfix/*`: Ramas para corregir errores críticos en producción.

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o un pull request para sugerir mejoras o reportar errores.

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
