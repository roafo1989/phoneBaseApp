package ru.home.phoneBaseApp.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.web.phoneBaseNote.PhoneBaseNoteController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class NoteServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private PhoneBaseNoteController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controller = springContext.getBean(PhoneBaseNoteController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PhoneBaseNote note = new PhoneBaseNote(
                request.getParameter("name"),
                Long.parseLong(request.getParameter("number")),
                request.getParameter("comment")
        );

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            controller.create(note);
        } else {
            controller.update(note, getId(request));
        }
        response.sendRedirect("notes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("notes");
                break;
            case "create":
            case "update":
                final PhoneBaseNote note = "create".equals(action) ? new PhoneBaseNote() : controller.getById(getId(request));
                request.setAttribute("note", note);
                request.getRequestDispatcher("/noteForm.jsp").forward(request, response);
                break;
            case "filter":
                long number = Long.parseLong(request.getParameter("number"));
                request.setAttribute("notes",controller.getByNumber(number));
                request.getRequestDispatcher("/notes.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("notes", controller.getAll());
                request.getRequestDispatcher("/notes.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
