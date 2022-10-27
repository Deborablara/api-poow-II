package br.ufsm.csi.app.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.csi.app.model.ShippingCompany;
import br.ufsm.csi.app.repository.ShippingCompanyRepository;


@RestController
@RequestMapping("/shipping-company")
public class ShippingCompanyController {

    @Autowired
    private ShippingCompanyRepository shippingCompanyRepository;

    @GetMapping()
    public List<ShippingCompany> getShippingCompanies(){
        List<ShippingCompany> shippingCompanies = shippingCompanyRepository.findAll();

        return shippingCompanies;
    }

    @GetMapping("/{id}")
    public ShippingCompany getShippingCompany(@PathVariable Long id){
        ShippingCompany shippingCompany = shippingCompanyRepository.getReferenceById(id);

        return shippingCompany;
    }

    @PutMapping("/{id}")
    @Transactional
    public int desactiveShippingCompany(@PathVariable Long id){
        return shippingCompanyRepository.desactiveShippingCompany(id);

    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<?> newShippingCompany(
        @RequestBody ShippingCompany values,
         UriComponentsBuilder uBuilder){
        ShippingCompany shippingCompany = new ShippingCompany(values.getName(), values.getActive());

        shippingCompanyRepository.save(shippingCompany);
        URI uri = uBuilder.path("/shipping-company/{id}").buildAndExpand(shippingCompany.getId()).toUri();

        return ResponseEntity.created(uri).body(shippingCompany);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<ShippingCompany> updateShippingCompany(@PathVariable Long id, @RequestBody ShippingCompany values){
        ShippingCompany shippingCompany = shippingCompanyRepository.getReferenceById(id);

        shippingCompany.setName(values.getName());
        shippingCompany.setActive(values.getActive());

        return ResponseEntity.ok(shippingCompany);

    }
    
}
