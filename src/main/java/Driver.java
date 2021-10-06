import controllers.EmployeeController;
import controllers.ReimbursementController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import javax.servlet.http.HttpSession;

public class Driver {
    public static void main(String... args) {
        Javalin app = Javalin.create().start(7000);
        app.post("/login", ctx -> {
            ctx.req.getSession();
        });

        app.get("/logout", ctx -> {
            HttpSession session = ctx.req.getSession(false);
            if(session != null) session.invalidate();
        });

        app.after(ctx -> {
            ctx.res.addHeader("Access-Control-Allow-Origin", "null");
        });

        app.config.addStaticFiles("/static", Location.CLASSPATH);

        ReimbursementController reimbursementController = new ReimbursementController(app);
        EmployeeController employeeController = new EmployeeController(app);
    }
}
