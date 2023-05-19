package Componets;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utente {
 private String nome;
 private String cognome;
 private LocalDate DataDiNascita;
 @Id
 private int numeroDiTessera;
public Utente() {
	super();
}
public Utente(String nome, String cognome, LocalDate dataDiNascita) {
	super();
	this.nome = nome;
	this.cognome = cognome;
	DataDiNascita = dataDiNascita;
	
}
public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public LocalDate getDataDiNascita() {
	return DataDiNascita;
}

public void setDataDiNascita(LocalDate dataDiNascita) {
	DataDiNascita = dataDiNascita;
}

public int getNumeroDiTessera() {
	return numeroDiTessera;
}

public void setNumeroDiTessera(int numeroDiTessera) {
	this.numeroDiTessera = numeroDiTessera;
}

@Override
public String toString() {
	return "Utente [nome=" + nome + ", cognome=" + cognome + ", DataDiNascita=" + DataDiNascita + ", numeroDiTessera="
			+ numeroDiTessera + "]";
}
 
	
 
 
}
