
public class uzel {
	int LDeti = 0;
	int LDosp = 0;
	int PDeti = 0;
	int PDosp = 0;
	int cena = 0;
	int[] operace = new int[3]; // vím, že délka stromu je 3
	int index = 0;

	uzel(int LDeti, int LDosp){
		this.LDeti = LDeti;	
		this.LDosp = LDosp;
	}
	
	uzel(){	
	}
	
	uzel(uzel novy){	
		this.LDeti =novy.getLDeti();
		this.LDosp = novy.getLDosp();
		this.PDeti = novy.getPDeti();
		this.PDosp = novy.getPDosp();
		this.cena = novy.getCena();
		for(int i =0 ;i<this.operace.length;i++){
			this.operace[i] = novy.getOperace()[i];
		}
		this.index = novy.getIndex();
	}

	void setLDeti(int LDeti){
		this.LDeti = LDeti;
	}

	int getLDeti(){
		return this.LDeti;
	}
	
	void setLDosp(int LDosp){
		this.LDosp = LDosp;
	}

	int getLDosp(){
		return this.LDosp;
	}
	
	void setPDeti(int PDeti){
		this.PDeti = PDeti;
	}

	int getPDeti(){
		return this.PDeti;
	}
	
	void setPDosp(int PDosp){
		this.PDosp = PDosp;
	}

	int getPDosp(){
		return this.PDosp;
	}
	
	void setOperace(int[] operace){
		this.operace = operace;
	}

	int[] getOperace(){
		return this.operace;
	}
	
	void setCena(int cena){
		this.cena = cena;
	}

	int getCena(){
		return this.cena;
	}
	
	void setIndex(int index){
		this.index = index;
	}

	int getIndex(){
		return this.index;
	}
}
