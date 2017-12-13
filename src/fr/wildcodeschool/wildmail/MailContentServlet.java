package fr.wildcodeschool.wildmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MailContentServlet")
public class MailContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        boolean success = false;
        if (id != null && !id.isEmpty()) {
            try {
                int idValue = Integer.parseInt(id);
                List<MailBean> mailList;
                if (request.getSession().getAttribute("mailList") == null) {
                    mailList = new ArrayList<>();
                } else {
                    mailList = (List<MailBean>) request.getSession().getAttribute("mailList");
                }
                if (idValue < mailList.size()) {
                    MailBean mailBean = mailList.get(idValue);
                    success = true;
                    request.setAttribute("mailBean", mailBean);
                    this.getServletContext().getRequestDispatcher("/mail_content.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // id value incorrect
            }
        }
        if (!success) {
            response.sendRedirect(request.getContextPath() + "/mail/list");
        }
    }
}
