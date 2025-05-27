package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 애플리케이션 로딩 시점에 딱 1번만 호출
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");

        // 엔티티 매니저는 쓰레드 간에 공유 X, 사용하고 버려야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");

//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());
//            findMember.setName("HelloJPA"); // 변경감지

            // JPA의 모든 데이터 변경은 트랜잭션 내에서 실행된다!
//            em.persist(member);

            /*
             * JPQL(테이블이 아닌 `객체`를 대상으로 쿼리)
             * JPA는 SQL(테이블을 대상으로 쿼리)을 추상화한 JPQL을 제공
             * -> 특정 데이터베이스 SQL에 의존하지 않는다!
             * JPA를 사용하면 엔티티 객체를 중심으로 개발
             * 문제는 검색 쿼리 !
             * 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
             * 애플리케이션이 필요한 데이터만 DB에서 가져오려면
             * 결국 검색 조건이 포함된 SQL이 필요하다!
             */
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();

//            for (Member member : result) {
//                System.out.println("member = " + member.getName());
//            }

            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            //영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L);
//
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());

            //영속
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L); // 쿼리가 안나간다.
//
//            System.out.println("result1 = " + (findMember1 == findMember2));

            /*
             * Transaction 쓰기 지연
             */
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("=====================");

            /*
             * Dirty Checking
             */
//            Member member = em.find(Member.class, 150L);
//            member.setName("UPDATE");

//            Member member1 = new Member();
//            member1.setUsername("A");
//
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            Member member3 = new Member();
//            member3.setUsername("C");
//
//            System.out.println("=====================");
//
//            em.persist(member1); //1, 51 : 50까지 미리 더미로 호출
//            // 51를 만나기 전엔 `call next value`를 한 번만
//            em.persist(member2); //MEM
//            em.persist(member3); //MEM
//
//            // 내부적인 로직 덕분에 select 없이 insert 쿼리만으로 값 조회해올 수 있다.
//            System.out.println("member1.id" + member1.getId());
//            System.out.println("member2.id" + member2.getId());
//            System.out.println("member3.id" + member3.getId());



//            System.out.println("=====================");
//
//            Member member = new Member();
//            member.setUserName("member1");
//
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("team1");
//            //
//            team.getMembers().add(member);
//
//            em.persist(team);

            tx.commit();


            // Movie movie = new Movie();
            // movie.setDirector("aaaa");
            // movie.setActor("bbbb");
            // movie.setName("바람과 함께 사라지다");
            // movie.setPrice(10000);
            // em.persist(movie);
            //
            // em.flush();
            // em.clear();
            //
            // Movie findMovie = em.find(Movie.class, movie.getId());
            // System.out.println("findMovie = " + findMovie);

            // Member member = new Member();
            // member.setUsername("member1");
            //
            // em.persist(member);
            //
            // Team team = new Team();
            // team.setName("teamA");
            // //
            // team.getMembers().add(member);
            //
            // em.persist(team);


            // Team team = new Team();
            // team.setName("TeamA");
            // // team.getMembers().add(member);
            // em.persist(team);
            //
            // Member member = new Member();
            // member.setUsername("member1");
            // // member.setTeamId(team.getId());
            // member.setTeam(team);
            // em.persist(member);
            //
            // em.flush();
            // em.clear();
            //
            // Member findMember = em.find(Member.class, member.getId());
            // List<Member> members = findMember.getTeam().getMembers();
            //
            // for (Member m : members) {
            // 	System.out.println("m = " + m.getUsername());
            // }

            Member member = new Member();
            member.setUserName("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}