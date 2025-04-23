package com.hb.reservationservice.Controller;

import com.hb.reservationservice.Model.Property;
import com.hb.reservationservice.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    //----------------get by partner id----------------
    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<List<Property>> getPropertiesByPartner(@PathVariable UUID partnerId) {
        List<Property> properties = propertyService.getPropertiesByPartnerId(partnerId);
        if(properties.isEmpty())
        {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }
    //----------------get by location----------------
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Property>> getPropertiesByLocation(@PathVariable String location) {
        List<Property> properties = propertyService.getPropertiesByLocation(location);
        System.out.println("location: " + location);
        if (properties.isEmpty()) {
            return ResponseEntity.noContent().build(); // or you can return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(properties);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable UUID id) {
        Optional<Property> property = propertyService.getById(id);
        if (property.isPresent()) {
            return ResponseEntity.ok(property.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property not found with id: " + id);
        }
    }
}
