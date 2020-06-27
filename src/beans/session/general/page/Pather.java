package beans.session.general.page;


import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Pather {
   
    private static final int NB_LINK_SHOW = 5;
    private Stack<Page> pages;
    private Boolean tooLong;
    private String root;
    private static final String ATT_PATH = "path";
   
    private static final String DEFAUTL_ROOT = "/";
    
    public Pather(String root) {
       this.pages = new Stack<Page>();
       this.tooLong = false;
       this.root = root;
    }
    
    public Pather() {
        this.pages = new Stack<Page>();
        this.tooLong = false;
        this.root = DEFAUTL_ROOT;
     }
    
    public Boolean getTooLong() {
        return tooLong;
    }
    
    public void clear() {
        this.pages.clear();
    }
    public Page forward(String value,String link) {
        Page p =  new Page( value, link );
        pages.add(p);
        return p;
    }
    
    public Page goBack(){
        return pages.pop();
    }
    
    public Page goTo(String name,String link) {
        Page p = null;
        
        if(this.pages.contains( new Page(name,link))) {
            
            do {
                p = goBack();
               
            } while ( !p.getPath().equals(name) );
            
            
            this.pages.add( p );
            
            
            return p;
            
        }else {
           
            return forward( name, link );
        }
       
    }
    
    public List<Page> getPages() {
        this.tooLong = this.pages.size()>=NB_LINK_SHOW;
        if(this.tooLong) {
            return  this.pages.subList(this.pages.size()-NB_LINK_SHOW, this.pages.size());
        }else
            return this.pages.subList( 0, pages.size() );
    }
    
   
    public static Pather getPather(HttpServletRequest request , String root) {
        SessionManager s = new SessionManager();
        Pather p = (Pather) s.get( request, ATT_PATH);
        if (p != null) {
            return p;
        } else {
            p = new Pather( root );
            s.save( request, ATT_PATH, p );
            return p;
        }
    }
    
    public static Pather getPather(HttpServletRequest request) {
        SessionManager s = new SessionManager();
        Pather p = (Pather) s.get( request, ATT_PATH);
        if (p != null) {
            return p;
        } else {
            p = new Pather(  );
            s.save( request, ATT_PATH, p );
            return p;
        }
    }
   
    
    

}
