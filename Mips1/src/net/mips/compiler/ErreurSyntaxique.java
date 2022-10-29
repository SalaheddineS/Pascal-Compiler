package net.mips.compiler;

public class ErreurSyntaxique extends ErreurCompilation{

	ErreurSyntaxique(CodesErr c) {
		super(c.getMessage());
	}

}
