package view;

import integration.entity.Availability;
import integration.entity.Experience;
import shared.DateDTO;
import shared.PublicApplicationDTO;
import view.response.AppRest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfet on 2018-02-15.
 */
public class ConversionService {

    public List<AppRest> convertApplication(List<PublicApplicationDTO> pa) {
        List<AppRest> appRestList = new ArrayList<AppRest>();
        for (PublicApplicationDTO paDTO: pa) {
            AppRest appRest = new AppRest();
            appRest.setUserID(paDTO.getUserID());
            appRest.setName(paDTO.getName());
            appRest.setEmail(paDTO.getEmail());
            appRest.setHired(paDTO.getHired());
            appRest.setRegistrationdate(paDTO.getRegistrationdate());
            appRest.setSsn(paDTO.getSsn());
            appRest.setSurname(paDTO.getSurname());
            appRest.setAvailabilities(paDTO.getAvailabilities());
            appRest.setExperience(paDTO.getExperiences());
            appRestList.add(appRest);
        }
        return appRestList;
    }
    

}
