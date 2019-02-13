package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pet;

/**
 * Servlet implementation class navigationServlet
 */
@WebServlet("/navigationServlet")
public class navigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public navigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetsListHelper dao = new PetsListHelper();
		String act = request.getParameter("doThisToPet");
		if (act == null) {
			 //no button has been selected
			getServletContext().getRequestDispatcher("/viewAllPetsServlet").forward(request, response);
		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Pet itemToDelete = dao.searchForPetById(tempId);
				dao.deleteItem(itemToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllPetsServlet").forward(request, response);
			} 
		} else if (act.equals("edit")) {
			 try {
				 Integer tempId = Integer.parseInt(request.getParameter("id"));
				 Pet itemToEdit = dao.searchForPetById(tempId);
				 request.setAttribute("itemToEdit", itemToEdit);
				 getServletContext().getRequestDispatcher("/edit-pet.jsp").forward(request, response);
			 } catch (NumberFormatException e) {
				 getServletContext().getRequestDispatcher("/viewAllPetsServlet").
					 forward(request, response);
			 }
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
			
		doGet(request, response);
	}

}
