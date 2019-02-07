/**
 * 
 */
package com.springboot.studentservices.userservices;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.studentservices.entities.Companies;
import com.springboot.studentservices.entities.Stock;
import com.springboot.studentservices.entities.User;
import com.springboot.studentservices.repositories.CompaniesRepository;
import com.springboot.studentservices.repositories.RoleRepository;
import com.springboot.studentservices.repositories.StockRepository;
import com.springboot.studentservices.repositories.UserRepository;

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
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@PostConstruct
	public void setup() {
		// Password encoder instance
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// Cleanup the tables
        companyRepo.deleteAllInBatch();
        stockRepo.deleteAllInBatch();
        roleRepo.deleteAllInBatch();
        userRepo.deleteAllInBatch();

        // =======================================
        
        // Create a User
        User user = new User();
        
        // Encode our password
        String encPass = encoder.encode("H545G12");

        // Create a Company --> testing admin / user roles
        Companies company = new Companies("The Wood Shavings Company",
                "Bulstat-Primer-001",
                "Vetovo","0886445573","sakito1234@abv.bg",encPass);

        // Create two tags
        Stock stock1 = new Stock("Head&Shoulders Shampoo","Anti-dandruff shampoo","KG","100", (float) 2.45);
        Stock stock2 = new Stock("Lacalut","Anti-bleeding toothpaste,designed to strengthen your teeth!","KG","200", (float) 3.70);


        // Add stock references in the post
        company.getStocks().add(stock1);
        company.getStocks().add(stock2);

        // Add company reference in the tags
        stock1.getCompanies().add(company);
        stock2.getCompanies().add(company);
        
        // Add company data to Users table
        user.setUsername(company.getEmail());
        user.setPassword(encPass);
        userRepo.save(user);
        
        // Save the user data to the Company table
        companyRepo.save(company);

        // =======================================
	}

}
