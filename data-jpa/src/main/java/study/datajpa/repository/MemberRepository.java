package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.Member;
import study.datajpa.dto.UserDTO;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameAndAgeGreaterThan(String name, int age);

    @EntityGraph(attributePaths = {"team"})
    List<Member> findByName(@Param("name") String name);

    @Query("select m from Member m where m.name = :name and m.age > :age")
    List<Member> findMember(@Param("name") String name, @Param("age") int age);

    @Query("select m.name from Member m")
    List<String> findUsername();

    @Query("select new study.datajpa.dto.UserDTO(m.name, m.age, t.name) from Member m join m.team t")
    List<UserDTO> findUserDTO();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m where m.name in :names")
    List<Member> findByUserNames(@Param("names") Collection names);

    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}
