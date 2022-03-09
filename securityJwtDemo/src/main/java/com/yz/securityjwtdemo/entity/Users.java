package com.yz.securityjwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable, UserDetails {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Max(value = 10000, message = "最大10000")
    @Min(value = 1, message = "最小1")
    @NotNull(message = "不能为空")

    private Integer id;

    @NotBlank(message = "不能为空")
    @Size(max = 16, min = 6, message = "字符串长度需要在6-16之间")
    private String username;

    @NotBlank(message = "不能为空")
    @Size(max = 18, min = 6, message = "字符串长度需要在6-18之间")
    private String pwd;

    private String remake;
    private RoleInfo roleInfo;

    private String menuus;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return Arrays.asList(new SimpleGrantedAuthority(menuus));
    }

    @Override
    public String getPassword() {
      return pwd;
    }

    @Override
    public String getUsername() {
      return username;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    public String getRoleName() {
		return roleInfo.getRoleName();
	}


}