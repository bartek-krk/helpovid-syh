package pl.ddcrew.helpovid.utility;

import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.model.Location;

@Service
public class DistanceCalculator {
    public static double calculateDistance(Location l1, Location l2){
        double a = (l1.getLatitude()- l2.getLatitude())*(l1.getLatitude()- l2.getLatitude());
        double b = (l1.getLongitude()-l2.getLongitude())*(l1.getLongitude()-l2.getLongitude());
        double c = Math.sqrt(a+b);
        return c*111; //output in km
    }
}
