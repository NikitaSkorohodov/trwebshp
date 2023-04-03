/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Firm;
import entity.Shuse;
import entity.Cover;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.FirmFacade;
import session.ShuseFacade;
import session.CoverFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "ShuseServlet", urlPatterns = {
    "/addShuse",
    "/createShuse",
    "/listShuse",
    "/shuse",
    
})
public class ShuseServlet extends HttpServlet {
    @EJB FirmFacade firmFacade;
    @EJB ShuseFacade bookFacade;
    @EJB CoverFacade coverFacade;
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
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/addBook":
                request.setAttribute("listFirms", firmFacade.findAll());
                request.setAttribute("listCovers", coverFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/books/addBook.jsp").forward(request, response);
                break;
            case "/createBook":
                String shuseName = request.getParameter("bookName");
                String[] firms = request.getParameterValues("firms");
                String prise = request.getParameter("prise");
                String quantity = request.getParameter("quantity");
                String coverId = request.getParameter("coverId");
                if(shuseName.isEmpty() || prise.isEmpty() || quantity.isEmpty()){
                    request.setAttribute("bookName", shuseName);
                    request.setAttribute("publishedYear", prise);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Заполните все поля.");
                    request.getRequestDispatcher("/addShuse").forward(request, response);
                    break;
                }
                if(firms == null){
                    request.setAttribute("bookName", shuseName);
                    request.setAttribute("publishedYear", prise);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Вы не выбрали автора");
                    request.getRequestDispatcher("/addShuse").forward(request, response);
                    break;
                }
                if(coverId == null){
                    request.setAttribute("shuseName", shuseName);
                    request.setAttribute("publishedYear", prise);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("info", "Вы не выбрали обложку для книги");
                    request.getRequestDispatcher("/addShuse").forward(request, response);
                    break;
                }
                List<Firm> listFirms = new ArrayList<>();
                for (int i = 0; i < firms.length; i++) {
                   listFirms.add(firmFacade.find(Long.parseLong(firms[i])));
                }
                Shuse shuse = new Shuse();
                shuse.setFirm(listFirms);
                shuse.setShuseName(shuseName);
                shuse.setPrise(Integer.parseInt(prise));
                shuse.setQuantity(Integer.parseInt(quantity));
                Cover cover = coverFacade.find(Long.parseLong(coverId));
                shuse.setCover(cover);
                bookFacade.create(shuse);
                for (int i = 0; i < listFirms.size(); i++) {
                    Firm firm = listFirms.get(i);
                    firm.getShuses().add(shuse);
                    firmFacade.edit(firm);
                }
                request.setAttribute("info", "Книга добавлена успешно");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listBooks":
                request.setAttribute("listBooks", bookFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/books/listBooks.jsp").forward(request, response);
                break;
            case "/book":
                String id = request.getParameter("id");
                request.setAttribute("book", bookFacade.find(Long.parseLong(id)));
                request.getRequestDispatcher("/WEB-INF/books/book.jsp").forward(request, response);
                break;
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
