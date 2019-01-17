/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.number2.rest;

import com.example.common.number1.rest.*;
import com.example.common.number2.domen.Player;
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
import java.util.HashMap;
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
@RestController
public class Example2RestController {

    private static ObjectMapper mapper = new ObjectMapper();
    public static final String JSON_$SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_$SCHEMA_ELEMENT = "$schema";

    @RequestMapping(value = "/schemaExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object get() {

        try {
            JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
            schemaFactory.setAutoPutDollarSchema(true);
            JsonNode productSchema = schemaFactory.createSchema(Player.class);
            System.out.println(productSchema);

            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(productSchema));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/formExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getForm() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode form = mapper1.createObjectNode();
            ObjectNode formIdPlayer = mapper1.createObjectNode();
            ObjectNode formHeight = mapper1.createObjectNode();
            ObjectNode formWeight = mapper1.createObjectNode();
            ObjectNode formName = mapper1.createObjectNode();
            ObjectNode formSurname = mapper1.createObjectNode();
            ObjectNode formDateOfBirth = mapper1.createObjectNode();
            ObjectNode formPlayerStatusRadio = mapper1.createObjectNode();
            ObjectNode formPlayerStatusSelect = mapper1.createObjectNode();
            ObjectNode formPlayerStatusCheck = mapper1.createObjectNode();
            ArrayNode optionLabels = mapper1.createArrayNode();
            ObjectNode formPlayerAttributes = mapper1.createObjectNode();
            ArrayNode formOrder = mapper1.createArrayNode();
            ObjectNode datasets = mapper1.createObjectNode();

            
            formPlayerAttributes.put("ui:widget", "textarea");
            
            formPlayerStatusRadio.put("ui:widget", "radio");
            
            formPlayerStatusSelect.put("ui:widget", "select");
                   
            //formPlayerStatusCheck.put("ui:widget", "checkboxes");
            
            formIdPlayer.put("ui:autofocus", true);
            formIdPlayer.put("ui:readonly", true);
            formIdPlayer.put("ui:widget", "updown");
            formIdPlayer.put("ui:placeholder", "Id player...");
            
            formName.put("ui:widget", "text");
            formName.put("ui:placeholder", "Name player...");
            
            formSurname.put("ui:placeholder", "Surname player...");
            formSurname.put("ui:widget", "text");
            
            formHeight.put("ui:placeholder", "Height player...");
            formHeight.put("ui:widget", "updown");

            formWeight.put("ui:widget", "updown");
            formWeight.put("ui:placeholder", "Weight player...");
            
            formOrder.add("idPlayer");
            formOrder.add("dateOfBirth");            
            formOrder.add("name");
            formOrder.add("surname");
            formOrder.add("height");
            formOrder.add("weight");
            formOrder.add("*");
           
            form.putPOJO("idPlayer",formIdPlayer);
            form.putPOJO("height",formHeight);
            form.putPOJO("weight",formWeight);
            form.putPOJO("name",formName);
            form.putPOJO("surname",formSurname);
            form.putPOJO("playerAttributes",formPlayerAttributes);
            form.putPOJO("playerStatusRadio",formPlayerStatusRadio);
            form.putPOJO("playerStatusSelect",formPlayerStatusSelect);
            form.putPOJO("playerStatusCheck",formPlayerStatusCheck);
            
            form.putPOJO("ui:order", formOrder);
            return ResponseEntity.status(HttpStatus.OK).body(form);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/modelExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getModel() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode model= mapper1.createObjectNode();
            model.put("idPlayer", 1);
            model.put("playerStatusSelect", "active");
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

}
