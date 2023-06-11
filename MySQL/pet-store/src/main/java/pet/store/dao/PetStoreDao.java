package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import pet.store.entity.PetStore;
@Repository
@Component
public interface PetStoreDao extends JpaRepository<PetStore, Long> {

}
