package hello.servlet.basic.web.frontcontroller.v4.controller;

import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> params, Map<String, Object> models) {
        String username=params.get("username");
        int age=Integer.parseInt(params.get("age"));
        Member member = memberRepository.save(new Member(username, age));

        models.put("member", member);
        return "save-result";
    }
}
