package controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import models.Reimbursement;
import service.ReimbursementService;

import javax.servlet.http.HttpSession;

import java.util.Locale;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.path;

public class ReimbursementController {
    private ReimbursementService reimbursementService;

    public ReimbursementController(Javalin app) {
        this.reimbursementService = new ReimbursementService();
        app.routes(() ->  {
            path("/reimbursement", () ->  {
                path("/all", () ->  {
                    get(findAllReimbursements);
                });
                path("/new", () ->  {
                    post(saveReimbursement);
                });
                path("/:name", () ->  {
                    get(findByName);
                });
                path("/approve/:id", () ->  {
                    post(approve);
                });
                path("/deny/:id", () ->  {
                    post(deny);
                });
            });
        });
    }

    private Handler findAllReimbursements = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        ctx.json(this.reimbursementService.findAll());
    };

    private Handler saveReimbursement = ctx -> {
        Reimbursement reimbursement = new Reimbursement(1,
                Double.parseDouble(ctx.req.getParameter("reimbursement_amount")),
                ctx.req.getParameter("reimbursement_description"),
                ctx.req.getParameter("employee_name"),
                "Pending");

        this.reimbursementService.save(reimbursement);
        this.reimbursementService.findAll();
        ctx.redirect("/home.html");
    };

    private Handler findByName = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        ctx.json(this.reimbursementService.findName(ctx.pathParam("name")));
    };

    private Handler approve = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        this.reimbursementService.approve(Integer.parseInt(ctx.pathParam("id")));
    };

    private Handler deny = ctx -> {
        HttpSession session = ctx.req.getSession(false);
        this.reimbursementService.deny(Integer.parseInt(ctx.pathParam("id")));
    };
}
