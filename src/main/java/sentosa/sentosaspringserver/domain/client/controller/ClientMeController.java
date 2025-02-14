package sentosa.sentosaspringserver.domain.client.controller;

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
import sentosa.sentosaspringserver.domain.client.dto.request.ClientProfileUpdateRequestDto;
import sentosa.sentosaspringserver.domain.client.dto.response.ClientProfileResponseDto;
import sentosa.sentosaspringserver.domain.client.service.ClientService;
import sentosa.sentosaspringserver.global.security.util.UserDetailsImpl;

@RestController
@RequestMapping("/v1/client/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
@Tag(name = "[클라이언트] 마이페이지 설정", description = "마이페이지 정보 조회 및 수정하기")
public class ClientMeController {
	private final ClientService clientService;

	@GetMapping
	@Operation(summary = "클라이언트 마이페이지 조회")
	public ResponseEntity<ClientProfileResponseDto> getProfile(
		@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		return ResponseEntity.ok(clientService.getClientProfile(userDetails.getUserId()));
	}

	@PutMapping
	@Operation(summary = "클라이언트 마이페이지 수정")
	public ResponseEntity<ClientProfileResponseDto> updateProfile(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody ClientProfileUpdateRequestDto requestDto
	) {
		return ResponseEntity.ok(clientService.updateClientProfile(requestDto, userDetails.getUserId()));
	}
}
