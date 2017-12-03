/*
 * Klasse RationalNumber, Mutterklasse Number, implementiert Comparable
 */
public class RationalNumber extends java.lang.Number implements java.lang.Comparable<RationalNumber>{
	private static final long serialVersionUID = 1;
	private int zaehler;	
	private int nenner;
	
	/*
	 *	Default-Konstruktor: erzeugt eine neue Bruchzahl 
	 *	mit den Werten zaehler = 0 und nenner = 1.
	 */
	public RationalNumber(){
		zaehler = 0;
		nenner = 1;
	}
	
	/*
	 *	Erzeugt eine neue Bruchzahl mit den übergebenen Werten. 
	 *	Falls für nenner der Wert 0 übergeben wird, soll der Wert des 
	 *	Default-Konstruktors verwendet werden. Zähler und Nenner übernehmen
	 */
	public RationalNumber(int zaehler, int nenner){
		this();
		if (nenner != 0){
			this.zaehler = zaehler;
			this.nenner = nenner;
			kuerzen();
		}
	}
	
	/*
	 * Erzeugt aus String, Zaehler und Nenner
	 */
	public RationalNumber(String input){
		this();
		char[] bz = input.toCharArray();
		boolean negativ = false;
		boolean EingabeKorrekt = true;
		boolean schreibezaehler = true;
		char start = 0;
		String snenner = "";
		String szaehler = "";
		
		//ist die Zahl negativ? Speichere den Wert
		if (bz[0] == '-'){
			negativ = true;
			start = 1;
		}
		
		//Lese die Zahl komplett aus und speichere in zwei Strings (Zaehler und Nenner)
		for (int index = start; index < bz.length; index++){
			//ist die Eingabe korrekt?
			if (EingabeKorrekt == true){
				//handelt es sich um eine Zahl?
				if (isteinezahl(bz[index])==true){
					if (schreibezaehler == true){
						szaehler = szaehler + bz[index];
					}else{
						snenner = snenner + bz[index];
					}
				//oder kommt schon die nächste zahl?	
				}else if(bz[index] == '/'){
					//Dieser Fall darf nur einmal auftreten
					if (schreibezaehler == false){
						EingabeKorrekt = false;
					}
					schreibezaehler = false;
				//oder ist die Eingabe falsch?
				}else{
					EingabeKorrekt = false;
				}
			}
		}
		
		//Überprüfe gespeicherte Daten
		if (szaehler == ""){
			EingabeKorrekt = false;
		}
		if (schreibezaehler == false){
			if (snenner == ""){
				EingabeKorrekt = false;
			}
		}
		
		//Speichere zaehler und nenner
		if (EingabeKorrekt == true){
			zaehler = Integer.parseInt(szaehler);
			if (negativ == true){
				zaehler *= (-1);
			}
			if (schreibezaehler == false){
				nenner = Integer.parseInt(snenner);
			}else{
				nenner = 1;
			}
			kuerzen();
		}
	}
	
	/*
	 * Überprüft ob das Array eine Zahl beinhaltet
	 * Für die Methode RationalNumbers(String)
	 */
	public boolean isteinezahl(char czahl){
		boolean zahl = false;
		switch(czahl){
		case ('0'): zahl = true; break;
		case ('1'): zahl = true; break;
		case ('2'): zahl = true; break;
		case ('3'): zahl = true; break;
		case ('4'): zahl = true; break;
		case ('5'): zahl = true; break;
		case ('6'): zahl = true; break;
		case ('7'): zahl = true; break;
		case ('8'): zahl = true; break;
		case ('9'): zahl = true; break;
		default: break;		
		}	
		return zahl;
	}
	
	/*
	 * Addiert zwei Bruchzahlen, verändert diese nicht und gibt das Ergebnis zurück
	 */
	public RationalNumber add(RationalNumber other){		
		RationalNumber othersec = new RationalNumber(other.zaehler, other.nenner);
		int zszaehler = zaehler;
		
		zszaehler *= othersec.nenner;
		othersec.zaehler *= nenner;		
		othersec.nenner *= nenner;
		othersec.zaehler += zszaehler;
		
		return othersec;		
	}

	/*
	 * Subtrahiert zwei Bruchzahlen, verändert diese nicht und gibt das Ergebnis zurück
	 */	
	public RationalNumber sub(RationalNumber other){
		RationalNumber othersec = new RationalNumber(other.zaehler, other.nenner);
		int zszaehler = zaehler;
		
		zszaehler *= othersec.nenner;
		othersec.zaehler *= nenner;		
		othersec.nenner *= nenner;
		othersec.zaehler = zszaehler - othersec.zaehler;
		
		return othersec;
	}

	/*
	 * Multipliziert zwei Bruchzahlen, verändert diese nicht und gibt das Ergebnis zurück
	 */	
	public RationalNumber multiply(RationalNumber other){
		RationalNumber othersec = new RationalNumber(other.zaehler, other.nenner);
		
		othersec.zaehler *= zaehler;
		othersec.nenner *= nenner;
		
		return othersec;
	}
	
	/*
	 * Teilt zwei Bruchzahlen, verändert diese nicht und gibt das Ergebnis zurück
	 */	
	public RationalNumber divide(RationalNumber other){
		RationalNumber othersec = new RationalNumber(other.zaehler, other.nenner);
		
		othersec.zaehler *= nenner;
		othersec.nenner *= zaehler;
		
		return othersec;
	}
	
	/*
	 *	Kürzt diese Bruchzahl, so dass Zähler und Nenner keinen 
	 *	gemeinsamen Teiler haben. Außerdem sollen die Vorzeichen 
	 *	so bereinigt werden, dass der Nenner immer positiv ist. 
	 */
	private void kuerzen(){
		int czaehler = zaehler;
		int cnenner = nenner;
		int kuerzer = 1;	
		boolean vorzeichen = false; 	// positiv
		
		//Nenner bereinigen
		if (nenner < 0){
			zaehler = zaehler * -1;
			nenner = nenner * -1;
			cnenner = nenner;
		}
		
		//Für den folgenden Algorithmus müssen Nenner und Zähler positiv sein
		if (zaehler < 0){
			vorzeichen = true;
			zaehler = zaehler * -1;
		}
		czaehler = zaehler;
		
		// euklidische Algorithmus
		// (Kürzalgorithmus)
		if (czaehler == 0){
			kuerzer = cnenner;
		}else{
			while (cnenner != 0){
				if (czaehler > cnenner){
					czaehler = czaehler - cnenner;
				}else{
					cnenner = cnenner - czaehler;
				}
			}
		}
		kuerzer = czaehler;	
		
		//Kürzen
		zaehler = zaehler / kuerzer;
		nenner = nenner / kuerzer;
		
		//Vorzeichen korrigieren
		if (vorzeichen == true){
			zaehler = zaehler * -1;
		}
	}
/*
 * vergleicht zwei Bruchzahlen auf 3 Fälle
 *		Fall 1: Beide Zahlen sind gleichgroß,	Rückgabewert =  0		
 * 		Fall 2: 1 Zahl ist größer, 				Rückgabewert =  1
 * 		Fall 3: 2 Zahl ist größer,				Rückgabewert = -1
 */
	@Override
	public int compareTo(RationalNumber zahl) {
		int ergebnis = 10;
		if (zaehler*zahl.nenner == zahl.zaehler*nenner){
			ergebnis = 0;
		}else if(zaehler*zahl.nenner > zahl.zaehler*nenner){
			ergebnis = 1;
		}else if(zaehler*zahl.nenner < zahl.zaehler*nenner){
			ergebnis = -1;
		}
		return ergebnis;
	}
	
	/*
	 * Überprüft auf Gleichheit und gibt true/false zurück
	 */
	public boolean equals(RationalNumber zahl){
		boolean equal = false;
		if (this.compareTo(zahl)==0){
			equal = true;
		}		
		return equal;
	}
	
	/*
	 * macht aus der übergebenen Zahl einen String und gibt diesen zurück
	 */
	public String toString(){
		String ausgabe = new String();
		ausgabe = zaehler + " / " + nenner;
		return ausgabe;
	}
	
	
	/*
	 * folgende Methoden geben die aktuelle Bruchzahl 
	 * als eine Zahl in verschiedenen Typen zurück
	 */
	@Override
	public double doubleValue() {
		return zaehler/nenner;
	}

	@Override
	public float floatValue() {
		return zaehler/nenner;
	}

	@Override
	public int intValue() {
		double a = (zaehler/nenner)-((int)(zaehler/nenner));
		if (a >= 0.5){
			return ((int)((zaehler/nenner)+1));
		}
		else{
			return ((int)(zaehler/nenner));
		}		
	}

	@Override
	public long longValue() {
		double a = (zaehler/nenner)-((long)(zaehler/nenner));
		if (a >= 0.5){
			return ((long)((zaehler/nenner)+1));
		}
		else{
			return ((long)(zaehler/nenner));
		}	
	}
}