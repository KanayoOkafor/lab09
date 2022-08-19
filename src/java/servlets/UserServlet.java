
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.UserService;


public class UserServlet extends HttpServlet {

  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService ();
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        
        try{
            switch(action) {
                case "edit":
                    User user = us.get(email);
                    request.setAttribute("editUser", user);
                    break;
                case "delete":
                    us.delete(email);
                    break;
                default:
                break;
            }
        }
            catch (Exception e) {
                    request.setAttribute("message", "Invalid");
                    }
        
    
    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request,response);
    return;
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        int roleID = Integer.parseInt(request.getParameter("role"));
        Boolean active = false;
        Role role = null;
        
        switch (roleID) {
            case 1:
                role = new Role((Integer)1, "System Admin");
            break;
            case 2:
                role = new Role((Integer)2, "Regular User");
            break;
            case 3:
                role = new Role((Integer)3, "Company admin");
            break;
            default:
                role = new Role ((Integer)2, "Regular user");
                }
                        
        
        
        
        UserService us = new UserService();
        
        try{
            switch (action) {
                case "add":
                    us.insert(email, active, firstname, lastname, password, role);
                    break;
                case "update":
                    us.update(email, active, firstname, lastname, password, role);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "Invalid");
        }
        
        try{
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        }
          catch (Exception e){
              request.setAttribute("message", "Invalid");
          }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
        
    }

   

}
