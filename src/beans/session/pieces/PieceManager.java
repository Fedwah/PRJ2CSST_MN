package beans.session.pieces;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.pieces.Piece;

@Stateless
public class PieceManager {
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

}
