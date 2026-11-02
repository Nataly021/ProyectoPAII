package Daoo;

import modelo.ConfiguracionSistema;
import java.util.List;

public interface ConfiguracionDAO {
    boolean guardar(ConfiguracionSistema c);
    List<ConfiguracionSistema> listar();
    boolean actualizar(ConfiguracionSistema c);
    boolean eliminar(int id);
}