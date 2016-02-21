package org.vaadin.samples.helloworld;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import java.util.Date;

public class PersonForm extends FormLayout {

    // Define the input fields for data.
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField email = new TextField("Email");
    DateField birthdate = new DateField("BirthDate");

    public PersonForm() {
        setCaption("Person form");
        
        addComponents(firstName, lastName, email, birthdate);
        
        // Create a dummy bean to bind to the form
        Person person = new Person();
        person.setFirstName("Henrik");
        person.setLastName("Smith");
        person.setEmail("henrik@smith.com");
        person.setBirthDate(new Date());

        // Use naming convention to bind fields, overrideable with @PropertyId
        BeanFieldGroup.bindFieldsUnbuffered(person, this);
        
        setMargin(true);

    }
}
