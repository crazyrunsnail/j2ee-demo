package personal.davino.j2ee.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/test", name = "MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("application/json;charset=utf8");

        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes, 0, bytes.length);
        }

        inputStream.close();

        outputStream.close();
        outputStream.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("application/json;charset=utf8");

        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes, 0, bytes.length);
        }

        inputStream.close();

        outputStream.close();
        outputStream.flush();
    }
}
