package beans.session.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;

public class PieceFactory extends BeanFactory<Piece> {

	

	public PieceFactory(Class<Piece> beanClass) {
		super(beanClass);
	}
	
	public PieceFactory() {
	
	}

	@Override
	public Piece create(HttpServletRequest request) {
		String code = (String) request.getParameter("codepiece");
		String nom = (String) request.getParameter("nom");
		String ref = (String) request.getParameter("ref");
		String mark = request.getParameter("marque");	
		Marque m = new Marque(mark);
		Modele mod = new Modele(Integer.decode(request.getParameter("modele")));
		Piece p = new Piece(code,ref,nom,m,mod);
		return p;
	}	

	@Override
	public void validateChilds(Piece bean) {
		if (!Arrays.asList(bean.getMark().getModeles()).contains(bean.getModal()))
		{
			this.addErreurs( "modal", "ce modele n'appartien pas à la marque sélectionnée");
		}
		
	}
	// mise à jour dans la base de données
	@Override
	public void updateChange(Piece newB, Piece old) {
		old.setPieceName(newB.getPieceName());
		old.setMark(newB.getMark());
		old.setModal(newB.getModal());
		old.setReference(newB.getReference());		
	}

}
