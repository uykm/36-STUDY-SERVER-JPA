package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "member_seq",
//        initialValue = 1, allocationSize = 50) // Table allocationSize도 동일한 전략
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member extends BaseEntity {

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "MEMBER_SEQ_GENERATOR")
    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

//    // EnumType.ORDINAL
//    // : enum의 순서값으로 저장하기 때문에,
//    // enum의 순서가 바뀌면 DB에 저장된 값과 매핑이 안될 수 있다.
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    // 과거 버전 방식
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
    ////    private LocalDateTime createdDateTime;
    ////    private LocalDateTime lastModifiedDateTime;
//
//    @Lob // Large Object
//    private String description;

    // @Transient : DB에 저장 X

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    // insertable = false, updatable = false : Member 테이블에 TEAM_ID 컬럼이 없기 때문에
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany // 다대다 X
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();



    public Member() {}

    public Member(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}