package beans.session.general.page;


import java.util.List;
import java.util.Stack;

public class Pather {
   
    private static final int NB_LINK_SHOW = 5;
    private Stack<Page> pages;
    private Boolean tooLong;
    public Pather(String root) {
       this.pages = new Stack<Page>();
       this.tooLong = false;
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

}
