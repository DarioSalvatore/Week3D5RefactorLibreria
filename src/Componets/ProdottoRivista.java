package Componets;

public class ProdottoRivista extends ProdottoGenerico {
	private Periodicita periodicita;

	public ProdottoRivista(String isbn, String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
		super(isbn, titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}


	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}
	
	public static String toStringFile(ProdottoRivista rivista) {
		return ProdottoRivista.class.getSimpleName()  
				+ "@" + rivista.isbn
				+ "@" + rivista.titolo
				+ "@" + rivista.annoPubblicazione
				+ "@" + rivista.numeroPagine
				+ "@" + rivista.periodicita;
	}

	public static ProdottoRivista fromStringFile(String stringFile) {
		String[] split = stringFile.split("@");
		Periodicita periodicita = Periodicita.valueOf(split[5]);
		
		return new ProdottoRivista(split[1], split[2], Integer.valueOf(split[3]), Integer.valueOf(split[4]), periodicita);
	}
}