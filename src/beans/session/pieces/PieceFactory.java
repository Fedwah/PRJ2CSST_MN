package beans.session.pieces;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;

public class PieceFactory extends BeanFactory<Piece> {

	@Override
	public Piece create(HttpServletRequest request) {
		String code = (String) request.getParameter("codepiece");
		String nom = (String) request.getParameter("nom");
		String mark = request.getParameter("marque");	
		Marque m = new Marque(mark);
		String modal = request.getParameter("modele");
		Modele mod = new Modele(modal);
		Piece p = new Piece(code,nom,m,mod);
		return p;
	}
	public boolean validate( Piece bean ) {
		//Map<String,ArrayList<String>> err = getErreurs();
		boolean valide = true;
		if(bean.getId().isEmpty())
		{
	
			addErreurs("ID", "Empty ID");
			valide = false;
		}
		if(bean.getPieceName().isEmpty())
		{
			
			addErreurs("pieceName", "Empty Name");
			valide = false;
		}
		return valide;		
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
