
package es.dentasmart.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author seridan
 * @param <T> representa al objeto de la clase que queremos 
 * guardar en la B.D.  p.e. Paciente
 * @param <K> se refiere al tipo de datos de la clave primaria
 * (Id) que se utiliza para obtener un solo registro
 */
public interface DAO<T, K>{
    
    void insertar(T p) throws DAOException;
    
    void modificar(T p) throws DAOException;
    
    void eliminar(T p) throws DAOException;
    
    ObservableList<T>obtenerTodos() throws DAOException;
    
    T obtenerUno(K id) throws DAOException;
}
