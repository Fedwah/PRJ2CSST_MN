package servlets.pieces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceManager;

/**
 * Servlet implementation class DeletePiece
 */
@WebServlet("/pieces/remove/*")
public class DeletePiece extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PieceManager pm = new PieceManager();
	private static final String Piece_Vue = "/pieces";
	private String id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePiece() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(Piece_Vue);
        id = request.getPathInfo().substring( 1 );// id of element
        PrintWriter out = response.getWriter();
		out.println("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>");
		out.println( 
				"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		out.println("<script>");
		out.println("$(document).ready(function(){");
		out.println("<script>\r\n" + 
				"    $(document).ready(function(){\r\n" + 
				"        swal({\r\n" + 
				"            title: \"Are you sure?\",\r\n" + 
				"            text: \"Once deleted, you will not be able to recover this imaginary file!\",\r\n" + 
				"            icon: \"warning\",\r\n" + 
				"            buttons: true,\r\n" + 
				"            dangerMode: true,\r\n" + 
				"          })\r\n" + 
				"          .then((willDelete) => {\r\n" + 
				"            if (willDelete) {\r\n" + 
				"              swal(\"Poof! Your imaginary file has been deleted!\", {\r\n" + 
				"                icon: \"success\",\r\n" + 
				"              });\r\n" + 
				"            } else {\r\n" + 
				"              swal(\"Your imaginary file is safe!\");\r\n" + 
				"            }\r\n" + 
				"          })\r\n" + 
				"    });\r\n" + 
				"\r\n" + 
				"</script>");
        if ( pm.trouverSupprimer( id ) ) // chercher l'element et le supprimer
        {
            pg.redirect( getServletContext(), request, response );
        } 
        else //echec de suppression
        {
            response.getWriter().append( "Erreur: La suppresion de la piece n∞"+id+" a echou√©" );
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
