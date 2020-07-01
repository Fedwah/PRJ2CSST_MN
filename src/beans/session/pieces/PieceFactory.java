package beans.session.pieces;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import beans.entities.pieces.Piece;

import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

import beans.session.vehicules.marques.MarqueManager;


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

		String nom = request.getParameter("nom");
		String ref = request.getParameter("ref");
		List<Modele> modals = createModalsList(request);
		Piece p  = new Piece(ref,nom,modals);
		return p;
	}	

	private List<Modele> createModalsList(HttpServletRequest request) {
		int cpt = Integer.parseInt(request.getParameter("cpt"));
		System.out.println("valeur cpt est " + cpt);
		List<Modele> modals = new ArrayList();
		for(int i=1;i<=cpt;i++)
		{
			String ch = Integer.toString(i);
			System.out.println("caine est " + ch);
			System.out.println("identifiant est " + request.getParameter(ch));
			modals.add(new Modele(Integer.parseInt(request.getParameter(Integer.toString(i)))));
			
		}
		return modals;
	}

	@Override
	public void validateChilds(Piece bean , BeanManager<Piece> beanM) {
		List<Modele> modals = bean.getModals();
		for(Modele mod : modals)
		{
			int cpt = 0 ;
			int idMod = mod.getId();
			for(int i = 0; i<modals.size();i++)
			{
				if(modals.get(i).getId() == idMod) cpt ++;
			}
			if(cpt>1) this.addErreurs("modals", "un modele est déjà inséré, vous ne pouvez pas l'inserer deux fois");
		}
		
	}
	// mise ï¿½ jour dans la base de donnï¿½es
	@Override
	public void updateChange(Piece newB, Piece old) {
	
	}

	public List<Piece> filterByModalId(List<Piece> pieces, int idMod)
	{
		List<Piece> filtrer = new ArrayList();
		for(Piece p : pieces)
		{
			List<Modele> modals = p.getModals();
			for(Modele m : modals)
			{
				if(m.getId() == idMod) filtrer.add(p);
			}
		}
		return filtrer;
	}
}
