package beans.session.general.page.pather;

public class Page {

    private String    path;
    private String    link;
    private PageState pageState;

    public Page( String path, String link ) {
        super();
        this.path = path;
        this.link = link;
        this.pageState = null;
    }

    @Override
    public boolean equals( Object obj ) {
        // TODO Auto-generated method stub
        return this.link.equals( ( (Page) obj ).link);
    }

    public String getPath() {
        return path;
    }

    public String getLink() {
        return link;
    }

    public void setPageState( PageState pageState ) {

        this.pageState = pageState;
    }

    public void updateState() {
        if ( !this.pageState.isSuccess() ) {
            this.pageState.setSuccess( true );
        }

    }

    public void updateState( String messageError ) {
        System.out.println( "Update sate with message: " + messageError );
        if ( messageError != null && !messageError.isEmpty() ) {
            this.pageState.setSuccess( false );
            this.pageState.setMessage( messageError );
        } else {
            updateState();
        }

    }

    public boolean hasAlertAndNotSeen() {
        if(this.pageState != null) {
            boolean b = !this.pageState.isSeen();
            this.pageState.setSeen();
            return b;
        }else {
            return false;
        }
        
    }

    public PageState getPageState() {
        return this.pageState;
    }

}
