package es.dentasmart.dao;

/**
 *
 * @author seridan
 */
public class DAOException  extends Exception{

    public DAOException(String string) {
        super(string);
    }

    public DAOException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DAOException(Throwable thrwbl) {
        super(thrwbl);
    }
    
    
    
    
}
