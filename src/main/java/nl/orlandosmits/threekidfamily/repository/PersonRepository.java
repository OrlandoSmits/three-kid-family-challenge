package nl.orlandosmits.threekidfamily.repository;

import java.util.List;

import nl.orlandosmits.threekidfamily.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

	List<PersonEntity> findAll();

}
