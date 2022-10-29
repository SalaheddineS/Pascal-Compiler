package net.mips.compiler;

public class ErreurCompilation extends Exception {
	String message;
ErreurCompilation(String message){
	this.message=message;
}
}
