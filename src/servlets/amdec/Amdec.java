package servlets.amdec;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.amdec.Cause;
import beans.entities.amdec.Defaillance;
import beans.entities.amdec.Effet;
import beans.session.amdec.cause.CauseFactory;
import beans.session.amdec.cause.CausesManager;
import beans.session.amdec.defaillance.DefaillanceFactory;
import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.effet.EffetFactory;
import beans.session.amdec.effet.EffetManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class Amdec
 */
@WebServlet("/amdec")
public class Amdec extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String AMDEC = "/WEB-INF/vues/amdec/amdec.jsp";  
	@EJB
	private CausesManager causeManager;
	@EJB
	private DefaillanceManager defaiManager;
	@EJB
	private EffetManager effManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Amdec() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(AMDEC , "Analyse AMDEC");	
		request.setAttribute("causes", causeManager.lister());
		request.setAttribute("effets", effManager.lister());
		request.setAttribute("defai", defaiManager.lister());
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(AMDEC , "Analyse AMDEC");	
		// volet cause
		if(request.getParameter("cause") != null)
		{
			System.out.println("post from cause");
			Cause c = new Cause( request.getParameter("cause"));
			CauseFactory cf = new CauseFactory();
			if(cf.validate(c) && cf.uniqueField(causeManager, "cause", c.getCause()))
			{
				causeManager.ajouter(c);
				doGet(request,response);
			}
			else
			{
				request.setAttribute("erreurs", cf.getErreurs());
				request.setAttribute("causes", causeManager.lister());
				pg.generate(getServletContext(), request, response);
			}
		}
		// volet effet
		if(request.getParameter("eff") != null)
		{
			System.out.println("post from effet");
			Effet e = new Effet( request.getParameter("eff"));
			EffetFactory ef = new EffetFactory();
			if(ef.validate(e) && ef.uniqueField(effManager, "effet", e.getEffet()))
			{
				effManager.ajouter(e);
				doGet(request,response);
			}
			else
			{
				request.setAttribute("erreurs", ef.getErreurs());
				request.setAttribute("effets", effManager.lister());
				pg.generate(getServletContext(), request, response);
			}
		}
		// volet defaillance
		if(request.getParameter("def") != null)
		{
			System.out.println("post from defaillance");
			Defaillance d = new Defaillance( request.getParameter("def"));
			DefaillanceFactory df = new DefaillanceFactory();
			if(df.validate(d) && df.uniqueField(defaiManager, "defaillance", d.getDefaillance()))
			{
				defaiManager.ajouter(d);
				doGet(request,response);
			}
			else
			{
				request.setAttribute("erreurs", df.getErreurs());
				request.setAttribute("defaillances", defaiManager.lister());
				pg.generate(getServletContext(), request, response);
			}
		}
		
		
		
	}

}