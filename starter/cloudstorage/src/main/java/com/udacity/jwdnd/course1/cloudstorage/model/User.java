package com.udacity.jwdnd.course1.cloudstorage.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
  private int id;
  @NotNull(message = "Username name cannot be empty")
  @Size(min = 6, max = 20, message = "Username must between 6 and 20")
  private String username;
  private String salt;
  @NotNull(message = "Password name cannot be empty")
  @Size(min = 6, max = 20, message = "Password must between 6 and 20")
  @Pattern(regexp = "^[a-zA-Z]\\w{6,20}$", message = "Password must contain letters (number and underscore can be used)")
  private String password;
  @NotNull(message = "First name cannot be empty")
  @Size(min = 1, max = 15, message = "First name must between 6 and 15")
  private String firstName;
  @NotNull(message = "Last name cannot be empty")
  @Size(min = 1, max = 15, message = "Last name must between 6 and 15")
  private String lastName;
}
