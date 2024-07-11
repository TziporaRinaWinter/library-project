/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BL.BorrowingService;
import BL.CustomerService;
import DAL.Borrowing;
import DAL.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author The user
 */
@WebServlet(urlPatterns = {"/Update"})
public class Update extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
          out.append("<h1>in update</h1>");
          BorrowingService bos=new BorrowingService();
           List<Borrowing> borrowings=bos.findCustomerBorrowing(1);
//          String borrowings [] =request.getParameterValues("borrowing");
          int numberOfCheckboxes = 2;
          for (int i = 1; i <=numberOfCheckboxes; i++) {
            String checkboxName = "checkbox"+i;
            String checkboxValue = request.getParameter(checkboxName);
            boolean isChecked = checkboxValue != null;
            if(isChecked){
                Borrowing borrowing = borrowings.get(i - 1);
                bos.update(borrowing.getCustomerId().getId(), borrowing.getBookId());
                out.append("<p>is returned</p>");
            }
          }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
