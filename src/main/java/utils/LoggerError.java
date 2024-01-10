package utils;

import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase que se usa para logear errores en un archivo
 */
public class LoggerError {
	
	private final static String logFilePath = "error_log.txt";
	
	/**
	 * Método que logea errores en el archivo del logFilePath. Ejemplo de uso
	 * dentro de un catch: LoggerError.log(e.getStackTrace(), e.getMessage())
	 * 
	 * @param stackTrace origen donde ocurrio el error
	 * @param message mensaje de error
	 */
	public static void log(StackTraceElement[] stackTrace, String message) {
		Logger logger = Logger.getLogger("ErrorLogger");
		
		try {
			FileHandler fh = new FileHandler(logFilePath, true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			logger.severe("Stack: " + Arrays.toString(stackTrace) + " Error: " + message + "\n");
			
			fh.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
