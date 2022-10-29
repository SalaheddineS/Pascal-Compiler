package net.mips.compiler;

import java.io.IOException;

public class Parser {
Scanner scanner;

public Parser(String filePath) throws Exception {
	this.scanner=new Scanner(filePath);


}

public void testeAccept(Tokens token, CodesErr codeErr) throws  ErreurLexicale, IOException {
    if (this.scanner.getSymbCour().getToken() == token) {
        System.out.println("Verified" + token + " " + this.scanner.getSymbCour().getNom());
        if (this.scanner.getCarCour() != 0) this.scanner.symbSuiv();
    }
    else throw new ErreurLexicale(codeErr);
}

public void program() throws ErreurLexicale, IOException {

	 
	testeAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
	testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	testeAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
	block();
	testeAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
}

public void block() throws ErreurLexicale, IOException {
	consts();
	vars();
	insts();
}

public void consts() throws ErreurLexicale, IOException {

	if(scanner.getSymbCour().getToken().equals(Tokens.CONST_TOKEN)) {
	testeAccept(Tokens.CONST_TOKEN,CodesErr.CONST_ERR);
	testeAccept(Tokens.ID_TOKEN,CodesErr.CONST_ERR);
	testeAccept(Tokens.EG_TOKEN,CodesErr.CONST_ERR);
	testeAccept(Tokens.NUM_TOKEN,CodesErr.CONST_ERR);
	testeAccept(Tokens.PVIR_TOKEN,CodesErr.CONST_ERR);
	while(scanner.getSymbCour().getToken().equals(Tokens.ID_TOKEN)) {
		testeAccept(Tokens.ID_TOKEN,CodesErr.CONST_ERR);
		testeAccept(Tokens.EG_TOKEN,CodesErr.CONST_ERR);
		testeAccept(Tokens.NUM_TOKEN,CodesErr.CONST_ERR);
		testeAccept(Tokens.PVIR_TOKEN,CodesErr.CONST_ERR);
	}}
	}

public void vars() throws ErreurLexicale, IOException {
	if(scanner.getSymbCour().getToken().equals(Tokens.VAR_TOKEN)) {
	testeAccept(Tokens.VAR_TOKEN,CodesErr.VAR_ERR);
	testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
		testeAccept(Tokens.VIR_TOKEN,CodesErr.ID_ERR);
		testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	}
	testeAccept(Tokens.PVIR_TOKEN,CodesErr.ID_ERR);}
		

}

public void insts() throws ErreurLexicale, IOException {
	testeAccept(Tokens.BEGIN_TOKEN,CodesErr.VAR_ERR);
	inst();
	while(scanner.getSymbCour().getToken()==Tokens.PVIR_TOKEN) {
		testeAccept(Tokens.PVIR_TOKEN,CodesErr.VAR_ERR);
        inst();
	}
	testeAccept(Tokens.END_TOKEN,CodesErr.VAR_ERR);
}

public void inst() throws ErreurLexicale, IOException { //reference a LL1 , on conditionne en trouvant le first 
	if(scanner.getSymbCour().getToken().equals(Tokens.BEGIN_TOKEN)) {insts();}
	else if(scanner.getSymbCour().getToken().equals(Tokens.ID_TOKEN)) {affec();}
	else if(scanner.getSymbCour().getToken().equals(Tokens.IF_TOKEN)) {si();}
	else if(scanner.getSymbCour().getToken().equals(Tokens.WHILE_TOKEN)) {tantque();}
	else if(scanner.getSymbCour().getToken().equals(Tokens.WRITE_TOKEN)) {ecrire();}
	else if(scanner.getSymbCour().getToken().equals(Tokens.READ_TOKEN)) {lire();}
}

public void affec() throws ErreurLexicale, IOException { //huwa := machi egal 
	testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	testeAccept(Tokens.AFFEC_TOKEN,CodesErr.ID_ERR);
	expr();
	
	
}
public void si() throws ErreurLexicale, IOException {
	testeAccept(Tokens.IF_TOKEN,CodesErr.VAR_ERR);
	cond();
	testeAccept(Tokens.THEN_TOKEN,CodesErr.VAR_ERR);
	inst();
}

public void tantque() throws ErreurLexicale, IOException {
	testeAccept(Tokens.WHILE_TOKEN,CodesErr.VAR_ERR);
	cond();
	testeAccept(Tokens.DO_TOKEN,CodesErr.VAR_ERR);
	inst();
}

public void ecrire() throws ErreurLexicale, IOException {
	testeAccept(Tokens.WRITE_TOKEN,CodesErr.VAR_ERR);
	testeAccept(Tokens.PARG_TOKEN,CodesErr.VAR_ERR);
	expr();
	while(scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)) {
		testeAccept(Tokens.VIR_TOKEN,CodesErr.VAR_ERR);
		expr();
	}
	testeAccept(Tokens.PARD_TOKEN,CodesErr.VAR_ERR);
}

public void lire() throws ErreurLexicale, IOException {
	testeAccept(Tokens.READ_TOKEN,CodesErr.VAR_ERR);
	testeAccept(Tokens.PARG_TOKEN,CodesErr.ID_ERR);
	testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	while(scanner.getSymbCour().getToken().equals(Tokens.VIR_TOKEN)) {
		testeAccept(Tokens.VIR_TOKEN,CodesErr.VAR_ERR);
		testeAccept(Tokens.ID_TOKEN,CodesErr.VAR_ERR);
	}
	testeAccept(Tokens.PARD_TOKEN,CodesErr.VAR_ERR);
}

public void cond() throws ErreurLexicale, IOException {
	expr();
	relop();
	expr();
}

public void expr() throws ErreurLexicale, IOException { //a verifier
 term();
 while(scanner.getSymbCour().getToken().equals(Tokens.PLUS_TOKEN)||scanner.getSymbCour().getToken().equals(Tokens.MOINS_TOKEN)) {
	 adop();
	 term();
 }

 }


public void term() throws ErreurLexicale, IOException {// KEMEL HNA 
	fact();
	 while(scanner.getSymbCour().getToken().equals(Tokens.MUL_TOKEN)||scanner.getSymbCour().getToken().equals(Tokens.DIV_TOKEN)) {
		 mulop();
		 fact();
	 }
	
}

public void fact() throws ErreurLexicale, IOException {// KEMEL HNA 
	if(scanner.getSymbCour().getToken().equals(Tokens.ID_TOKEN)) {testeAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);}
	else if (scanner.getSymbCour().getToken().equals(Tokens.NUM_TOKEN)) {testeAccept(Tokens.NUM_TOKEN,CodesErr.ID_ERR);}
	else if (scanner.getSymbCour().getToken().equals(Tokens.PARG_TOKEN)) {
		testeAccept(Tokens.PARG_TOKEN,CodesErr.ID_ERR);
		expr();
		testeAccept(Tokens.PARD_TOKEN,CodesErr.ID_ERR);}
}

public void relop() throws ErreurLexicale, IOException {
	if(scanner.getSymbCour().getToken().equals(Tokens.EG_TOKEN)) {testeAccept(Tokens.EG_TOKEN,CodesErr.NUM_ERR);}
	else if(scanner.getSymbCour().getToken().equals(Tokens.DIFF_TOKEN)) {testeAccept(Tokens.DIFF_TOKEN,CodesErr.NUM_ERR);}
	else if(scanner.getSymbCour().getToken().equals(Tokens.INF_TOKEN)) {testeAccept(Tokens.INF_TOKEN,CodesErr.NUM_ERR);}
	else if(scanner.getSymbCour().getToken().equals(Tokens.SUP_TOKEN)) {testeAccept(Tokens.SUP_TOKEN,CodesErr.NUM_ERR);}
	else if(scanner.getSymbCour().getToken().equals(Tokens.INFEG_TOKEN)) {testeAccept(Tokens.INFEG_TOKEN,CodesErr.NUM_ERR);}
	else if(scanner.getSymbCour().getToken().equals(Tokens.SUPEG_TOKEN)) {testeAccept(Tokens.SUPEG_TOKEN,CodesErr.NUM_ERR);}

}
public void adop() throws ErreurLexicale, IOException {

		 if(scanner.getSymbCour().getToken().equals(Tokens.PLUS_TOKEN)){testeAccept(Tokens.PLUS_TOKEN,CodesErr.VAR_ERR);}
		 else if(scanner.getSymbCour().getToken().equals(Tokens.MOINS_TOKEN)){testeAccept(Tokens.MOINS_TOKEN,CodesErr.VAR_ERR);}

}
public void mulop() throws ErreurLexicale, IOException {
	 if(scanner.getSymbCour().getToken().equals(Tokens.MUL_TOKEN)){testeAccept(Tokens.MUL_TOKEN,CodesErr.VAR_ERR);}
	 else if(scanner.getSymbCour().getToken().equals(Tokens.DIV_TOKEN)){testeAccept(Tokens.DIV_TOKEN,CodesErr.VAR_ERR);}
}

public Scanner getScanner() {
	return scanner;
}

public void setScanner(Scanner scanner) {
	this.scanner = scanner;
}

}
