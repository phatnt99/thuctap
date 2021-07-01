package net.freetuts.frontend.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth0.jwt.JWT;

import net.freetuts.frontend.model.JwtResponse;
import net.freetuts.frontend.model.User;
import net.freetuts.frontend.services.UserService;

/**
 * The Class UserController.
 */
@Controller
public class UserController {

	/** The user service. */
	@Autowired
	UserService userService;

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@GetMapping("/admin")
	public String admin() {
		return "dashboard";
	}

	/**
	 * Login.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/admin/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	/**
	 * Login.
	 *
	 * @param user the user
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@PostMapping("/admin/login")
	public String login(@ModelAttribute User user, HttpServletRequest request,
			HttpServletResponse response) {

		JwtResponse jwtResponse = userService.login(user);

		String token  = jwtResponse.getToken();
		Cookie cookie = new Cookie("Token", token);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		Date issDay  = JWT.decode(token).getIssuedAt();
		Date expDate = JWT.decode(token).getExpiresAt();
		long diff    = getDateDiff(issDay, expDate, TimeUnit.SECONDS);
		cookie.setMaxAge((int) diff);
		cookie.setPath("/freetuts-frontend/admin");

		response.addCookie(cookie);

		return "redirect:/admin";
	}

	/**
	 * Logout.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@GetMapping("/admin/logout")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response) {

		Cookie cookie = new Cookie("Token", null);
		cookie.setMaxAge(0);

		response.addCookie(cookie);

		return "redirect:/admin/login";
	}

	/**
	 * Get a diff between two dates.
	 *
	 * @param startDate     the oldest date
	 * @param endDate     the newest date
	 * @param timeUnit     the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date startDate, Date endDate,
			TimeUnit timeUnit) {
		long diffInMillies = endDate.getTime() - startDate.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
