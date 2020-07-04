package beans.entities.maintenance.niveaux;



public enum Niveau {
	niv1("Niveau 1", "Actions simples n�cessaires � l�exploitation et r�alis�es sur des �l�ments facilement accessibles en toute s�curit�" + 
			"� l�aide d��quipements de soutien int�gr�s au bien."),
	niv2("Niveau 2","Actions qui n�cessitent des proc�dures simples et/ou des �quipements de soutien (int�gr�s au bien ou ext�rieurs)" + 
			"d�utilisation ou de mise en �uvre simple."),
	niv3("Niveau 3","Op�rations qui n�cessitent des proc�dures complexes et/ou des �quipements de soutien portatifs, d�utilisation ou" + 
			"de mise en �uvre complexes"),
	niv4("Niveau 4","Op�rations dont les proc�dures impliquent la ma�trise d�une technique ou technologie particuli�re et/ou la mise en" + 
			"�uvre d��quipements de soutien sp�cialis�s."),
	niv5("Niveau 5","Op�rations dont les proc�dures impliquent un savoir-faire, faisant appel � des techniques ou technologies particuli�res,"
			+ " des processus et/ou des �quipements de soutien industriels.");
	
	public final String label; // niveau
	public final String desc; // description

	private Niveau(String label,String desc) {
		this.label = label;
		this.desc = desc;
	}
	



	public String getLabel() {
		return label;
	}

	public String getDesc() {
		return desc;
	}
	
	public Niveau getNiv(String label) {
        for(Niveau n: Niveau.values()) {
            if(n.getLabel().equals( label ))
                return n;
        }
        return null;
    }
	
	
	
}
