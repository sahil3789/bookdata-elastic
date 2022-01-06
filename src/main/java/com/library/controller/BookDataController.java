package com.library.controller;

import com.library.domain.BookData;
import com.library.domain.SearchBookList;
import com.library.service.BookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/library")
public class BookDataController {

    @Autowired
    private BookDataService bookDataService;

    @PostMapping("/book/save")
    public ResponseEntity<BookData> storeData(@RequestBody BookData bookData) {

        BookData book = bookDataService.storeData(bookData);

        if(book==null)
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/book/update")
    public ResponseEntity<BookData> updateBook(@RequestBody BookData bookData) {

        BookData book = bookDataService.updateBook(bookData);

        if(book==null)
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping("/book/remove/{bookId}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Long bookId) {

        Boolean deleted = bookDataService.removeBook(bookId);

        if(deleted)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/book/search/{searchQuery}")
    public ResponseEntity<SearchBookList> getData(@PathVariable String searchQuery){

        List<BookData> bookDataList = bookDataService.getData(searchQuery).get().collect(Collectors.toList());

        SearchBookList searchList =  bookDataService.returnSearchList(bookDataList);

        if(searchList==null)
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(searchList, HttpStatus.OK);
    }
}
