package Springclase4.LinkTracker.controllers;

import Springclase4.LinkTracker.entities.ErrorDto;
import Springclase4.LinkTracker.entities.LinkDto;
import Springclase4.LinkTracker.exceptionsHandler.InvalidUrl;
import Springclase4.LinkTracker.exceptionsHandler.UrlNotFound;
import Springclase4.LinkTracker.services.LinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LinkTrackerController {

    @Autowired
    private LinkTrackerService service ;

    @PostMapping("crear")
    public LinkDto crearUrl(@RequestBody LinkDto url) throws InvalidUrl {
        return service.guardarUrl(url);
    }

    @PostMapping("crear/url")
    public LinkDto crearUrlPassword(@RequestBody LinkDto url, @RequestParam String pass) throws InvalidUrl{

        if(pass != null)
            url.setPassword(pass);
        else
            url.setUrl("");

        return service.guardarUrl(url);
    }

    @GetMapping("link/{linkId}")
    public void redirect(@PathVariable Integer linkId, HttpServletResponse response) throws UrlNotFound, IOException {
        LinkDto link = service.redirect(linkId);
        if (link != null){
            response.sendRedirect(link.getUrl());
        } else {
            response.sendError(404);
        }
    }

    @GetMapping("metrics/{linkId}")
    public LinkDto metrics(@PathVariable Integer linkId) throws UrlNotFound{
         LinkDto url = service.getMetrics(linkId);
         return url;
    }

    @PostMapping("delete/{linkId}")
    public void deleteUrl(@PathVariable Integer linkId) throws UrlNotFound{
        service.deleteUrl(linkId);
    }

    @ExceptionHandler(InvalidUrl.class)
    public ResponseEntity<ErrorDto> invalidUrl(InvalidUrl err){
        ErrorDto exception = new ErrorDto();
        exception.setName("Url invalida");
        exception.setDescription("La Url : "+ err.getMessage() + " no es una url valida");
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(UrlNotFound.class)
    public  ResponseEntity<ErrorDto> urlNotFound(UrlNotFound err){
        ErrorDto exception = new ErrorDto();
        exception.setName("No se encontro la url ");
        exception.setDescription( err.getMessage());
        return ResponseEntity.badRequest().body(exception);
    }


}
