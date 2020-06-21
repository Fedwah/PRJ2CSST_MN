package beans.session.pieces;

import java.util.Arrays;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleManager;

public class PieceFactory extends BeanFactory<Piece> {
	@EJB
	private MarqueManager em;	

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
		Marque m = em.trouver(mark);
		if(m!=null) System.out.println("marque existe");
		else System.out.println("marque est null");
		System.out.println("le modele est " + request.getParameter("modele"));
		Modele mod = new Modele(Integer.decode(request.getParameter("modele")));
		System.out.println("le modele est " + mod.getTitre());
		Piece p = new Piece(code,ref,nom,m,mod);
		return p;
	}	
	public Piece create(HttpServletRequest request, MarqueManager markManager, ModeleManager modManager) {
		String code = (String) request.getParameter("codepiece");
		String nom = (String) request.getParameter("nom");
		String ref = (String) request.getParameter("ref");
		String mark = request.getParameter("marque");	
		Marque m = markManager.trouver(mark);
		if(m!=null) System.out.println("marque existe");
		else System.out.println("marque est null");
		System.out.println("le modele est " + request.getParameter("modele"));
		Modele mod = modManager.trouver((Integer.decode(request.getParameter("modele"))));
		System.out.println("le modele est " + mod.getTitre());
		Piece p = new Piece(code,ref,nom,m,mod);
		return p;
	}
	
	@Override
	public void validateChilds(Piece bean , BeanManager<Piece> beanM) {
		MarqueFactory mf = new MarqueFactory();
		if (!mf.findModal(bean.getMark(), bean.getModal()))
		{
			this.addErreurs( "modal", "ce modele n'appartient pas � la marque s�lectionn�e");
		}
		
	}
	// mise � jour dans la base de donn�es
	@Override
	public void updateChange(Piece newB, Piece old) {
		old.setPieceName(newB.getPieceName());
		old.setMark(newB.getMark());
		old.setModal(newB.getModal());
		old.setReference(newB.getReference());		
	}

}
