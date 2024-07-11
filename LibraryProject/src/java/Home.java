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
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @authon
 */
@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {

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
            String name=request.getParameter("userName");
            
            Customer c=null;
            CustomerService cs=new CustomerService();
            c=cs.getByName(name);
            int i=0;
            BorrowingService bos=new BorrowingService();
            List<Borrowing> borrowings=bos.findCustomerBorrowing(c.getId());
            /* TODO output your page here. You may use following sample code. */
            out.append("<h1>wellcome</h1>");
            out.append("<h2>");
            out.append(c.getUserName());
            out.append("</h2>");
            out.append("<form method=\"POST\" action=\"/LibraryProject/Update\" name=\"form\">");
            if(borrowings!=null){
                out.append("<table border=1>");
                out.append("<thead>\n"+"<tr>\n" +
                        "<th>Date</th>\n"+"<th>name</th>\n"+"<th>rutuened</th>\n"+
                        "</tr>\n"+"</thead>\n");
                out.append("<tbody>");
                for(Borrowing b:borrowings){
                    i++;
                    String uniqueName="checkbox"+i;
                    String returned="false";
                    if(b.isIsReturn())
                        returned="true";
                    String date="";
                    date+=b.getDate();
                    out.append("<tr>");
                    out.append("<td>");
                    out.append(date);
                    out.append("</td>");
                    out.append("<td>");
                    out.append(b.toString());
                    out.append("</td>");
                    out.append("<td>");
                    out.append(returned);
                    out.append("</td>");
                    out.append("<td>");
                    out.append("<input type=\"checkbox\" name=\""+uniqueName+"\""+">");
                    out.append("</td>");
                    out.append("<input name=\"borrowing\" value=\""+b+"\""+">");
                    out.append("</tr>");
                    
                }
                out.append("</tbody>\n"+"</table>");
            }
            out.append("<input type=\"submit\" value=\"to update\" name=\"form\">");
            out.append("</form>");
//            ServletContextEvent sce=
//            sce.getServletContext().setAttribute("b", borrowings);
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
