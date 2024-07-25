package Ada_School.Proyecto_Integrador.repository;

import Ada_School.Proyecto_Integrador.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {}
