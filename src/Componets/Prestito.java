package Componets;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="prestiti")
public class Prestito {

	@Id
	private int Key;
	
	@OneToMany
	private List<Utente> utente;
	
	@Enumerated(EnumType.STRING)
	private Elemento elemento;
	
	private LocalDate inizioPrestito;
	
	private LocalDate restituzionePrevista = inizioPrestito.plusDays(30);
	
	private LocalDate restituzioneEffettiva;

	public Prestito() {
		super();
	}

	
	

	public Prestito(int key, List<Utente> utente, Elemento elemento, LocalDate inizioPrestito,
			LocalDate restituzioneEffettiva) {
		super();
		Key = key;
		this.utente = utente;
		this.elemento = elemento;
		this.inizioPrestito = inizioPrestito;
		this.restituzioneEffettiva = restituzioneEffettiva;
	}


	

	public List<Utente> getUtente() {
		return utente;
	}




	public void setUtente(List<Utente> utente) {
		this.utente = utente;
	}




	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public LocalDate getInizioPrestito() {
		return inizioPrestito;
	}

	public void setInizioPrestito(LocalDate inizioPrestito) {
		this.inizioPrestito = inizioPrestito;
	}

	public LocalDate getRestituzionePrevista() {
		return restituzionePrevista;
	}

	public void setRestituzionePrevista(LocalDate restituzionePrevista) {
		this.restituzionePrevista = restituzionePrevista;
	}

	public LocalDate getRestituzioneEffettiva() {
		return restituzioneEffettiva;
	}

	public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
		this.restituzioneEffettiva = restituzioneEffettiva;
	}

	public int getKey() {
		return Key;
	}

	public void setKey(int key) {
		Key = key;
	}
	
	
	
	
	
}
