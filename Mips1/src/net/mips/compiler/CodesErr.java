package net.mips.compiler;

public enum CodesErr {
CAR_INC_ERR("Symbole inconnu"),FIC_VID_ERR("Erreur d'ouverture de fichier"),PROGRAM_ERR("Mot clé program attendu")
,ID_ERR("identificateur Attendu"),PVIR_ERR("Symbole';' attendu"),PNT_ERR("Erreur point"),CONST_ERR("Erreur constante"),VAR_ERR("Erreur Variable")
,	AFFEC_ERR("Erreur Affectation"),NUM_ERR("Erreur num");
	private String message;
	
	
	CodesErr(String message){
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
