package com.npu.libraryapp.exceptions;

public class BookDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BookDaoException(String msg) {
		super(msg);
	}
}
