package beans.entities.directive;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import beans.entities.regions.unites.Unite;
import beans.entities.utilisateurs.Utilisateur;
@Entity
public class directive  implements Serializable{
@Id
@GeneratedValue( strategy = GenerationType.IDENTITY )
private String id;
private int sender;
private String objet;
private String message;
private int reciever;





public directive() {
	super();
	 
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

 
public int getSender() {
	return sender;
}
public void setSender(int sender) {
	this.sender = sender;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
 
 
 
public int getReciever() {
	return reciever;
}
public void setReciever(int reciever) {
	this.reciever = reciever;
}
public String getObjet() {
	return objet;
}
public void setObjet(String objet) {
	this.objet = objet;
}
public directive(int sender, String objet, String message, int reciever) {
	super();
	this.sender = sender;
	this.objet = objet;
	this.message = message;
	this.reciever = reciever;
}
 
 
 
 
 



 





}
