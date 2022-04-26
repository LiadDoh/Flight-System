package com.FlightsSystem.ContactUs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contactus")
public class ContactUsDTO {

    @Id
    @GeneratedValue
    private int messageId;
    private String senderName;
    private String content;


}
