package beans.session.vehicules.categorie;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.CategorieVehicule;
import beans.session.general.BeanFactory;

public class CategorieVehiculeFactory extends BeanFactory<CategorieVehicule> {

    public static final String PARAM_TITRE = "new_categorie";
    
    public static final String VUE_LIST_FORM                  = "/WEB-INF/vues/vehicules/categories/vehicules.categories.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Vehicules/Categories";
    @Override
    
    public CategorieVehicule create( HttpServletRequest request ) {
        // TODO Auto-generated method stub
        CategorieVehicule categ = new CategorieVehicule();
        categ.setTitre((String) (request.getParameter( PARAM_TITRE )));
        return categ;
    }
    
    @Override
    public void updateChange( CategorieVehicule newB, CategorieVehicule old ) {
        // TODO Auto-generated method stub
        if(old.getTitre()!=newB.getTitre()) {
           old.setTitre( newB.getTitre() );
        }
        
    }
    
    @Override
    public void validateChilds( CategorieVehicule bean ) {
        // TODO Auto-generated method stub
        //y'a pas de champ (child) complexe dans CategorieVehicule de type ManyToOne ( ou autre) 
        //donc on fait rien 
    }
}
