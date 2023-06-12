package uz.shakh.appphonecrud.service;

import jdk.incubator.foreign.Addressable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.shakh.appphonecrud.entity.Phone;
import uz.shakh.appphonecrud.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;
import java.util.zip.Adler32;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public List<Phone> getAllPhone(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Phone> phoneRepositoryAll = phoneRepository.findAll(pageable);
        return phoneRepositoryAll.getContent();
    }

    public Phone getById(Integer id){
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        return optionalPhone.orElse(null);
    }

    public Phone addPhone(Phone phone){
        return phoneRepository.save(phone);
    }

    public Phone updatePhone(Integer id, Phone phone){
        Optional<Phone> phoneOptional = phoneRepository.findById(id);
        if (phoneOptional.isPresent()){
            Phone updatePhone = phoneOptional.get();
            updatePhone.setCompanyName(phone.getCompanyName());
            updatePhone.setModel(phone.getModel());
            updatePhone.setPrice(phone.getPrice());
            Phone save = phoneRepository.save(updatePhone);
            return save;
        }
        return null;
    }

    public boolean deletePhone(Integer id){
        try {
        phoneRepository.deleteById(id);
        return true;
        }catch (Exception e){
            return false;
        }
    }
}
