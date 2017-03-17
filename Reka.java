import java.util.Scanner;


/**
 * 
 * @author Marketa Jedlickova
 * 
 * Program na zjisteni poctu moznostosti prejezdu pres reku pomoci lodky
 *
 */
public class Reka {
	public static Scanner sc = new Scanner(System.in);
	public static int mama = 1;
	public static int tata = 2;
	public static int deti = 3;
	public static uzel[] OPEN= new uzel[7];
	public static uzel[] CLOSE= new uzel[5];
	public static int indexO = 0;
	public static int indexC = 0;

	/**
	 * Metoda slouzici pro pouziti operatoru 1
	 */
	public static void pouzijO1(uzel S, int index){
		uzel novy = new uzel(S);
		novy.setCena(S.getCena()+1);
		novy.setLDosp(S.getLDosp()-index);
		novy.setPDosp(S.getPDosp()+index);

		int[] pomTab = novy.getOperace();
		pomTab[novy.getIndex()] = 1;
		novy.setOperace(pomTab);
		novy.setIndex(S.getIndex()+1);

		OPEN[indexO]=novy;
		indexO++;
	}

	/**
	 * Metoda slouzici pro pouziti operatoru 2
	 */
	public static void pouzijO2(uzel S, int index){
		uzel novy = new uzel(S);
		novy.setCena(S.getCena()+1);
		novy.setLDeti(S.getLDeti()-index);
		novy.setPDeti(S.getPDeti()+index);

		int[] pomTab = S.getOperace();
		pomTab[S.getIndex()] = 2;
		novy.setOperace(pomTab);
		novy.setIndex(S.getIndex()+1);

		OPEN[indexO]=novy;
		indexO++;
	}

	public static void otevri(uzel S){
		if(S.getLDosp()==3){
			pouzijO1(S,1);
			pouzijO1(S,2);
		}else if(S.getLDosp()==2){
			pouzijO1(S,2);
		}else if(S.getLDosp()==1){
			pouzijO1(S,1);
		}

		if(S.getLDeti()==3){
			pouzijO2(S,3);
		}
	}

	public static void prohledavaniDoSirky(){		
		//2. pokud je OPEN prazdny KONEC 
		if(OPEN[0]==null){
			System.out.println("Uloha nemá øešení");
			System.exit(0);
		}else{
			//3. v OPEN najdi minima
			//3. a) zjisti jestli mezi OPEN neni CIL
			int k=0;
			while(OPEN[k]!=null){
				if(OPEN[k].getLDeti()==0 && OPEN[k].getLDosp()==0){
					System.out.println("Poøadí použitých operátoru pro nejkratší øešení: ");
					for(int i = 0;i<OPEN[k].getOperace().length;i++){	
						if(OPEN[k].getOperace()[i] != 0){
							System.out.println("operátor "+OPEN[k].getOperace()[i]+" ");
						}	
					}
					System.exit(0);
				}
				k++;
			}
		}


		//3. b) pokud ne vyber jeden a expanduj
		otevri(OPEN[0]);

		CLOSE[indexC]=OPEN[0];
		indexC++;
		indexO--;
		for(int l=0;l<OPEN.length-1;l++){ 
			OPEN[l]=OPEN[l+1];	
		}


		//5. prohledej strom a pokud se dva shodují vyškrtni ten s vyšším ohodnocením a s ním i jeho následníky
		vyskrtani();

		// jdi do bodu 2.
		prohledavaniDoSirky(); // rekurzni volani
	}

	public static void vyskrtani(){
		for(int i=0;i<OPEN.length;i++){ 
			for(int j=0;j<OPEN.length;j++){ 
				if(i!=j && OPEN[i]!=null && OPEN[j]!=null){
					if(OPEN[i].getLDeti()==OPEN[j].getLDeti() && OPEN[i].getLDosp()==OPEN[j].getLDosp()){
						if(OPEN[i].getCena()>OPEN[j].getCena()){
							indexO--;
							for(int l=i;l<OPEN.length;l++){ 
								OPEN[l]=OPEN[l+1];
							}
						}else{
							indexO--;
							for(int l=j;l<OPEN.length-1;l++){ 
								OPEN[l]=OPEN[l+1];
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args){
		//1. CLOSED(-) automaticky a OPEN(s0) nastavime 
		int pocetDospelych = mama + tata;
		uzel s0 = new uzel(deti, pocetDospelych);
		OPEN[indexO]= s0;
		indexO++;
		//2.-4.
		prohledavaniDoSirky();
	}
}
