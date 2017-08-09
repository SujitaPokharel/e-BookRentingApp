package com.npu.libraryapp.resthandlers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.npu.libraryapp.domain.Book;
import com.npu.libraryapp.exceptions.UnknownResourceException;
import com.npu.libraryapp.services.BookService;

@Path("/librestapp")
public class BookRestHandler {
	@Autowired
	private BookService bookService;
	private Logger logger = Logger.getLogger(BookRestHandler.class);
	
	/* Test Url:
	 * http://localhost:8080/zuniversity/webservices/studrestapp/book/200
	 * See web.xml file for Jersey configuration
	 */
	
	@GET
	@Path("/book/{title}")
	@Produces("application/xml, application/json")
	public Book getBook(@PathParam("bookid") String title) {
		Book book = null;
		
		book = lookupBook(title);
		if (book == null) {
			throw new UnknownResourceException("Book title: " + title + " is invalid");
		}
		
		return book;
	}

	private Book lookupBook(String title) {
		Book book;

		book = bookService.findBookDetails(title);
		if (book == null) {
			throw new UnknownResourceException("Book title: " + title + " is invalid");
		}

		return book;
	}

	@POST
	@Path("/book")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addBook(Book newBook) {
		ResponseBuilder respBuilder;
		
		bookService.addNewBook(newBook);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newBook);
		return respBuilder.build();
	}
	
	/* Test Url:  Use HTTP Delete command
	 * http://localhost:8080/libraryapp/webservices/librestapp/book/java
	 */
	@DELETE
	@Path("/book/{title}")
	public Response deleteBook(@PathParam("title") String title) {
		boolean removeBook =  true;
		ResponseBuilder respBuilder;
		
		//removeBook = bookService.deleteBook(title);
		if (removeBook ) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
		return respBuilder.build();
	}
	

	/* Test Url -- Put (HTTP Put Command) the data from file book.xml to:
	 * http://localhost:8080/libraryapp/webservices/librestapp/book/202
	 * After doing the post, use a get command to retrieve the book (and verify that the post was successful).
	 */
	@PUT
	@Path("/book/{bookid}")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response updateBook(Book newBook, @PathParam("bookid") int id) {
		ResponseBuilder respBuilder;
		boolean updatedBook = true;
		
		//updatedBook = bookService.updateRecord(newBook, id);
		if (updatedBook) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.status(Status.OK);
			respBuilder.entity(updatedBook);
		}
		
		return respBuilder.build();
	}
}
