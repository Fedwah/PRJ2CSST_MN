package beans.entities.pieces;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
@Entity
public class Piece implements Serializable {
@Id
private String id; 
private String pieceName;
@ManyToOne
private Marque mark;
@ManyToOne
private Modele modal;
public Piece(String id, String pieceName, Marque mark, Modele modal) {
	super();
	this.id = id;
	this.pieceName = pieceName;
	this.mark = mark;
	this.modal = modal;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPieceName() {
	return pieceName;
}
public void setPieceName(String pieceName) {
	this.pieceName = pieceName;
}
public Marque getMark() {
	return mark;
}
public void setMark(Marque mark) {
	this.mark = mark;
}
public Modele getModal() {
	return modal;
}
public void setModal(Modele modal) {
	this.modal = modal;
}

}