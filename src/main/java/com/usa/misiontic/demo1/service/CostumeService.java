package com.usa.misiontic.demo1.service;

import com.usa.misiontic.demo1.entities.Costume;
import com.usa.misiontic.demo1.entities.Reservation;
import com.usa.misiontic.demo1.repository.CostumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {
    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll(){
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id){
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume){
        if (costume.getId()==null){
            return costumeRepository.save(costume);
        }else {
            Optional<Costume> e = costumeRepository.getCostume(costume.getId());
            if (e.isPresent()){
                return costume;
            }else{
                return costumeRepository.save(costume);
            }
        }
    }

    public Costume update(Costume costume){
        if(costume.getId()!=null){
            Optional<Costume> e = costumeRepository.getCostume(costume.getId());
            if (e.isPresent())
            {if (costume.getName()!=null){
                e.get().setName(costume.getName());
            }
                if (costume.getBrand()!=null){
                    e.get().setBrand(costume.getBrand());
                }
                if (costume.getDescription()!=null){
                    e.get().setDescription(costume.getDescription());
                }
                if (costume.getYear()!=null){
                    e.get().setYear(costume.getYear());
                }
                if (costume.getCategory()!=null){
                    e.get().setCategory(costume.getCategory());
                }
                costumeRepository.save(e.get());
                return e.get();
            }else {
                return costume;
            }
        }else {
            return costume;
        }
    }




//    public boolean delete(int id){
//        boolean flag =false;
//        Optional<Costume>p= costumeRepository.getCostume(id);
//        if (p.isPresent()){
//            costumeRepository.delete(p.get());
//            flag=true;
//        }
//        return flag;
//    }

    public boolean deleteCustome (int id){
        Boolean d = getCostume(id).map(costume -> {
            costumeRepository.delete(costume);
            return true;
        }).orElse(false);
        return d;
    }


}
