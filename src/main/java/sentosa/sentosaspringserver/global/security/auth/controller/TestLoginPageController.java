package sentosa.sentosaspringserver.global.security.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class TestLoginPageController {

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri.partner}")
	private String partnerRedirectUri;

	@Value("${kakao.redirect-uri.client}")
	private String clientRedirectUri;

	@GetMapping("/page")
	public String loginPage(Model model) {
		String partnerLoginUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + partnerRedirectUri;
		String clientLoginUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + clientRedirectUri;

		model.addAttribute("partnerLoginUrl", partnerLoginUrl);
		model.addAttribute("clientLoginUrl", clientLoginUrl);

		return "login";
	}
}
