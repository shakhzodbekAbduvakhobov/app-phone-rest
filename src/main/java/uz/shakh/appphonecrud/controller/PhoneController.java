package uz.shakh.appphonecrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shakh.appphonecrud.entity.Phone;
import uz.shakh.appphonecrud.service.PhoneService;

import java.util.List;

@RestController
@RequestMapping("/api/phone/")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @GetMapping
    public HttpEntity<?> getAllPhone(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        List<Phone> allPhone = phoneService.getAllPhone(page, size);
        return ResponseEntity.ok(allPhone);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPhoneById(@PathVariable Integer id){
        Phone phoneServiceById = phoneService.getById(id);
        return ResponseEntity.status(phoneServiceById != null ? 202 : 409).body(phoneServiceById);
    }

    @PostMapping
    public HttpEntity<?> addPhone(@RequestBody Phone phone){

        Phone addPhone = phoneService.addPhone(phone);
        return ResponseEntity.status(201).body(addPhone);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updatePhone(@PathVariable Integer id, @RequestBody Phone phone){
        Phone updatePhone = phoneService.updatePhone(id, phone);
        return ResponseEntity.status(updatePhone != null ? 202 : 409).body(updatePhone);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePhone(@PathVariable Integer id){
        boolean deletePhone = phoneService.deletePhone(id);
        if (deletePhone){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
