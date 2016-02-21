package org.vaadin.samples.helloworld;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SuppressWarnings("serial")
public class HelloWorldUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(new BasicExample());
        tabSheet.addTab(new ListingData());
        tabSheet.addTab(new PersonForm());
        tabSheet.setSizeFull();
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        setContent(tabSheet);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = HelloWorldUI.class)
    public static class Servlet extends VaadinServlet {}

}
