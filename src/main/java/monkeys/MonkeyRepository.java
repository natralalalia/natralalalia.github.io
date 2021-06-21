package monkeys;

import org.springframework.data.jpa.repository.JpaRepository;

interface MonkeyRepository extends JpaRepository<Monkey, Long> {

}