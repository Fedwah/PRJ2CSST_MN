package beans.session.maintenance;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.vehicules.VehiculesManager;

public class MaintenanceFactory extends BeanFactory<Maintenance> {
	
	

	public MaintenanceFactory() {

	}

	public MaintenanceFactory(Class<Maintenance> beanClass) {
		super(beanClass);

	}

	@Override
	public Maintenance create(HttpServletRequest request) {
		Maintenance m = new Maintenance();

		Vehicule v = new Vehicule(request.getParameter("matricule"));
		m.setV(v);
		m.setUn(v.getUnite());
		System.out.println("id est piece " + request.getParameter("piece"));
		Piece p = new Piece(request.getParameter("piece"));
		m.setP(p);
		System.out.println("niveau est " + request.getParameter("niveau"));
		Niveau n = new Niveau(Integer.parseInt(request.getParameter("niveau")));
		m.setNiv(n);
		try {
			m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ));
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		m.setUn(new Unite("un1"));
		return m ;
	}
	
	public Maintenance create(HttpServletRequest request, VehiculesManager vm) {
		Maintenance m = new Maintenance();
		if(vm !=null)
		{
			System.out.println("vm est null");
		}
		Vehicule v = vm.trouver(request.getParameter("matricule"));
		m.setV(v);
		m.setUn(v.getUnite());
		Piece p = new Piece(request.getParameter("piece"));
		m.setP(p);
		Niveau n = new Niveau(Integer.parseInt(request.getParameter("niv")));
		try {
			m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ));
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return m ; 
	}

	@Override
	public void validateChilds(Maintenance bean) {
		if(bean.getV() == null)
		{
			this.addErreurs("v", "Ce numero d'immatriculation n'appartient à aucun véhicule");
		}
		
	}

	@Override
	public void updateChange(Maintenance newB, Maintenance old) {
		// TODO Auto-generated method stub
		
	}

}
