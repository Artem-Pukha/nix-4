package com.spnsolo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "main-servlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private ServletReport report;

    @Override
    public void init() throws ServletException {
        report = new ServletReport();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">Main Servlet GET method</h1>");
        responseBody.println("<h3 align=\"center\"> All users </h3>");
        String currentIp = req.getRemoteHost();
        String userAgent = req.getHeader("User-Agent");
        report.addUserInfo(currentIp,userAgent);
        for(Map.Entry<String,String> user : report.getUserInfo().entrySet()){
            responseBody.println("<div align=\"center\">");
            if(user.getKey().equals(currentIp)){
                responseBody.println("<b>"+ user.getKey() + "::" + user.getValue()+"</b>");
            }else {
                responseBody.println(user.getKey() + "::" + user.getValue());
            }
        }
        responseBody.println("</div>");
    }

}
