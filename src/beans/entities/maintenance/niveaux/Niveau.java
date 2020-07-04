package beans.entities.maintenance.niveaux;



public enum Niveau {
	niv1("Niveau 1", "Actions simples nécessaires à l’exploitation et réalisées sur des éléments facilement accessibles en toute sécurité" + 
			"à l’aide d’équipements de soutien intégrés au bien."),
	niv2("Niveau 2","Actions qui nécessitent des procédures simples et/ou des équipements de soutien (intégrés au bien ou extérieurs)" + 
			"d’utilisation ou de mise en œuvre simple."),
	niv3("Niveau 3","Opérations qui nécessitent des procédures complexes et/ou des équipements de soutien portatifs, d’utilisation ou" + 
			"de mise en œuvre complexes"),
	niv4("Niveau 4","Opérations dont les procédures impliquent la maîtrise d’une technique ou technologie particulière et/ou la mise en" + 
			"œuvre d’équipements de soutien spécialisés."),
	niv5("Niveau 5","Opérations dont les procédures impliquent un savoir-faire, faisant appel à des techniques ou technologies particulières,"
			+ " des processus et/ou des équipements de soutien industriels.");
	
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
