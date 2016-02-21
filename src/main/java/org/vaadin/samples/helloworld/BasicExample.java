
package org.vaadin.samples.helloworld;

import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import javax.servlet.ServletContext;

/**
 *
 * @author Matti Tahvonen
 */
public class BasicExample extends FrameworkExample {

    public BasicExample() {
        setCaption("Basic example");
        
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

        // add components to layout, this class inherits VerticalLayout
        addComponents(name, greetButton);
    } 
   
}
