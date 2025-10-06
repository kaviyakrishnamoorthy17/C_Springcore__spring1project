package com.tnsif.placement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tsinf.placement", "com.tnsif.PlacementManagement_Student"})
public class PlacementManagementApplication{   // ✅ Class name corrected

    public static void main(String[] args) {
        SpringApplication.run(PlacementManagementApplication.class, args);  // ✅ Updated reference
    }
}