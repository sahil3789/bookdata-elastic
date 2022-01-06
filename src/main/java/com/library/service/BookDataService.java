package com.library.service;

import com.library.domain.BookData;
import com.library.domain.SearchBookList;
import com.library.repository.BookDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDataService {

    @Autowired
    private BookDataRepository bookDataRepository;

    public BookData storeData(BookData bookData) {

        try {
            return bookDataRepository.save(bookData);
        }
        catch (Exception e) {
            return null;
        }
    }

    public BookData updateBook(BookData bookData){

        try {
            bookDataRepository.deleteByBookId(bookData.getBookId());

            BookData book = new BookData();
            book.setBookId(bookData.getBookId());
            book.setContent(bookData.getContent());
            bookDataRepository.save(book);

            return book;
        }
        catch (Exception e){
            return null;
        }
    }

    public Boolean removeBook(Long bookId){

        try {
            bookDataRepository.deleteByBookId(bookId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public SearchBookList returnSearchList(List<BookData> bookDataList){

        SearchBookList searchBookLists = new SearchBookList();

        try {
            for (BookData book : bookDataList) {
                if (searchBookLists.getBookDataList() == null) {
                    List<BookData> bData = new ArrayList<>();
                    bData.add(book);
                    searchBookLists.setBookDataList(bData);
                } else {
                    searchBookLists.getBookDataList().add(book);
                }
            }
            return searchBookLists;
        }
        catch (Exception e){
            return null;
        }
    }

    public Page<BookData> getData(String query) {

        Pageable pageable = PageRequest.of(0, 100);
        try {
            return bookDataRepository.findAllByContent(query, pageable);
        }
        catch (Exception e){
            return null;
        }
    }

}