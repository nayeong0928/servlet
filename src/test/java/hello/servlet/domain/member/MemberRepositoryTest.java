package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    @DisplayName("이름이 hello, 나이가 20인 회원을 저장하고, 제대로 저장되었는지 확인한다.")
    void save(){
        //given
        Member member=new Member("hello", 20);

        //when
        Member saveMember=memberRepository.save(member);

        //then
        Member findMember=memberRepository.findById(member.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    @DisplayName("회원 두 명을 저장하고, 회원 전체를 조회하여 제대로 저장되었는지 확인한다.")
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result=memberRepository.findAll();

        //then
        Assertions.assertThat(result).contains(member1, member2);
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}