package com.library.repository;

import com.library.domain.BookData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDataRepository extends ElasticsearchRepository<BookData,Long> {

    @Query("{\"match\":{\"content\":{\"query\":\"habits\"}}}")
    Page<BookData> findAllByContent(String query, Pageable pageable);

    @Query("{\"match\":{\"bookId\":{\"query\":\1}}}")
    void deleteByBookId(Long bookId);

}
