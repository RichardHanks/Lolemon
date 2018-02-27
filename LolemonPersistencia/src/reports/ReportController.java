package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lolemon.consultas.Consultas;
import lolemon.persistencia.modelo.Personaje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportController {

	private static CampeonesCollection personajes = new CampeonesCollection();
	private static Consultas con;

	public static List<Personaje> loadPersonajes() {
		fillPersonajes();
		return personajes.getPersonajes();
	}

	@SuppressWarnings("unchecked")
	private static void fillPersonajes() {
		con= new Consultas();
		ArrayList<Personaje> pList = (ArrayList<Personaje>) con.getEm().createQuery("select p from Personaje p")
				.getResultList();
		for (Personaje per : pList) {
			personajes.addPersonaje(per);
		}

	}

	public static void makeReport() {

		try {
			InputStream is = ReportController.class.getResourceAsStream("Blank_A4.jasper");

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("REPORT", "PERSONAJES LOLEMON!");

			JasperPrint jp = JasperFillManager.fillReport(is, parametros,new JRBeanCollectionDataSource(loadPersonajes()));

			JasperExportManager.exportReportToPdfFile(jp, "informe.pdf");
			Desktop.getDesktop().open(new File("informe.pdf"));
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
