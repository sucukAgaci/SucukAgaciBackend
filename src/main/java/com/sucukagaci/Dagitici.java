/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author umurgogebakan
 */
@WebServlet(name = "Dagitici", urlPatterns = {"/Dagitici"})
public class Dagitici extends HttpServlet {
    Random random = new Random();
    public Dagitici() {

    }

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Dagitici</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Dagitici at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            HashMap<String, Object> result = new HashMap<>();
            String servisAdi = request.getParameter("s");

            if (servisAdi.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (Storage.getStorage().userExist(username)) {
                    String storedPassword = Storage.getStorage().getUser(username).getPassword();

                    if (storedPassword.equals(password)) {
                        HttpSession ss = request.getSession(true);
                        ss.setAttribute("user", Storage.getStorage().getUser(username));

                        result.put("Error", 0);
                        result.put("message", "Hosgeldiniz" + Storage.getStorage().getUser(username));
                        Gson gson = new Gson();
                        out.println(gson.toJson(result));
                    } else {
                        HttpSession ss = request.getSession(false);
                        if (ss != null) {
                            ss.invalidate();
                        }
                        result.put("Error", 99);
                        result.put("message", "Username or Password invalid");
                        Gson gson = new Gson();
                        out.println(gson.toJson(result));
                    }
                } else {
                    HttpSession ss = request.getSession(false);
                    if (ss != null) {
                        ss.invalidate();
                    }
                    result.put("Error", 99);
                    result.put("message", "Username or Password invalid");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                }
            } else if (servisAdi.equals("getUser")) {
                HttpSession ss = request.getSession(false);

                if (ss != null) {
                    Gson gson = new Gson();
                    out.println(gson.toJson(ss.getAttribute("user")));
                } else {
                    result.put("Error", 98);
                    result.put("message", "Not logged in");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                }

            } else if (servisAdi.equals("signUp")) {
                // Oturum aciksa kullaniciyi cikart, signUp'i baslat.
                HttpSession ss = request.getSession(false);
                if (ss != null) {
                    ss.invalidate();
                }
                ss = request.getSession(true);
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String age = request.getParameter("age");

                if (Storage.getStorage().userExist(email)) {
                    result.put("Error", 96);
                    result.put("message", "User already exists.");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                    return;
                }
                String verifCode = "";
                for ( int i = 0; i < 6; i++){
                    verifCode += random.nextInt(10);
                }
                ss.setAttribute("verificationCode", verifCode);
                User tmp = new User(email, password, name, surname);
                ss.setAttribute("candidate", tmp);
                log(verifCode);
                log(tmp.toString());
                result.put("Error", 0);
                result.put("message", "User Signed Up. Verify your email");
                Gson gson = new Gson();
                out.println(gson.toJson(result));

            } else if (servisAdi.equals("verification")) {
                // Oturum aciksa kullaniciyi cikart, signUp'i baslat.
                HttpSession ss = request.getSession(false);
                User tmp = (User) ss.getAttribute("candidate");
                String verifCode = (String) ss.getAttribute("verificationCode");
                
                String userVerifCode = request.getParameter("verificationCode");
                
                

                if (verifCode.equals(userVerifCode)) {
                    result.put("Error", 00);
                    result.put("message", "User verified");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                    Storage.getStorage().addUser(tmp.getEmail(), tmp);
                    ss.removeAttribute("candidate");
                    ss.setAttribute("user", tmp);
                    return;
                }

                result.put("Error", 95);
                result.put("message", "User not verified");
                Gson gson = new Gson();
                out.println(gson.toJson(result));

            } else if (servisAdi.equals("driverRegistry")) {
                HttpSession ss = request.getSession(false);
                String model = request.getParameter("model");
                String isDriver = request.getParameter("isDriver");
                String color = request.getParameter("color");
                String plates = request.getParameter("plates");
                String seats = request.getParameter("seats");
                
                if( isDriver != null && Boolean.valueOf(isDriver) && seats != null && Integer.valueOf(seats) > 0 ) {
                    Vehicle vehicle = new Vehicle(model, color, plates, Integer.valueOf(seats));
                    User tmp = (User) ss.getAttribute("user");
                    Driver driver = null;
                    if ( Storage.getStorage().driverExists(tmp.getEmail())) {
                        driver = Storage.getStorage().getDriver(tmp.getEmail());
                    } else {
                        driver = new Driver(tmp);
                        Storage.getStorage().addDriver(tmp.getEmail(), driver);
                    }
                    List<Vehicle> vehicles = driver.getVehicles();
                    vehicles.add(vehicle);
                    driver.setVehicles(vehicles);
                    Storage.getStorage().save();
                    result.put("Error", 00);
                    result.put("message", "Driver registered");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                }
                    result.put("Error", 00);
                    result.put("message", "Driver not registered");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
            } else if ( servisAdi.equals("showCars")) {
                HttpSession ss = request.getSession(false);
                User usr = (User) ss.getAttribute("user");
                if ( Storage.getStorage().driverExists(usr.getEmail())) {
                    Driver tmp = Storage.getStorage().getDriver(usr.getEmail());
                    Gson gson = new Gson();
                    result.put("Error", 00);
                    result.put("message", gson.toJson(tmp.getVehicles()));
                    
                    out.println(gson.toJson(result));
                    return;
                }
                    result.put("Error", 00);
                    result.put("message", "No vehicles");
                    Gson gson = new Gson();
                    out.println(gson.toJson(result));
                
            }
            
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
