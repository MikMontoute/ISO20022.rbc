package com.rbc.iso20022.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.rbc.iso20022.entity.Customer;
import com.rbc.iso20022.entity.PaymentTransaction;
import com.rbc.iso20022.model.Pacs008;
import com.rbc.iso20022.repository.CustomerRepository;
import com.rbc.iso20022.repository.PaymentRepository;




@Service
public class PaymentService {


private final CustomerRepository customers;
private final PaymentRepository payments;
private final AuditService audit;
public boolean stat;

public PaymentService(
CustomerRepository customers,
PaymentRepository payments,
AuditService audit
){

this.customers=customers;
this.payments=payments;
this.audit=audit;
}



public void process(Pacs008 p){



if(payments.existsByMessageId(p.getMessageId()))
{

	audit.record(
			"Duplicate Transaction",
			p.getMessageId(),
			"Failed"
			);
	
	stat=false;
}
else {


// debtor customer

Customer debtor =
customers
.findByCustomerName(
p.getDebtor()
)
.orElseGet(
Customer::new
);


debtor.setCustomerName(
p.getDebtor()
);


debtor.setLastTransactionId(
p.getTransactionId()
);


customers.save(debtor);




// transaction


PaymentTransaction tx =
new PaymentTransaction();


tx.setMessageId(
p.getMessageId()
);


tx.setTransactionId(
p.getTransactionId()
);


tx.setDebtor(
p.getDebtor()
);


tx.setCreditor(
p.getCreditor()
);


tx.setAmount(
p.getAmount()
);


tx.setCreated(
LocalDateTime.now()
);

stat=true;

payments.save(tx);
audit.record(
"PAYMENT_COMPLETED",
p.getMessageId(),
"SUCCESS"
);

}

}

}