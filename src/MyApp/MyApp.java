package MyApp;

import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Componets.ProdottoGenerico;
import Componets.ProdottoLibro;
import Componets.ProdottoRivista;
import Componets.Periodicita;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.EntityManager;



public class MyApp {
	String url = "jdbc:postgresql;//localhost:5432/Week3-D5?useSSL=false";
	String username = "postgres";
	String password = "SocietaEDD69";
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Week3D5RefactorLibreria");
    static EntityManager em = emf.createEntityManager();
	
	private static final Logger logger = LoggerFactory.getLogger(MyApp.class);


	private static final String FILE_PATH = "c:\\Users\\Dario Salvatore\\Desktop\\biblioteca.txt";


	private Map<String, ProdottoGenerico> archivio;

	public MyApp() {
		this.archivio = new HashMap<String, ProdottoGenerico>();	
	}

	public void aggiungi(ProdottoGenerico nuovoElemento) {
		archivio.put(nuovoElemento.getIsbn(), nuovoElemento);
		
		logger.info("Elemento aggiunto in archivio. ISBN: {} - Anno Pubblicazione: {}",nuovoElemento.getIsbn(), nuovoElemento.getAnnoPubblicazione());
	}
	
	public void rimuovi(String isbn) {
		ProdottoGenerico elementoRimosso = archivio.remove(isbn);
		if ( elementoRimosso != null)
		logger.info("Elemento rimosso dall'archivio. ISBN: {} - Anno Pubblicazione: {}",elementoRimosso.getIsbn(), elementoRimosso.getAnnoPubblicazione());

	}
	
	public ProdottoGenerico ricercaPerIsbn(String isbn){
		
		return archivio.get(isbn);
		
	}
	
	public List<ProdottoGenerico> ricercaPerAnnoPubblicazione(Integer annoPubblicazione){
		
		return archivio.values().stream().filter(elem -> annoPubblicazione.equals(elem.getAnnoPubblicazione()))
								  .collect(Collectors.toList()); 
		
	}
	
	
	public List<ProdottoLibro> ricercaPerAutore(String autore){
		
		return archivio.values().stream().filter(elem -> elem instanceof ProdottoLibro) 
								  .map(elem -> (ProdottoLibro) elem) 
								  .filter(elem -> autore.equals(elem.getAutore()))
								  .collect(Collectors.toList()); 
		
	}
	
	
	public void salvaCatalogo() throws IOException {

		String fileString = "";

		for (ProdottoGenerico elemento : archivio.values()) {
			if (fileString.length() != 0) {
				fileString += "#";
			}
			if (elemento instanceof ProdottoLibro) {
				fileString += ProdottoLibro.toStringFile((ProdottoLibro) elemento);
			} else if (elemento instanceof ProdottoRivista) {
				fileString += ProdottoRivista.toStringFile((ProdottoRivista) elemento);
			} 
		}

		File file = new File(FILE_PATH);
		FileUtils.writeStringToFile(file, fileString, "UTF-8");
		logger.info("Dati salvati correttamente sul file " + FILE_PATH);

	}

	public void caricaCatalogo() throws IOException {
		this.archivio.clear();

		File file = new File(FILE_PATH);

		String fileString = FileUtils.readFileToString(file, "UTF-8");

		List<String> splitElementiString = Arrays.asList(fileString.split("#"));

		for (String curString : splitElementiString) {
			ProdottoGenerico elemento = null;
			if ( curString.startsWith(ProdottoLibro.class.getSimpleName()) ) {
				elemento = ProdottoLibro.fromStringFile(curString);
			} else if ( curString.startsWith(ProdottoRivista.class.getSimpleName()) ) {
				elemento= ProdottoRivista.fromStringFile(curString);
			}
			this.archivio.put(elemento.getIsbn(), elemento);
			
		}
		logger.info("Dati caricati correttamente dal file " + FILE_PATH);

	}
	
	public static void main(String[] args) {
		MyApp catalogo = new MyApp();
		
		
		
		ProdottoLibro l1 = new ProdottoLibro("11111","Guerra e Pace", 1891, 1278, "Lev Tolstoj", "Romanzo");
		ProdottoRivista r1 = new ProdottoRivista("11112","Times", 2021, 178, Periodicita.MENSILE);
		
		catalogo.aggiungi(r1);
		catalogo.aggiungi(l1);
		
		try {
			catalogo.salvaCatalogo();
			
			catalogo.caricaCatalogo();
			
			List<ProdottoLibro> ricercaPerAutore = catalogo.ricercaPerAutore("Lev Tolstoj");
			
			ricercaPerAutore.forEach(elem -> System.out.println("Titolo: " + elem.getTitolo()));
			
		} catch (IOException e) {
			logger.error("Errore durante la lettura/scrittura",e);
		}


	}
	
//	 public class Jpa {
//		private static final EntityManagerFactory entityManagerFactory ;
//		static {
//		    try {
//		        entityManagerFactory = Persistence.createEntityManagerFactory("Week3D5RefactorLibreria");
//		    } catch (Throwable ex) {
//		        System.err.println("Initial EntityManagerFactory creation failed." + ex);
//		        throw new ExceptionInInitializerError(ex);
//		    }
//		}
//
//		public static EntityManagerFactory getEntityManagerFactory() {
//		    return entityManagerFactory;
//		}
//		}
	

}
