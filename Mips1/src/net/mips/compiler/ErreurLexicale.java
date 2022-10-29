package net.mips.compiler;

public class ErreurLexicale extends ErreurCompilation{

	ErreurLexicale(CodesErr c) {
		super(c.getMessage());
		
	}

}
