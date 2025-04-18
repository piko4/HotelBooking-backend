package com.hb.reservationservice.Service;

import com.hb.reservationservice.Model.Property;
import com.hb.reservationservice.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService
{
    @Autowired
    private PropertyRepository propertyRepository;
    public Property saveProperty(Property property)
    {
        return propertyRepository.save(property);
    }

    public List<Property> getall()
    {
        return propertyRepository.findAll();
    }

    public List<Property> getPropertiesByType(String type)
    {
        return propertyRepository.findByTypeIgnoreCase(type);
    }
}
