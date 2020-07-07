package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ricerca {
	
	private List<String> soluzione ;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso.
	 * @param parola parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		this.soluzione=new ArrayList<>();
		//caso iniziale
		parola=parola.toUpperCase();
		List<Character>disponibili=new ArrayList<>();
		for(int i=0;i<parola.length();i++) {
			disponibili.add(parola.charAt(i));
		}
		//avvio ricorsione
		this.cerca("", 0, disponibili);
		
		return this.soluzione;
	}
	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma.
	 * 
	 * @param parziale parte iniziale dell'anagramma costruito finora
	 * @param livello livello della ricorsione, sempre uguale a parziale.length()
	 * @param disponibili insieme delle lettere non ancora utilizzate --> meglio list di set dato che
	 * il set non mi permetterebbe di insierire due lettere uguali
	 */
	
	
	private void cerca( String parziale, int livello, List<Character>disponibili) {
		//caso terminale
		if(disponibili.size()==0) {//livello=parola.leght
			//controllare che soluzione parziale sia nel dizionario if(dizionario.contains(parziale)) --> la salvo altrimenti la elimino
			this.soluzione.add(parziale);	
		}
		
		// caso generale
		//provare ad aggiungere a parziale tutti i caratterei presenti tra i disponibili
		for(Character ch: disponibili) {
			// non sto toccando attivamente la stringa parziale per cui non devo fare backtracking
			String tentativo =parziale+ch;
			// non genero parole che non esistono
			// if( nel dizionario esistono delle parole che iniziano con tentativo?) --> se si chiamo la funzione ricorsiva, altrimenti mi fermo
			//la creo perchè sto iterando su disponibili e non posso modificarla
			List<Character>rimanenti= new ArrayList<>(disponibili);
			rimanenti.remove(ch);
			cerca(tentativo, livello+1,rimanenti);
			
		}	
	}
	
	
	// per non ripetere la parola uguale 
	//nel caso terminale if(parziale non è presente nella soluzione allora l'aggiungo)
	// altrimenti vincolo l'ordine

}

/*
Dato di partenza: parola da anagrammare, di lunghezza N
Soluzione parziale: una parte dell'anagramma già costruito (i primi caratteri).
Livello: numero di lettere di cui è composta la soluzione parziale.
Soluzione finale: soluzione di lunghezza N -> caso terminale
Caso terminale: salvare la soluzione trovate per poterle restituire tutte.
Generazione delle nuove soluzioni: provare a aggiungere una lettera, scegliendola
tra quelle che non sono ancora state utilizzate (nella soluzione parziale).
*/