package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository  extends JpaRepository<QRCode, Long> {
}
