package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase que se usa para logear errores en un archivo
 */
public class LoggerError {

	private final static String logFilePath = "error_log.txt";

	/**
	 * Método que logea errores en el archivo del logFilePath. Ejemplo de uso dentro
	 * de un catch: LoggerError.log(e.getStackTrace(), e.getMessage())
	 * 
	 * @param stackTrace origen donde ocurrio el error
	 * @param message    mensaje de error
	 */
	public static void log(StackTraceElement[] stackTrace, String message) {
		Logger logger = Logger.getLogger("ErrorLogger");
		try {
			FileHandler fh = new FileHandler(logFilePath, true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.severe(" Stack: " + Arrays.toString(stackTrace) + " // Message: " + message + " @");

			fh.close();
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}
	}

	public static ArrayList<LibraryLog> readLog() {
		String dir = System.getProperty("user.dir"); // Me da el escritorio no se por que jkasjkas
		List<String> logsList = new ArrayList<>();
		ArrayList<LibraryLog> libraryLogs = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(logFilePath));
			StringBuffer content = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			String[] logs = content.toString().split(" @");
			br.close();
			logsList = Arrays.asList(logs);
			for (String log : logsList) {
				String[] fechaError = log.split("utils.LoggerError log");
				String[] stackMensaje = fechaError[1].split(" // ");
				LibraryLog lLog = new LibraryLog(fechaError[0], stackMensaje[0], stackMensaje[1]);
				libraryLogs.add(lLog);
			}
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}
		return libraryLogs;
	}
}
