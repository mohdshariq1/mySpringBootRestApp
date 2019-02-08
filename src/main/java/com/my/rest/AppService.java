package com.my.rest;


import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface AppService extends CrudRepository<AppModel, Long> {

    @Query("select a from AppModel a where a.company = :company")
    Stream<AppModel> findCompany(@Param("company") String company);

    List<AppModel> findByCompany( String company);

}
