package hello.servlet.basic.web.frontcontroller.v4.controller;

import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> params, Map<String, Object> models) {

        List<Member> members=memberRepository.findAll();
        models.put("members", members);
        return "members";
    }
}
