package com.npu.libraryapp.exceptions;

public class BookDaoDuplicateTableEntryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookDaoDuplicateTableEntryException(String msg) {
		super(msg);
	}
}
