package hu.tetra_meno_septemes.app;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.tetra_meno_septemes.app.config.TemplateEngineUtil;
import hu.tetra_meno_septemes.app.word.Word;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/"})
public class MainPage extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            engine.process("index.html", context, resp.getWriter());
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Word word = new Word();
        Gson stringFromJS = new Gson();

        BufferedReader bufferedReader = req.getReader();
        Word requestToJson = stringFromJS.fromJson(bufferedReader, Word.class);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String productsToMainPage = gson.toJson(requestToJson.startGame());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(productsToMainPage);
        resp.getWriter().flush();



    }
}
