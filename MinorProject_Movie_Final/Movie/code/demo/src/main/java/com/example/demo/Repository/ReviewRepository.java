package com.example.demo.Repository;

import com.example.demo.Entity.Cast;
import com.example.demo.Entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends CrudRepository<Review, Integer>
{

}
