package id.artivisi.belajar.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HaloController {
    @GetMapping("/halo")
    public void halo(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        output.println("<html><head><title>Halo Spring MVC</title></head>");
        output.println("<body>");
        output.println("<h1>Halo Spring MVC</h1>");
        output.println("</body>");
        output.println("</html>");
    }
}
