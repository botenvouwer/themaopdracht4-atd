/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author yanick
 */
@Stateless
public class ArticleService extends Service<Article, Long> {
    
    public ArticleService() {
        super(Article.class);
    }
    
    public List<Article> getArticles(){
        EntityManager e = getEntityManager();
        Query q = e.createQuery("SELECT a FROM Article a WHERE a.softDelete = FALSE");
        return q.getResultList();
    }
    
    public void deleteArticle(int id) {
        Query q = getEntityManager().createQuery("UPDATE Article a SET a.softDelete = TRUE WHERE a.id= :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    
    public void inboeken(int id, int count) {
        Query q = getEntityManager().createQuery("UPDATE Article a SET a.stock = a.stock + :count WHERE a.id= :id");
        q.setParameter("count", count);
        q.setParameter("id", id);
        q.executeUpdate();
    }
}