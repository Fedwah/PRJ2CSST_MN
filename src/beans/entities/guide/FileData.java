package beans.entities.guide;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private byte[] bin;
    
    
    
    public FileData() {
       
    }


    public FileData( Integer id ) {
        super();
        this.id = id;
    }


    public FileData(byte[] bin) {
        this.bin = bin;
    }


    public Integer getId() {
        return id;
    }


    public void setId( Integer id ) {
        this.id = id;
    }


    public byte[] getBin() {
        return bin;
    }


    public void setBin( byte[] bin ) {
        this.bin = bin;
    }

    
}
