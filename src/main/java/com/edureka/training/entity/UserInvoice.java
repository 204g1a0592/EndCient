package com.edureka.training.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class UserInvoice {
	
	
	    private Long invoiceid;
	    private Long userid;           // user reference
	    private Double totalamount;
	    private LocalDate purchasedate;
	    private Set<InvoiceItem> invoiceitems;
		public Long getInvoiceid() {
			return invoiceid;
		}
		public void setInvoiceid(Long invoiceid) {
			this.invoiceid = invoiceid;
		}
		public Long getUserid() {
			return userid;
		}
		public void setUserid(Long userid) {
			this.userid = userid;
		}
		public Double getTotalamount() {
			return totalamount;
		}
		public void setTotalamount(Double totalamount) {
			this.totalamount = totalamount;
		}
		public LocalDate getPurchasedate() {
			return purchasedate;
		}
		public void setPurchasedate(LocalDate purchasedate) {
			this.purchasedate = purchasedate;
		}
		public Set<InvoiceItem> getInvoiceitems() {
			return invoiceitems;
		}
		public void setInvoiceitems(Set<InvoiceItem> invoiceitems) {
			this.invoiceitems = invoiceitems;
		}
	    
	}



