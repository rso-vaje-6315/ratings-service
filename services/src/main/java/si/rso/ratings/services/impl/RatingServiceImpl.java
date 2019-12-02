package si.rso.ratings.services.impl;

import si.rso.ratings.mappers.RatingMapper;
import si.rso.ratings.persistence.RatingEntity;
import si.rso.ratings.services.RatingService;
import si.rso.ratings.lib.Rating;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

@ApplicationScoped
public class RatingServiceImpl implements RatingService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Override
    public Rating getProductRating(String productId){
        TypedQuery<RatingEntity> query = em.createNamedQuery(RatingEntity.FIND_BY_PRODUCT, RatingEntity.class)
                .setParameter("productId", productId);;

        return RatingMapper.fromEntity(query.getSingleResult());

    }

}