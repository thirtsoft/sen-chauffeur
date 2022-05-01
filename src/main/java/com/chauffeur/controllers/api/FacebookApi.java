package com.chauffeur.controllers.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface FacebookApi {

    @GetMapping(value = APP_ROOT + "/facebooks/NumberOfPagesLikes")
    @ApiOperation(value = "Decompter le nombre total de mention j'aime",
            notes = "Cette méthode permet de compter et d'afficher le nombre total mention j'aime de la page sunuchauffeur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de j'aime"),
            @ApiResponse(code = 400, message = "Aucun liste Annonce")

    })
    int getNumberOfPagesLikes() throws IOException, JSONException;

    @GetMapping(value = APP_ROOT + "/facebooks/NumberOfPagesFollowers")
    @ApiOperation(value = "Decompter le nombre total d'Abonnées",
            notes = "Cette méthode permet de compter et d'afficher le nombre total d'Abonnées sur la page sunuchauffeur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Abonnées"),
            @ApiResponse(code = 400, message = "Aucun liste Abonnées")

    })
    int countNumberOfPagesFollowers() throws IOException, JSONException;


}
