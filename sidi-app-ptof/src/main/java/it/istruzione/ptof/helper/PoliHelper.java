package it.istruzione.ptof.helper;

import java.util.ArrayList;
import java.util.List;


public class PoliHelper {

	public static List<String> poloRegionaleToCodiceRegioneList(String codicePoloRegionale) {

		List<String> out = new ArrayList<>();
		
		if (codicePoloRegionale.equals("YR01")){
			out.add("AB");
		}else if (codicePoloRegionale.equals("YR02")){
			out.add("BA");
		}else if (codicePoloRegionale.equals("YR03")){
			out.add("CL");
		}else if (codicePoloRegionale.equals("YR04")){
			out.add("CA");
		}else if (codicePoloRegionale.equals("YR05")){
			out.add("EM");
		}else if (codicePoloRegionale.equals("YR06")){
			out.add("FR");
		}else if (codicePoloRegionale.equals("YR07")){
			out.add("LA");
		}else if (codicePoloRegionale.equals("YR08")){
			out.add("LI");
		}else if (codicePoloRegionale.equals("YR09")){
			out.add("LO");
		}else if (codicePoloRegionale.equals("YR10")){
			out.add("MA");
		}else if (codicePoloRegionale.equals("YR11")){
			out.add("MO");
		}else if (codicePoloRegionale.equals("YR12")){
			out.add("PI");
		}else if (codicePoloRegionale.equals("YR13")){
			out.add("PU");
		}else if (codicePoloRegionale.equals("YR14")){
			out.add("SA");
		}else if (codicePoloRegionale.equals("YR15")){
			out.add("SI");
		}else if (codicePoloRegionale.equals("YR16")){
			out.add("TO");
		}else if (codicePoloRegionale.equals("YR18")){
			out.add("UM");
		}else if (codicePoloRegionale.equals("YR19")){
			out.add("TR");
			out.add("VE");
		}
		
		return out;
	}


}