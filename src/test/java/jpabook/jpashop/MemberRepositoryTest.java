package jpabook.jpashop;

import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // Test에 있으면 Test 끝난 뒤에 Rollback 해버림
    @Rollback(false)
    public void testMember () {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member); // opt + command + v
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); // command + d
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername()); // command + d
        Assertions.assertThat(findMember).isEqualTo(member);
        // 같은 transaction 내 저장 및 조회 -> 동일 persistence context
        // 동일 persistence context 안에서는 id가 같으면 같은 Entity
        // 1차 cache에서 꺼내옴(No select query)
        System.out.println("findMember == member: " + (findMember == member));
    }
}