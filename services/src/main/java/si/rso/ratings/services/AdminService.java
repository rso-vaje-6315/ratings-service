package si.rso.ratings.services;

import si.rso.ratings.lib.Rating;

import java.util.List;

public interface AdminService {
    
    List<Rating> populateData();
    
    void removeData();
}
