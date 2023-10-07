package br.ufsm.csi.app.controller;

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
import br.ufsm.csi.app.service.ShippingCompanyService;

@RestController
@RequestMapping("/shipping-company")
public class ShippingCompanyController {

    @Autowired
    private ShippingCompanyService shippingCompanyService;

    @GetMapping()
    public List<ShippingCompany> getShippingCompanies() {
        return shippingCompanyService.getShippingCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingCompany> getShippingCompany(@PathVariable Long id) {
        return shippingCompanyService.getShippingCompany(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public int desactiveShippingCompany(@PathVariable Long id) {
        return shippingCompanyService.desactiveShippingCompany(id);
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<?> newShippingCompany(
            @RequestBody ShippingCompany values,
            UriComponentsBuilder uBuilder) {
        return shippingCompanyService.newShippingCompany(values, uBuilder);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<ShippingCompany> updateShippingCompany(@PathVariable Long id,
            @RequestBody ShippingCompany values) {
        return shippingCompanyService.updateShippingCompany(id, values);
    }

}
