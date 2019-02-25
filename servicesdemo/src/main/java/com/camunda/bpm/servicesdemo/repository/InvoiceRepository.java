package com.camunda.bpm.servicesdemo.repository;

import com.camunda.bpm.servicesdemo.entity.accountspayable.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}