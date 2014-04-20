package com.xiao.service;

import com.xiao.domain.Book;

public class UpperCaseServiceImpl implements UpperCaseService {

	@Override
	public Book toUpperCase(Book book) {
		System.out.println("Servicing book:"+book);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		book.setName(book.getName().toUpperCase());
		return book;
	}
	
}
