package com.example.demo.Repository;
import com.example.demo.Entity.Cast;
import com.example.demo.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CastsRepository extends CrudRepository<Cast, Integer>
{

}