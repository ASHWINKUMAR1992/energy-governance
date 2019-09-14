package com.ashwin.energygovernance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ashwin
 *
 */
@SpringBootApplication(scanBasePackages = "com.ashwin")
public class EnergyGovernanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyGovernanceApplication.class, args);
    }

}
