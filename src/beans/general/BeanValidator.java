package beans.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import beans.vehicules.Vehicule;

public class BeanValidator<T> {

    private Map<String,ArrayList<String>> erreurs ;
    public BeanValidator(T bean) {
        super();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        
        erreurs = new HashMap<String,ArrayList<String>>();
        
        for ( ConstraintViolation<T> v : violations ) {
            if(erreurs.containsKey(v.getPropertyPath().toString())){
                erreurs.get(v.getPropertyPath().toString()).add(v.getMessage());
            }else {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add( v.getMessage() );
                erreurs.put( v.getPropertyPath().toString(),arr);
            }
      
        }
    }
    
    public Map<String, ArrayList<String>> getErreurs() {
        return erreurs;
    }

}
