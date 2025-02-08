package sentosa.sentosaspringserver.domain.partner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.domain.partner.dto.PartnerRequestDto;
import sentosa.sentosaspringserver.domain.partner.dto.PartnerResponseDto;
import sentosa.sentosaspringserver.domain.partner.service.PartnerAdminService;

@Tag(name = "파트너 (Partner)", description = "파트너 CRUD API")
@RestController
@RequestMapping("/v1/partner")
@RequiredArgsConstructor
public class PartnerAdminController {
	private final PartnerAdminService partnerService;

	@Operation(summary = "파트너 생성")
	@PostMapping
	public ResponseEntity<PartnerResponseDto> createPartner(
		@RequestBody @Valid PartnerRequestDto partnerAdminRequestDto
	) {
		PartnerResponseDto responseDto = partnerService.createPartner(partnerAdminRequestDto);
		return ResponseEntity.ok(responseDto);
	}
}
