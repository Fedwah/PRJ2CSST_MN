package beans.session.general.page;

public class PageState {

    private boolean success;
    private String  message;
    private String  title;
    private String  icon;

    private boolean seen;
    
    
    public PageState( boolean success, String title, String message, String icon ) {
        super();
        this.success = success;
        this.message = message;
        this.title = title;
        this.icon = icon;
        this.seen = false;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess( boolean success ) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon( String icon ) {
        this.icon = icon;
    }

    public static PageState succes( String title , String message) {
        return new PageState( true, title,message,"success");
    }

    public static PageState error( String title , String message ) {
        return new PageState( false,title, message ,"warning");
    }

    public static PageState none() {
        return null;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.title+" "+this.message+" "+this.icon;
    }

    public boolean isSeen() {
        return seen;
    }
    
    public void setSeen(){
        if(!this.seen) this.seen = true;
    }
}
