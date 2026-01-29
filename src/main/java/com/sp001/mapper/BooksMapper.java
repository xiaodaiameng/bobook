package com.sp001.mapper;

import com.sp001.pojo.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BooksMapper {
    Books findByBookname(@Param("keyword") String bookname);
    int insertBook(Books books);
    int deleteBook(@Param("id") int id);
    Books getBookById(@Param("id") Integer id);
    List<Books> findAllBooks();
    int updateBook(Books books);
    List<Books> findBooksBySellerId(@Param("sellerId") int sellerId);

}