package com.hb.partnerservice.Controller;

import com.hb.partnerservice.Model.Partner;
import com.hb.partnerservice.Service.PartnerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController
{


    @Autowired
    private PartnerService partnerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Partner partner)
    {
        partnerService.registerPartner(partner);
        return "Partner registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Partner partner, HttpSession session)
    {
        Optional<Partner> existingPartner = partnerService.findByEmail(partner.getEmail());

        if (existingPartner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }

        Partner foundPartner = existingPartner.get();
        boolean passwordMatches = passwordEncoder.matches(partner.getPassword(), foundPartner.getPassword());

        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }

        session.setAttribute("partner", foundPartner); // Store partner in session

        // Return partner details (excluding sensitive info like password)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("partner", Map.of(
                "id", foundPartner.getId(),
                "partnername", foundPartner.getPartnername(),
                "email", foundPartner.getEmail()
        ));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session)
    {
        if (session.getAttribute("partner") == null) {
            System.out.println("alerady logged out!");
            return ResponseEntity.ok("alerady logged out");
        }
        session.invalidate();
        System.out.println("logout successfully!");
        return ResponseEntity.ok("Partner logged out successfully!");
    }


    @GetMapping("/partner")
    public ResponseEntity<?> getPartner(HttpSession session)
    {
        Partner partner = (Partner) session.getAttribute("partner");
        if (partner != null) {
            Partner updatedPartner = partnerService.findByID(partner.getId());
            return ResponseEntity.ok(updatedPartner);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No partner logged in");
    }


}
