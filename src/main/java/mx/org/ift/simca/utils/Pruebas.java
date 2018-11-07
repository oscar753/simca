package mx.org.ift.simca.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author KODE
 *
 */
public class Pruebas {
	
	static Date date = null;

	public static void main(String args[]) {
		//System.out.println("VigenciaIni: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		System.out.println("VigenciaIni: " + (date != null
				? new SimpleDateFormat("dd/MM/yyyy").format(date)
				: null));
		
	}
}
