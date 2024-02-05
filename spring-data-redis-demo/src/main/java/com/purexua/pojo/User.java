package com.purexua.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String name;
  @SerializedName("age")
  private Integer age;
  private Integer sex;
}
