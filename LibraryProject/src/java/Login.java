/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BL.CustomerService;
import DAL.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author צפורה רינה
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            String name = request.getParameter("userName");
            String password = request.getParameter("password");
            CustomerService cs=new CustomerService();
            
            
            
            if (name != null && !name.isEmpty()) {
            // יצירת עוגיה עם שם המשתמש
            Cookie usernameCookie = new Cookie("username", name);
            usernameCookie.setMaxAge(60 * 60 * 24); // תקופת החיים של העוגיה - יום אחד
            response.addCookie(usernameCookie);

            // יצירת אובייקט Customer
            Customer customer = new Customer(name, password);

            // בדיקה אם הלקוח כבר קיים במערכת

            Customer existingCustomer = cs.getByName(name);
           
            if (existingCustomer == null) {
                // הוספת הלקוח לבסיס הנתונים
                cs.addCustomer(name, password);
            } else if(cs.isCorrect(name, password)) {
                // שימור הלקוח הקיים ב- `ServletContext` אם הוא קיים
                customer = existingCustomer;
            }

            // שמירת הלקוח ב- `ServletContext`
            getServletContext().setAttribute("customer", customer);

            // הפניה לדף קבלת פנים עם שם המשתמש
            request.setAttribute("username", name);
            request.getRequestDispatcher("/Home").include(request, response);
        } else {
            // במקרה שאין שם משתמש
            request.getRequestDispatcher("index.html").forward(request, response);
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
