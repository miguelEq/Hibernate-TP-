package ar.edu.unq.epers.bichomon.backend.hibernate.impl;

import ar.edu.unq.epers.bichomon.backend.service.runner.TransactionRunner;
import org.hibernate.Session;

public class HibernateDAO<T> {

    private Class<T> entityType;

    public HibernateDAO(Class<T> entityType){
        this.entityType = entityType;
    }

    public void guardar(T item) {
        Session session = TransactionRunner.getCurrentSession();
        session.save(item);
    }

    public T recuperar(Long id) {
        Session session = TransactionRunner.getCurrentSession();
        return this.validacion(session.get(entityType, id));
    }

    public T validacion(T tipe){
        if(tipe==null){
            throw  new RuntimeException("No esta persistido en la BD");
        }else{
            return tipe;
        }

    }

    public void  actualizar(T item){
        Session session = TransactionRunner.getCurrentSession();
        session.update(item);
    }
}
