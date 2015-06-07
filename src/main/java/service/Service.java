/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.validate.ErrorList;
import domain.validate.Validate;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author william
 * @param <E> entity type, bijvoorbeeld {@link User}
 * @param <ID> type van de {@link Id} van de entitrit, bijvoorbeeld
 * {@link User#id}'s type
 */
public abstract class Service<E, ID> {

    private final Class<E> entityClass;

    protected Service(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public E find(ID id) {
        return getEntityManager().find(entityClass, id);
    }

    public ErrorList create(E entity) {
        
        ErrorList e = new ErrorList();
        if(entity instanceof Validate){
            e = ((Validate)entity).validate();
        }
        
        if(e.isValid()){
            getEntityManager().persist(entity);
        }
        
        return e;
    }

    public ErrorList update(E entity) {
        ErrorList e = new ErrorList();
        if(entity instanceof Validate){
            e = ((Validate)entity).validate();
        }
        
        if(e.isValid()){
            getEntityManager().merge(entity);
        }
        
        return e;
    }

    public void delete(E entity) {
        getEntityManager().remove(entity);
    }
}
