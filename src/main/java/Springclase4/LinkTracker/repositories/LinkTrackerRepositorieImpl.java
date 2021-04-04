package Springclase4.LinkTracker.repositories;

import Springclase4.LinkTracker.entities.LinkDto;
import Springclase4.LinkTracker.exceptionsHandler.InvalidUrl;
import Springclase4.LinkTracker.exceptionsHandler.UrlNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class LinkTrackerRepositorieImpl implements LinkTrackerRepositorie{

    private final Map<Integer,LinkDto> urlList = new HashMap<>();

    @Override
    public LinkDto findUrlbyId(Integer idLink) throws UrlNotFound{
        LinkDto urlFind = urlList.get(idLink);
        if(urlFind == null)
            throw new UrlNotFound("no se encontro la url con el id solicitado");
        else{
            urlList.get(idLink).setCount();
            return urlFind;
        }

    }

    @Override
    public LinkDto save(LinkDto url) throws InvalidUrl {
        if(!urlValidator(url)){
            throw new InvalidUrl(url.getUrl());
        }else {
            if(url.getLinkId() == null){
                url.setLinkId(urlList.size());
            }
            urlList.put(url.getLinkId(),url);
        }
        return url;
    }

    @Override
    public LinkDto findUrlMetrics(Integer idLink) throws UrlNotFound {
        LinkDto urlFind = urlList.get(idLink);

        if(urlFind ==  null)
            throw new UrlNotFound("no se pudieron obtener datos de la url solicitada ");
        else
             return urlFind;
    }

    public boolean urlValidator(LinkDto url)
    {
        String URL_REGEX = "(?i)^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?$"; // matches <http://google.com>

        Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
        String cadena = url.getUrl();
        if(url == null) return false;

        Matcher matcher = URL_PATTERN.matcher(cadena);
        boolean check = matcher.matches();
        return check;
    }

    @Override
    public void deleteUrlById(Integer idLink) throws UrlNotFound {
        LinkDto urlFind = urlList.remove(idLink);
        if(urlFind ==  null)
            throw new UrlNotFound("no se pudo eliminar la url con el id ingresado ");

    }
}
