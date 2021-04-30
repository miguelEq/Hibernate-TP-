package ar.edu.unq.epers.bichomon.backend.hibernate.impl;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;


public class HibernateBichoDAO extends HibernateDAO<Bicho> implements BichoDAO {

    public HibernateBichoDAO() {
        super(Bicho.class);
    }

}
