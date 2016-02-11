package org.vaadin.samples.helloworld;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.ServletContext;

@Theme("valo")
@SuppressWarnings("serial")
public class HelloWorldUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        // All of this code is running in your server's JVM
        // Create a TextField input.
        TextField name = new TextField("Your Name", "Vaadin");

        // Access any server-side API directly.
        ServletContext servletContext = VaadinServlet.getCurrent().
                getServletContext();
        String serverName = servletContext.getServerInfo();

        // Create a Button and define a server-side click listener.
        Button greetButton = new Button("Greet the Server");
        greetButton.addClickListener(event
                -> Notification.show("Hello " + name.getValue() + "!\n"
                        + "I'm " + serverName));

        // Display TextField and Button vertically.
        VerticalLayout layout = new VerticalLayout(name, greetButton);

        layout.setSpacing(true);
        layout.setMargin(true);
        setContent(layout);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = HelloWorldUI.class)
    public static class Servlet extends VaadinServlet {}

}
