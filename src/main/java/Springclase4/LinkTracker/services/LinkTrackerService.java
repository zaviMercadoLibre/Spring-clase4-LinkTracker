package Springclase4.LinkTracker.services;

import Springclase4.LinkTracker.entities.LinkDto;
import Springclase4.LinkTracker.exceptionsHandler.InvalidUrl;
import Springclase4.LinkTracker.exceptionsHandler.UrlNotFound;

public interface LinkTrackerService {
    public LinkDto guardarUrl(LinkDto url) throws InvalidUrl;
    public LinkDto redirect(Integer linkId) throws UrlNotFound;
    public LinkDto getMetrics(Integer linkId) throws UrlNotFound;
    public void deleteUrl(Integer linkId) throws UrlNotFound;
}
