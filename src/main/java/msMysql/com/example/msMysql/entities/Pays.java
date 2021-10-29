package msMysql.com.example.msMysql.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code_pays")
    private String codePays;
    private String nom;

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "codePays='" + codePays + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}