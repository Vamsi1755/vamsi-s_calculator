package com.caluclater;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class test1 extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            double num1 = Double.parseDouble(
                    request.getParameter("num1"));

            double num2 = Double.parseDouble(
                    request.getParameter("num2"));

            String operator = request.getParameter("operator");

            double result = 0;

            switch (operator) {

            case "+":
                result = num1 + num2;
                break;

            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":

                if (num2 == 0) {
                    response.setStatus(
                            HttpServletResponse.SC_BAD_REQUEST);
                    out.print("Cannot divide by zero");
                    return;
                }

                result = num1 / num2;
                break;

            default:
                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST);
                out.print("Invalid operator");
                return;

            }

            out.print(result);

        }
        catch (NumberFormatException e) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST);

            out.print("Invalid numbers");

        }
    }
}