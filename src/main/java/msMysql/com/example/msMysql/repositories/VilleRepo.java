package msMysql.com.example.msMysql.repositories;

import msMysql.com.example.msMysql.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepo extends JpaRepository<Ville, Integer> {
    List<Ville> findByCodePostal(String codePostal);
    List<Ville> findByNomContaining(String nom);
    List<Ville> findByCodePostalStartingWith(String codeDep);
}