package base;
import java.io.IOException;

import net.mips.compiler.*;
public class main {

	/* public static void main(String[] args) throws Exception {
		Scanner s=new Scanner("C:\\Users\\salah\\eclipse-workspace\\Mips1\\prog.mips1");
		s.initMotsCles();
		s.lireCar();

		while(s.getCarCour()!='\0') {
			s.symbSuiv();
			 System.out.println(s.getSymbCour().getToken().toString() + " : " + s.getSymbCour().getNom());
		}
	}
*/
	public static void main(String[] args) throws Exception {
		Parser s=new Parser("C:\\Users\\salah\\eclipse-workspace\\Mips1\\prog.mips1");
		s.getScanner().initMotsCles();
		s.getScanner().lireCar();
		s.getScanner().symbSuiv();
		s.program();
		}
	}

