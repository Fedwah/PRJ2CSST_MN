package beans.session.guide;


import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import beans.entities.guide.Fichier;
import beans.session.general.BeanManager;
import beans.session.general.file.FileSystemManager;

@Stateless
public class FichierManager extends BeanManager<Fichier> {

    private FileSystemManager fs;
    @PersistenceContext(unitName = "MN_unit")
    private EntityManager em;
    
    public FichierManager() {
        super( Fichier.class );
        fs = new FileSystemManager();
    }
    
    @Override
    public EntityManager getEntityManger() {

        return this.em;
    }
    
    
    public boolean ajouter( Fichier bean ,InputStream in ) throws IOException {
        //fs.ajouter( bean.getNom(), in );
        System.out.println( "ajouter en systeme" );
        return super.ajouter( bean );
    }
    
 
    public boolean supprimer( Fichier bean ) {
        //fs.supprimer( bean.getNom());
        return super.supprimer( bean );
    }
    
    public FileSystemManager getFs() {
        return fs;
    }
    
    public Fichier trouverAvecDonnee(Object id) {
        Fichier f= trouver( id );
        if(f!=null) {
            System.out.println( "get bin : "+f.getData().getId().intValue());
        }
        
        return f;
    }

}
