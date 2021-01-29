package com.example.demo.Repository;

import com.example.demo.Entity.Movie;
import com.example.demo.Entity.StreamingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StreamingDetailsRepository extends CrudRepository<StreamingDetails, Integer>
{

}