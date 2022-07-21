package myCode.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ApplicationInitializer implements WebApplicationInitializer {
    //следует перезгрузить метод
    // onStartup -> startup-> start
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("myCode");
        context.refresh();//требует для создания бинов

        final var servlet = new DispatcherServlet(context);//регистрация и настройка сервелета
        final var registration = servletContext.addServlet("app",servlet);//создаение сервлета

        registration.setLoadOnStartup(1);//1-> on 0->off
        registration.addMapping("/");//path
    }
}
