# Prácticas usadas
Referencias: https://www.oracle.com/technetwork/java/codeconventions-150003.pdf

## Reporte SonarLint
![](sonarlint.png)

### `Dinero.java`
#### Indentación (Sección 4)
Se recomienda utilizar cuatro espacios para cada nivel de sangría en el código, en lugar de tabuladores, para mantener la legibilidad y consistencia en todos los entornos. Si se usan tabuladores, deben equivaler exactamente a 8 espacios.
```java
    public Dinero sumar(Dinero otraCantidad) {
        validarMoneda(otraCantidad);
        return new Dinero(this.cantidad.add(otraCantidad.cantidad).doubleValue(), moneda);
    }
```

#### Proporcionar acceso a variables de instancia y de clase (Sección 10.1)
No se deben declarar variables public sin una razón justificada. Lo ideal es que el acceso a las variables se haga mediante métodos getters y setters, para mantener la encapsulación, controlar el acceso y proteger el estado interno del objeto.
```java
    private final BigDecimal cantidad;
    private final String moneda;
    
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getMoneda() {
        return moneda;
    }
```

#### Returning Values (Sección 10.5.2)
Al devolver valores, se recomienda evitar estructuras innecesarias como if/else redundantes. Siempre que sea posible, utiliza retornos directos o expresiones ternarias para simplificar y reflejar claramente la intención del método.
```java
    public boolean esMayorQue(Dinero otraCantidad) {
        validarMoneda(otraCantidad);
        return this.cantidad.compareTo(otraCantidad.cantidad) > 0;
    }
```

#### Constantes (Sección 10.3)
Evita escribir valores numéricos directamente en el código ("números mágicos"). En su lugar, utiliza constantes con nombres descriptivos, declaradas como static final. Solo se permite el uso directo de -1, 0 y 1 en contextos como bucles for.
```java
private final BigDecimal cantidad;
```

#### Convenciones de nomenclatura (Sección 9)
Nombres claros, con estilo CamelCase para clases y lowerCamelCase para métodos.
```java
public class Dinero {
    public Dinero sumar(Dinero otraCantidad) { ... }
    public boolean esMayorQue(Dinero otraCantidad) { ... }
}
```

### `HorarioAtencion.java`
#### Comentarios (Sección 5)
Los comentarios deben añadir valor y no duplicar lo evidente. Deben ser claros, actualizados y evitar la redundancia para no quedar obsoletos.
```java
    // Convierte un nombre de día en español a DayOfWeek
    private DayOfWeek parseDia(String dia) {
        switch (dia.toLowerCase(Locale.ROOT)) {
            case "lunes": return DayOfWeek.MONDAY;
            case "martes": return DayOfWeek.TUESDAY;
            case "miercoles": return DayOfWeek.WEDNESDAY;
            case "jueves": return DayOfWeek.THURSDAY;
            case "viernes": return DayOfWeek.FRIDAY;
            case "sabado": return DayOfWeek.SATURDAY;
            case "domingo": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Día inválido: " + dia);
        }
    }
```

### `Restaurante`
#### Java Source Files (Sección 3.1)
Cada archivo .java debe contener una sola clase o interfaz pública, y debe estar ubicada al inicio del archivo. Si hay clases privadas auxiliares, pueden colocarse después de la clase pública principal.
```java
package Model.Dominio.Restaurantes;

public class Restaurante {
    ...
}
```

#### Placement (Sección 6.2)
Las variables de instancia están declaradas al inicio del bloque de clase, como lo indica la convención.
```java
private final int id;
private String nombre;
private String descripcion;
private String direccion;
private String telefono;
private TipoCocina tipoCocina;
private double calificacionPromedio;
private HorarioAtencion horarioAtencion;
private List<Plato> platos;
private Administrador admin;
```