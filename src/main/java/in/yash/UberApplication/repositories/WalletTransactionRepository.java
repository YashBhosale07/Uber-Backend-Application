package in.yash.UberApplication.repositories;

import in.yash.UberApplication.entities.WalletTranscation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTranscation,Long> {
}
