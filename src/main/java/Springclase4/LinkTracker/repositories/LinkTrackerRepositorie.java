package Springclase4.LinkTracker.repositories;

import Springclase4.LinkTracker.entities.LinkDto;
import Springclase4.LinkTracker.exceptionsHandler.InvalidUrl;
import Springclase4.LinkTracker.exceptionsHandler.UrlNotFound;

public interface LinkTrackerRepositorie {
    public LinkDto findUrlbyId(Integer idLink) throws UrlNotFound;
    public LinkDto save(LinkDto url) throws InvalidUrl;
    public LinkDto findUrlMetrics(Integer idLink) throws UrlNotFound;
    boolean urlValidator(LinkDto url);
    public void deleteUrlById(Integer idLink) throws UrlNotFound;
}
