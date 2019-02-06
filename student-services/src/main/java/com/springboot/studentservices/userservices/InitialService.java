/**
 * 
 */
package com.springboot.studentservices.userservices;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.studentservices.entities.Companies;
import com.springboot.studentservices.entities.Stock;
import com.springboot.studentservices.repositories.CompaniesRepository;
import com.springboot.studentservices.repositories.StockRepository;

/**
 * @author my
 *
 */
@Service
public class InitialService {

	
	@Autowired
	private CompaniesRepository companyRepo;
	
	@Autowired
	private StockRepository stockRepo;
	
	@PostConstruct
	public void setup() {
		 // Cleanup the tables
        companyRepo.deleteAllInBatch();
        stockRepo.deleteAllInBatch();

        // =======================================

        // Create a Company --> testing admin / user roles
        Companies company = new Companies("The Wood Shavings Company",
                "Bulstat-Primer-001",
                "Vetovo","0886445573","sakito1234@abv.bg","H545G12");

        // Create two tags
        Stock stock1 = new Stock("Head&Shoulders Shampoo","Anti-dandruff shampoo","KG","100", (float) 2.45);
        Stock stock2 = new Stock("Lacalut","Anti-bleeding toothpaste,designed to strengthen your teeth!","KG","200", (float) 3.70);


        // Add stock references in the post
        company.getStocks().add(stock1);
        company.getStocks().add(stock2);

        // Add company reference in the tags
        stock1.getCompanies().add(company);
        stock2.getCompanies().add(company);

        companyRepo.save(company);

        // =======================================
	}

}
