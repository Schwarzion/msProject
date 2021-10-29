package msMysql.com.example.msMysql.entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Ville implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_ville  ")
    private int idVille;
    @Column(name="code_postal")
    private String codePostal;
    private String nom;
    @ManyToOne
    @JoinColumn(name="code_pays")
    private Pays pays;

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", codePostal='" + codePostal + '\'' +
                ", nom='" + nom + '\'' +
                ", pays=" + pays +
                '}';
    }
}