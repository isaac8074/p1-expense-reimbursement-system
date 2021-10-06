package controllers;

import javax.servlet.http.HttpSession;

import io.javalin.Javalin;
import io.javalin.http.Handler;


import models.Employees;
import service.EmployeeService;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class EmployeeController {
    private EmployeeService employeeService;
    public Employees employees;

    public EmployeeController(Javalin app)  {
        this.employeeService = new EmployeeService();
        app.routes(() ->  {
            path("/employee", () ->  {
                path("/username", () ->  {
                    get(loginRef);
                });
                path("/login", () ->  {
                    post(loginDir);
                });
            });
        });
    }

    private Handler loginRef = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        employees = this.employeeService.getByUsername(ctx.pathParam("username"));
        ctx.json(employees);
    };

    private Handler loginDir = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        employees = this.employeeService.getByUsername(ctx.req.getParameter("username"));
        if(employees.getEmployee_password().equals(ctx.req.getParameter("password")) && employees.getEmployee_role().equals("Employee"))    {
            ctx.redirect("/home.html");
        }
        else if(employees.getEmployee_password().equals(ctx.req.getParameter("password")) && employees.getEmployee_role().equals("Manager"))   {
            ctx.redirect("/managerhome.html");
        }
        else{
            ctx.redirect("/loginpage.html");
        }
    };
}
