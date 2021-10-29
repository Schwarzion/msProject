package msMysql.com.example.msMysql.controllers;

import msMysql.com.example.msMysql.entities.Ville;
import msMysql.com.example.msMysql.repositories.VilleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/villes")
public class VilleController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VilleRepo villeRepo;

    @GetMapping("/{cp}")
    public ResponseEntity<?> findByCodePostal(@PathVariable("cp") String codePostal) {
        log.info("**** Requête reçue pour Ville::findByCodePostal({}) " , codePostal);
        List<Ville> villes = villeRepo.findByCodePostal(codePostal);
        if (villes.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<List<Ville>>(villes, HttpStatus.OK);
    }

    @GetMapping("/departements/{codeDept}")
    public ResponseEntity<?> findByCodeDepartement
            (@PathVariable("codeDept") String codeDepartement) {
        log.info("**** Requête reçue pour Ville::codeDep({})");
        List<Ville> items = villeRepo.findByCodePostalStartingWith(codeDepartement);
        if (items.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<List<Ville>>(items, HttpStatus.OK);
    }


    @PostMapping("/nom")
    public ResponseEntity<?> findByNom(@RequestParam("nom") String nom) {
        System.out.println(nom);
        log.info("**** Requête reçue pour Ville::findByNom({}) " , nom);
        List<Ville> items = villeRepo.findByNomContaining(nom);
        if (items.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<List<Ville>>(items, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Ville ville) {
        log.info("**** Requête reçue pour Ville::add({}) " , ville.getNom());
        log.info(ville.toString());
        Ville newVille = villeRepo.save(ville);
        if (newVille == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newVille.getIdVille()).toUri();
        System.out.println(location);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody Ville newVille, @PathVariable("id") int id) {
        log.info("**** Requête reçue pour Ville::edit({}) " , newVille);
        log.info("Ville");
        Optional<Ville> ville = villeRepo.findById(id);
        if (ville.isPresent()) {
            Ville updateVille = ville.get();
            updateVille.setNom(newVille.getNom());
            updateVille.setPays(newVille.getPays());
            updateVille.setCodePostal(newVille.getCodePostal());
            return new ResponseEntity<>(villeRepo.save(updateVille), HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int idVille)
    {
        log.info("**** Requête reçue pour Ville::delete({}) " , idVille);
        Optional<Ville> ville = villeRepo.findById(idVille);
        if (!ville.isPresent())
            return new ResponseEntity<Ville>(HttpStatus.NOT_FOUND);
        villeRepo.delete(ville.get());
        return new ResponseEntity<>(idVille, HttpStatus.OK);

    }
}