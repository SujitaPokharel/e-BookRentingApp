package com.npu.libraryapp.exceptions;

public class BookDaoUnknownTableEntryException extends Exception {
	private static final long serialVersionUID = 1L;
	public BookDaoUnknownTableEntryException(String msg) {
		super(msg);
}
}