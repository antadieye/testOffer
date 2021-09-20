package com.app.testoffer.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")
	@GenericGenerator(name = "UserIdGenerator", strategy = "native")
	@Column(name = "ID", nullable = false, insertable = true, updatable = false)
	private long id;

	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Column(name = "BIRTH_DATE", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "birthDate is mandatory")
	@Past(message = "birthDate must be a date in the past")
	private LocalDate birthDate;

	@Column(name = "COUNTRY_RESIDENCE", nullable = false)
	@NotBlank(message = "Country of residence is mandatory")
	private String countryResidence;

	@Column(name = "PHONE_NUMBER", nullable = true)
	private String phoneNumber;

	@Column(name = "GENDER", nullable = true)
	private String gender;
	
	@Column(name = "EMAIL", nullable = true)
	private String email;
}
