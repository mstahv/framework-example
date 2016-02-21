package org.vaadin.samples.helloworld;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import java.util.List;

/**
 *
 * @author Matti Tahvonen
 */
public class ListingData extends FrameworkExample {

    PersonService backendService = PersonService.getInstance();

    public ListingData() {
        setCaption("Listing data");

        // List of entities from any Java backend (EJB/JPA/Spring/etc).
        List<Person> personList = backendService.getAllPersons();

        // Create a Grid and data-bind the Person objects.
        Grid grid = new Grid();
        grid.setContainerDataSource(
                new BeanItemContainer<>(Person.class, personList));

        // Define which columns to be displayed.
        grid.setColumns("firstName", "lastName", "email");

        // add components to layout, this class inherits VerticalLayout
        addComponents(grid);
    }

}
