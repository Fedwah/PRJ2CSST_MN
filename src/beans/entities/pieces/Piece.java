package beans.entities.pieces;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.JSONPropertyIgnore;


import beans.entities.vehicules.Modele;

@Entity
public class Piece implements Serializable {
    
    @Id
    @NotEmpty
    @Size( min = 1, max = 20 )
    private String   refrence;

    @NotEmpty
    @Size( min = 3, max = 20 )
    private String   pieceName;

    @ManyToMany (fetch = FetchType.EAGER)
    @NotNull
    private List<Modele>   modals;

    public Piece() {
    }

    

    public Piece( String refrence ) {
        super();
        this.refrence = refrence;
    }



    public Piece(String refrence, String pieceName, List<Modele> modal) {
		super();
		this.refrence = refrence;
		this.pieceName = pieceName;
		this.modals = modal;
	}

// getters and setters 
	
	

	public String getPieceName() {
        return pieceName;
    }

    public String getRefrence() {
		return refrence;
	}



	public void setRefrence(String refrence) {
		this.refrence = refrence;
	}


	 @JSONPropertyIgnore
	public List<Modele> getModals() {
		return modals;
	}



	public void setModals(List<Modele> modals) {
		this.modals = modals;
	}



	public void setPieceName( String pieceName ) {
        this.pieceName = pieceName;
    }


}
