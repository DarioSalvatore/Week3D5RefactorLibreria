package Componets;

import javax.persistence.Entity;

@Entity
public class ProdottoLibro extends ProdottoGenerico {
	
	private String autore;
	private String genere;
	
	
	
	public ProdottoLibro() {
		super();
	}

	public ProdottoLibro(String isbn, String titolo, Integer annoPubblicazione, Integer numeroPagine, String autore, String genere) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	
	
	public static String toStringFile(ProdottoLibro libro) {
		return ProdottoLibro.class.getSimpleName()  
				+ "@" + libro.isbn
				+ "@" + libro.titolo
				+ "@" + libro.annoPubblicazione
				+ "@" + libro.numeroPagine
				+ "@" + libro.autore
				+ "@" + libro.genere;
	}

	public static ProdottoLibro fromStringFile(String stringFile) {
		String[] split = stringFile.split("@");
		
		return new ProdottoLibro(split[1], split[2], Integer.valueOf(split[3]), Integer.valueOf(split[4]), split[5], split[6]);
	}

}
