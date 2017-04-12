package ci.prosuma.prosumagpv.metier.util.app;



public class Ean13Generator {
	
	private Ean13Generator(){}
	
	
	public static String generateEAN13(String seq){
		String o = seq+"";

		int seq1 = Integer.parseInt(o.substring(0, 1));
		int seq2 = Integer.parseInt(o.substring(1, 2));
		int seq3 = Integer.parseInt(o.substring(2, 3));
		int seq4 = Integer.parseInt(o.substring(3, 4));
		int seq5 = Integer.parseInt(o.substring(4,5));
		int seq6 = Integer.parseInt(o.substring(5, 6));
		int seq7 = Integer.parseInt(o.substring(6, 7));
		int seq8 = Integer.parseInt(o.substring(7, 8));
		int seq9 = Integer.parseInt(o.substring(8, 9));
		int seq10 = Integer.parseInt(o.substring(9, 10));
		int seq11 = Integer.parseInt(o.substring(10, 11));
		int seq12 = Integer.parseInt(o.substring(11, 12));
		
		
		int[]  gc = new int[]{seq1,seq2,seq3,seq4,seq5,seq6,seq7,seq8,seq9,seq10,seq11,seq12};
	
		boolean pair = false;
		int result = 0;
		for(int i: gc){
			if(pair){
				result = result + (i*3);
				pair = false;
			}else{
				result = result + (i*1);
				pair = true;
			}
		}
		
		int reste = result %10;
		int clef = 0;
		if(reste != 0){
			clef = 10 - reste;
		}
		
		return clef+"";
		
	}

}
