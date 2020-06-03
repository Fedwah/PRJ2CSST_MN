package beans.session.pieces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.pieces.Piece;
import beans.session.general.BeanManager;

@Stateless
public class PieceManager extends BeanManager<Piece> {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;

	public PieceManager() {
		super(Piece.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Piece> getList()
	{
		return (List<Piece>) em.createQuery("select l from Piece l").getResultList();
	}
	@Override
	public EntityManager getEntityManger() {
		// TODO Auto-generated method stub
		return em;
	}
    public boolean updatePiece(Object id,Piece newPiece)
    {
    	Piece oldPiece =(Piece) this.trouver(id);
    	if(oldPiece != null)
    	{
    		PieceFactory pf = new PieceFactory();
    		pf.updateChange(newPiece, oldPiece);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
