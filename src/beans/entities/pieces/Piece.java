package beans.entities.pieces;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.JSONPropertyIgnore;

import beans.entities.maintenance.Maintenance;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;

@Entity
public class Piece implements Serializable {
    @Id
    @NotEmpty
    @Size( min = 1, max = 10 )
    private String            id;

    @NotEmpty
    @Size( min = 1, max = 10 )
    private String            reference;

    @NotEmpty
    @Size( min = 3, max = 20 )
    private String            pieceName;

    @ManyToOne
    private Marque            mark;

    @ManyToOne
    @NotNull
    private Modele            modal;

    @ManyToMany( mappedBy = "pieces" )
    private List<Maintenance> maintenances;

    public Piece() {
    }

    public Piece( String id, String reference, String pieceName, Marque mark, Modele modal,
            List<Maintenance> maintenances ) {
        super();
        this.id = id;
        this.reference = reference;
        this.pieceName = pieceName;
        this.mark = mark;
        this.modal = modal;
        this.maintenances = maintenances;
    }

    public Piece( String id, String pieceName, Marque mark, Modele modal ) {
        super();
        this.id = id;
        this.pieceName = pieceName;
        this.mark = mark;
        this.modal = modal;
    }

    public Piece( String id, String reference, String pieceName, Marque mark, Modele modal ) {
        super();
        this.id = id;
        this.reference = reference;
        this.pieceName = pieceName;
        this.mark = mark;
        this.modal = modal;
    }

    public Piece( String id ) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName( String pieceName ) {
        this.pieceName = pieceName;
    }

    public Marque getMark() {
        return mark;
    }

    public void setMark( Marque mark ) {
        this.mark = mark;
    }

    @JSONPropertyIgnore
    public Modele getModal() {
        return modal;
    }

    public void setModal( Modele modal ) {
        this.modal = modal;
    }

    public String getReference() {
        return reference;
    }

    public void setReference( String reference ) {
        this.reference = reference;
    }

    @JSONPropertyIgnore
    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances( List<Maintenance> maintenances ) {
        this.maintenances = maintenances;
    }

}
