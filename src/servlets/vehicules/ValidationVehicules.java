package servlets.vehicules;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.general.BeanValidator;
import beans.vehicules.EtatVehicule;
import beans.vehicules.Marque;
import beans.vehicules.Model;
import beans.vehicules.Vehicule;

/**
 * Servlet implementation class ValidationVehicules
 */
@WebServlet("Vehicules/add")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ValidationVehicules extends HttpServlet {
    private static final String PARAM_DATE_ACHAT = "dateAchat";
    private static final String PARAM_NUM_IMMATRICULATION = "numImmatriculation";
    private static final String PARAM_MODEL = "model";
    private static final String PARAM_MARQUE = "marque";
    private static final String PARAM_ETAT = "etat";
    private static final String VUE_SUCCESS = "/WEB-INF/vues/vehicules/vehicules.add.jsp";
    private static final String VUE_FAIL = "/WEB-INF/vues/vehicules/vehicules.form.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationVehicules() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Vehicule vehicule = new Vehicule();
		
		vehicule.setNum_immatriculation( request.getParameter( PARAM_NUM_IMMATRICULATION ) );
		try {
            vehicule.setDate_achat(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter( PARAM_DATE_ACHAT )));
        } catch ( ParseException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		vehicule.setEtat( new EtatVehicule(request.getParameter( PARAM_ETAT )));
		vehicule.setMarque( new Marque(request.getParameter( PARAM_MARQUE )));
		vehicule.setModel( new Model(request.getParameter( PARAM_MODEL )) );
		InputStream inputStream = null;
		OutputStream out = null;
		Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
           
            vehicule.setPhoto( inputStream.);
        }
        
		System.out.println(  new BeanValidator<Vehicule>( vehicule ).getErreurs());
		request.setAttribute( "erreurs", new BeanValidator<Vehicule>( vehicule ).getErreurs());
		request.setAttribute( "vehicule", vehicule );
		
		this.getServletContext().getRequestDispatcher(VUE_FAIL).forward( request, response );
	}

}
