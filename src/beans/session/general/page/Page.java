package beans.session.general.page;

public class Page {
    
    private String path;
    private String link;
    
    public Page( String path, String link ) {
        super();
        this.path = path;
        this.link = link;
    }

   
    @Override
    public boolean equals( Object obj ) {
        // TODO Auto-generated method stub
        return this.path.equals(((Page)obj).path);
    }
    
    public String getPath() {
        return path;
    }
    
    public String getLink() {
        return link;
    }
}
