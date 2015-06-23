/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.validate.DomainError;
import domain.validate.ErrorList;
import domain.validate.Validate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author yanick
 */
@Entity
public class UsedArticle implements Serializable, Validate {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Article article;
    private int count;
    
    public UsedArticle() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsedArticle)) {
            return false;
        }
        
        UsedArticle other = (UsedArticle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("domain.UsedArticle[ id= %s ]", id);
    }

    @Override
    public ErrorList validate() {
        ErrorList list = new ErrorList();
        
        if(count <= 0){
            list.setError(new DomainError("countError", "Gebruikt artikel aantal moet positief zijn"));
        }
        else if(count > article.getStock() && id == null){
            list.setError(new DomainError("countError", String.format("Er is niet genoeg voorraad (in voorraad %s - %s)", article.getStock(), article.getName())));
        }
        
        return list;
    }
}
