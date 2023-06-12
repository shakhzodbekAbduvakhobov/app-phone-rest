package uz.shakh.appphonecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shakh.appphonecrud.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
