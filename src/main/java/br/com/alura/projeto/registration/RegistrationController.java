package br.com.alura.projeto.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationService registrationService, RegistrationRepository registrationRepository) {
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping("/registration/new")
    public ResponseEntity<?> createRegistration(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        try {
            registrationService.enrollStudent(newRegistration);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {

        List<Object[]> results = registrationRepository.getReportData();

        List<RegistrationReportItem> items = results.stream()
                .map(r -> new RegistrationReportItem(
                        (String) r[0],
                        (String) r[1],
                         r[5].toString(),
                        (String) r[2],
                        (String) r[3],
                        (Long) r[4]
                ))
                .toList();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/registration/report/inactive")
    public ResponseEntity<List<RegistrationReportItem>> reportInactive() {

        List<Object[]> results = registrationRepository.getInactiveReportData();

        List<RegistrationReportItem> items = results.stream()
                .map(r -> new RegistrationReportItem(
                        (String) r[0],
                        (String) r[1],
                        r[5].toString(),
                        (String) r[2],
                        (String) r[3],
                        (Long) r[4]
                ))
                .toList();

        return ResponseEntity.ok(items);
    }
}
