package org.vaadin.samples.helloworld;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link PersonService#getInstance()}.
 */
public class PersonService {

	private static PersonService instance;
	private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

	private final HashMap<Long, Person> contacts = new HashMap<>();
	private long nextId = 0;

	private PersonService() {
	}

	/**
	 * @return a reference to an example facade for Person objects.
	 */
	static PersonService getInstance() {
		if (instance == null) {
			instance = new PersonService();
			instance.ensureTestData();
		}
		return instance;
	}

	/**
	 * @return all available Person objects.
	 */
	public synchronized List<Person> findAll() {
		return findAll(null);
	}

	/**
	 * Finds all Person's that match given filter.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @return list a Person objects
	 */
	public synchronized List<Person> findAll(String stringFilter) {
		ArrayList<Person> arrayList = new ArrayList<>();
		for (Person contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	/**
	 * Finds all Person's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Person objects
	 */
	public synchronized List<Person> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Person> arrayList = new ArrayList<>();
		for (Person contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return contacts.size();
	}

	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Person to be deleted
	 */
	public synchronized void delete(Person value) {
		contacts.remove(value.getId());
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
 for new Person instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Person entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Person) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
					"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
					"Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
					"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
					"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
					"Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
					"Jaydan Jackson", "Bernard Nilsen" };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Person c = new Person();
				c.setFirstName(split[0]);
				c.setLastName(split[1]);
				c.setEmail(split[0].toLowerCase() + "@" + split[1].toLowerCase() + ".com");
				Calendar cal = Calendar.getInstance();
				int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
				cal.add(Calendar.DAY_OF_MONTH, daysOld);
				c.setBirthDate(cal.getTime());
				save(c);
			}
		}
	}

    List<Person> getAllPersons() {
        return findAll();
    }

}