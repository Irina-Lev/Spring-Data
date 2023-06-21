package com.product.star.homework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner contactDao(ContactRepository  contactRepository) {
        return args -> {
            contactRepository.deleteAllInBatch();

            var contact1 = new Contact("Jackie", "Chan", "jchan@gmail.com", "1234567890");
            var contact1Id = contactRepository.save(contact1).getId();
            var contact2 = new Contact( "Maria", "Ivanova", "mivanova@gmail.com", "7654321");
            contactRepository.save(contact2);

            var contacts = contactRepository.findAll();
            System.out.println(contacts);

            var newPhone = "777-77-77";
            contactRepository.updatePhone(contact1Id, newPhone);
            var newEmail = "cap@gmail.com";
            contactRepository.updateEmail(contact1Id, newEmail);
            var updatedContact = contactRepository.findById(contact1Id)
                    .orElseThrow(() -> new IllegalArgumentException());
            System.out.println(updatedContact);
        };
    }
}
