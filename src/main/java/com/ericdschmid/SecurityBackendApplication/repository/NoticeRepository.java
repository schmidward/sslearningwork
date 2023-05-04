package com.ericdschmid.SecurityBackendApplication.repository;

import java.util.List;

import com.ericdschmid.SecurityBackendApplication.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    //This annotation lets us write a SQL query with help of the entity class. Mentioning the class and field names instead of column names
    //This says please fetch all the notices where the current date is between the notice's begin and end date
    @Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
    List<Notice> findAllActiveNotices();

}
