package Springclase4.LinkTracker.services;

import Springclase4.LinkTracker.entities.LinkDto;
import Springclase4.LinkTracker.exceptionsHandler.InvalidUrl;
import Springclase4.LinkTracker.exceptionsHandler.UrlNotFound;
import Springclase4.LinkTracker.repositories.LinkTrackerRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkTrackerServiceImpl implements  LinkTrackerService{

    @Autowired
    private LinkTrackerRepositorie linkTracker;

    @Override
    public LinkDto guardarUrl(LinkDto url) throws InvalidUrl {
           return linkTracker.save(url);
    }

    @Override
    public LinkDto redirect(Integer linkId) throws UrlNotFound {
        return linkTracker.findUrlbyId(linkId);
    }

    @Override
    public LinkDto getMetrics(Integer linkId) throws UrlNotFound{
        return linkTracker.findUrlMetrics(linkId);
    }

    @Override
    public void deleteUrl(Integer linkId) throws UrlNotFound{
        linkTracker.deleteUrlById(linkId);
    }


}
