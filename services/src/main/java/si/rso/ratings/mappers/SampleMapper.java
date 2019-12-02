package si.rso.ratings.mappers;

import si.rso.ratings.lib.Sample;
import si.rso.ratings.persistence.SampleEntity;

public class SampleMapper {
    
    public static Sample fromEntity(SampleEntity entity) {
        return new Sample();
    }
    
}