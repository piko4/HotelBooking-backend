package com.hb.reservationservice.Controller;

import com.hb.reservationservice.Model.Property;
import com.hb.reservationservice.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController
{
    @Autowired
    private PropertyService propertyService;
    //---------------- add a property ----------------
    @PostMapping("/add")
    public ResponseEntity<Property> addProperty(@RequestBody Property property)
    {
        Property savedProperty = propertyService.saveProperty(property);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }
    //---------------- get all properties by type ----------------
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Property>> getPropertiesByType(@PathVariable String type) {
        List<Property> properties = propertyService.getPropertiesByType(type);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
    //---------------- get all properties ----------------
    @GetMapping("/getall")
    public ResponseEntity<List<Property>> getProperties(){
        List<Property> properties=propertyService.getall();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
