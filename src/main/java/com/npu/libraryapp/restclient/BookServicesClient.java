package com.npu.libraryapp.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Invocation.Builder;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.npu.libraryapp.domain.Book;

/* This is code that would appear on the Client side.   Remember that before you can run this, you must
* have your Rest Server running!
*/

public class BookServicesClient {
	
	static private Logger logger = Logger.getLogger(BookServicesClient.class);
	private static String BOOK_SERVICES_URL = "http://localhost:8080/libraryapp/webservices/librestapp/book/";
	private static Client authclient=null;  /* Required for JAX-RS authorization processing -- client will add Authorization Header */
	private static Client client=null;  
	
	public static void main(String args[]) {
		testLookupBook();
		//testDelete();
		//testPut();
		//testPost();
		//testDelete();
	}
	

	/* Client that will add an Authorization header */
	private static Client getClientWithAuth() {
		if (authclient == null) {
			authclient = ClientBuilder.newClient();
			/* Dummy user/password that should be overridden in the actual invocations */
			HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("user", "password");
			authclient.register(authFeature);
		}
		
		return authclient;
	}
	
	/* Client that will not add the authorization header */
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		
		return client;
	}
	
	public static Book testLookupBook() {
		int idToLookup = 200;  /* Just some hardcoded test data */
		int responseCode;
		Book book=null;
		
		Client client = getClient();
		
		//Targeting the RESTful Webservice we want to invoke by capturing it in WebTarget instance.
		WebTarget target = client.target(BOOK_SERVICES_URL + idToLookup);
		
		
		//Building the request i.e a GET request to the RESTful Webservice defined
		//by the URI in the WebTarget instance.
		Invocation getAddrEntryInvocation = target.request(MediaType.APPLICATION_XML_TYPE).buildGet();
		Response response = getAddrEntryInvocation.invoke();
		
		responseCode = response.getStatus();
		logger.info("The response code is: " + responseCode);
		if (responseCode == 200) {
			book = response.readEntity(Book.class);
			logger.info(book);
		}
		
		return book;
	}
	
	/* Using a POST Http Command, we'll add a completely new book */
	public static void testPost() {
		int responseCode;
		Book newBook;
		Client client = getClient();
		
		newBook = createNewBook();
		
		WebTarget target = client.target(BOOK_SERVICES_URL);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.post(Entity.xml(newBook));
		
		responseCode = response.getStatus();
		logger.info("The response code from Post operation is: " + responseCode);
		
		if (responseCode == 201) {
			Book createdBook = response.readEntity(Book.class);
			logger.debug("Book object returned by the POST command: " + createdBook);
		}
	}
	
	public static Book createNewBook() {
		Book book;
		
		book = new Book();
		
		book.setAuthor("Shakespare");
		book.setTitle("Romeo And Juliet");
		book.setBookid(205);
		book.setIsbn(12345);
		return book;
	}
	
	/* Using a PUT Http Command, we'll modify an existing book */
	public static void testPut() {
		int idToModify = 202;  /* Just some hardcoded test data */
		int responseCode;
		Book bookIsbnToChange;
		
		/* First lookup our Book object.   Then we'll make a change.   */
		
		Client client = getClient();
		
		bookIsbnToChange = testLookupBook();
		if (bookIsbnToChange == null) {
			logger.info("Unable to perform the PUT request.  The lookup of book returned null.  ");
			return;
		}
		
		bookIsbnToChange.setIsbn(3005);
		
		WebTarget target = client.target(BOOK_SERVICES_URL + idToModify);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.put(Entity.xml(bookIsbnToChange));
		
		responseCode = response.getStatus();
		logger.info("The response code from Put operation is: " + responseCode);
		
		if (responseCode == 200) {
			Book changedBook = response.readEntity(Book.class);
			logger.debug("Book changed in PUT request: " + changedBook);
		}
	}
	
	/* Using a Delete Http Command, we'll delete an existing book */
	public static void testDelete() {
		int idOfBookToDelete = 200;  /* just some hardcoded test data */
		int responseCode;
		Client client = getClient();
		
		WebTarget target = client.target(BOOK_SERVICES_URL + idOfBookToDelete);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.delete();
		
		responseCode = response.getStatus();
		logger.info("The response code from delete operation is: " + responseCode);
		
		if (responseCode == Status.OK.getStatusCode()) {
			logger.debug("Book removed");
		}
	}
	
}
