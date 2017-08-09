package com.npu.libraryapp.exceptions;

public class BookDaoRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BookDaoRuntimeException(String msg) {
		super(msg);

	}
}