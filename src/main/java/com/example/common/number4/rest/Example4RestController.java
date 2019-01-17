/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.number4.rest;

import com.example.common.number3.rest.*;
import com.example.common.number3.domen.Player;
import com.example.common.number3.domen.Team;
import com.example.common.number3.rest.*;
import com.example.common.number4.domen.Match;
import com.example.common.number4.domen.Place;
import com.example.common.number4.domen.Referee;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.module.jsonSchema.customProperties.TitleSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;
import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.google.common.collect.HashBiMap;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CECA
 */
// Ako ne stavim multiselect na true onda ne mogu da pretrazujem select polje!!!!!!!!!!!!!!
@RestController
public class Example4RestController {

    private static ObjectMapper mapper = new ObjectMapper();
    public static final String JSON_$SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_$SCHEMA_ELEMENT = "$schema";

    @RequestMapping(value = "/schemaExample4", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object get() {

        try {
            JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
            schemaFactory.setAutoPutDollarSchema(true);
            JsonNode productSchema = schemaFactory.createSchema(Match.class);
            System.out.println(productSchema);

            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(productSchema));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/formExample4", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getForm() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode form = mapper1.createObjectNode();
            ObjectNode formIdMatch = mapper1.createObjectNode();
            ObjectNode formDateOfMatch = mapper1.createObjectNode();
            ObjectNode formDurationOfMatch = mapper1.createObjectNode();
            ObjectNode formTeam1 = mapper1.createObjectNode();
            ObjectNode formTeam2 = mapper1.createObjectNode();
            ObjectNode formReferee = mapper1.createObjectNode();
            ObjectNode formPlace = mapper1.createObjectNode();
            ArrayNode order = mapper1.createArrayNode();

            ObjectNode formTeam1Id = mapper1.createObjectNode();
            ObjectNode formTeam2Id = mapper1.createObjectNode();
            ObjectNode formRefereeId = mapper1.createObjectNode();
            ObjectNode formPlaceId = mapper1.createObjectNode();

            ObjectNode formStatsOfMatch = mapper1.createObjectNode();
            ObjectNode table = mapper1.createObjectNode();
            ArrayNode tableCols = mapper1.createArrayNode();
            ArrayNode deleteCols = mapper1.createArrayNode();
            ObjectNode formStatsId = mapper1.createObjectNode();
            ObjectNode formStatsDescription = mapper1.createObjectNode();
            ObjectNode formStatsTime = mapper1.createObjectNode();
            ObjectNode action = mapper1.createObjectNode();

            formIdMatch.put("ui:widget", "updown");

            formDurationOfMatch.put("ui:widget", "updown");

            formTeam1Id.put("ui:widget", "updown");
            formTeam1.putPOJO("id", formTeam1Id);

            formTeam2Id.put("ui:widget", "updown");
            formTeam2.putPOJO("id", formTeam2Id);

            formRefereeId.put("ui:widget", "updown");
            formReferee.putPOJO("id", formRefereeId);

            formPlaceId.put("ui:widget", "updown");
            formPlace.putPOJO("id", formPlaceId);

            formStatsOfMatch.put("ui:field", "table");
            formStatsOfMatch.put("ui:title", "Stats of match");

            formStatsId.put("dataField", "id");
            formStatsId.put("dataAlign", "center");
            formStatsId.put("displayName", "Id sta");
            formStatsId.put("ui:widget", "updown");

            formStatsDescription.put("dataField", "sdescription");
            formStatsDescription.put("dataAlign", "center");
            formStatsDescription.put("displayName", "Description");
            
            formStatsTime.put("dataField", "time");
            formStatsTime.put("dataAlign", "center");
            formStatsTime.put("displayName", "Time");
            formStatsTime.put("ui:widget", "updown");

            tableCols.add(formStatsId);
            tableCols.add(formStatsDescription);
            tableCols.add(formStatsTime);

            action.put("action", "delete");
            action.put("displayName", "Delete");
            action.put("icon", "glyphicon glyphicon-minus");
            action.put("dataAlign", "center");

            deleteCols.add(action);

            table.putPOJO("tableCols", tableCols);
            table.putPOJO("rightActions", deleteCols);
            formStatsOfMatch.putPOJO("table", table);

            order.add("id");
            order.add("dateAndTime");
            order.add("duration");
            order.add("statsOfMatch");
            order.add("team1");
            order.add("team2");
            order.add("place");
            order.add("*");

            form.putPOJO("ui:order", order);
            form.putPOJO("id", formIdMatch);
            form.putPOJO("duration", formDurationOfMatch);
            form.putPOJO("team1", formTeam1);
            form.putPOJO("team2", formTeam2);
            form.putPOJO("referee", formReferee);
            form.putPOJO("place", formPlace);
            form.putPOJO("statsOfMatch", formStatsOfMatch);
            return ResponseEntity.status(HttpStatus.OK).body(form);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/modelExample4", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getModel() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode model = mapper1.createObjectNode();
            ArrayNode statsOfMatch = mapper1.createArrayNode();
            
            ObjectNode stats1 = mapper1.createObjectNode();
            ObjectNode stats2 = mapper1.createObjectNode();
            stats1.put("id", 1);
            stats1.put("sdescription", "Foul");
            stats1.put("time", 23);
            
            stats2.put("id", 2);
            stats2.put("sdescription", "Yellow card");
            stats2.put("time", 25);
            
            statsOfMatch.add(stats1);
            statsOfMatch.add(stats2);
            
            model.put("id", 1);
            model.putPOJO("statsOfMatch", statsOfMatch);
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }
}
