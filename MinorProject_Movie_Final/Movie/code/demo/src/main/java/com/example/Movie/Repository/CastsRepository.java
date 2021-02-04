package com.example.Movie.Repository;
import com.example.Movie.Entity.Cast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CastsRepository extends CrudRepository<Cast, Integer>
{

}