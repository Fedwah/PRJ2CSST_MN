package beans.session.pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

public class PieceFactory extends BeanFactory<Piece> {


	@Override
	public Piece create(HttpServletRequest request) {
		String code = (String) request.getParameter("codepiece");
		String nom = (String) request.getParameter("nom");
		String mark = request.getParameter("marque");	
		Marque m = new Marque(mark);
		String modal = request.getParameter("modele");
		Modele mod = new Modele(modal,m);
		Piece p = new Piece(code,nom,m,mod);
		return p;
	}	

	@Override
	public void validateChilds(Piece bean) {
		
		
	}
	// mise à jour dans la base de données
	@Override
	public void updateChange(Piece newB, Piece old) {
		old.setPieceName(newB.getPieceName());
		old.setMark(newB.getMark());
		old.setModal(newB.getModal());
		
	}

}
