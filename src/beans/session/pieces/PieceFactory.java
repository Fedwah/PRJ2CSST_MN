package beans.session.pieces;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

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
	    super(Piece.class);
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
		System.out.println("valeur cpt est " + request.getParameter("cpt"));
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
		System.out.println( "get modals :"+modals +" size "+modals.size());
		for(int j=0; j<modals.size()-1;j++)
		{
		    
			int cpt = 1 ;
			int idMod = modals.get(j).getId();
			
			for(int i = j+1; i<modals.size();i++)
			{
				if(modals.get(i).getId() == idMod) cpt ++;
			}
			
			if(cpt>1) this.addErreurs("modals", "un modele est déja insére, vous ne pouvez pas l'inserer deux fois");
		}
		
	}
	
	// mise � jour dans la base de donn�es
	@Override
	public void updateChange(Piece newB, Piece old) {
		old.setPieceName(newB.getPieceName());
		old.setModals(newB.getModals());
	
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
	
	@Override
	public Map<String, Class<?>> fieldListClassesExport() {
	    Map<String, Class<?>> c = super.fieldListClassesExport();
	    c.put( "modals", Modele.class );
	    return c;
	}
}
