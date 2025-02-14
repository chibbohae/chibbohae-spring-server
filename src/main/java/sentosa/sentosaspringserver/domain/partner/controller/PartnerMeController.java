package sentosa.sentosaspringserver.domain.partner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.domain.partner.dto.request.PartnerProfileUpdateRequestDto;
import sentosa.sentosaspringserver.domain.partner.dto.response.PartnerProfileResponse;
import sentosa.sentosaspringserver.domain.partner.service.PartnerService;
import sentosa.sentosaspringserver.global.security.util.UserDetailsImpl;

@RestController
@RequestMapping("/v1/partner/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_PARTNER')")
@Tag(name = "[파트너] 마이페이지 설정", description = "마이페이지 정보 조회 및 수정하기")
public class PartnerMeController {
	private final PartnerService partnerService;

	@GetMapping
	@Operation(summary = "파트너 마이페이지 조회")
	public ResponseEntity<PartnerProfileResponse> getProfile(
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		return ResponseEntity.ok(partnerService.getPartnerProfile(userDetails.getUserId()));
	}

	@PutMapping
	@Operation(summary = "파트너 마이페이지 수정")
	public ResponseEntity<PartnerProfileResponse> updateProfile(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody PartnerProfileUpdateRequestDto requestDto
	) {
		return ResponseEntity.ok(partnerService.updatePartnerProfile(requestDto, userDetails.getUserId()));
	}
}
