package beans.session.pieces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.pieces.Piece;
import beans.session.general.BeanManager;

@Stateless
public class PieceManager extends BeanManager {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;

	public PieceManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void addPiece(Piece p)
	{
		em.persist(p);
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

}
