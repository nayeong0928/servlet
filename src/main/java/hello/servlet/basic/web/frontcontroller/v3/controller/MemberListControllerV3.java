package hello.servlet.basic.web.frontcontroller.v3.controller;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.v3.ControllerV3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> map) {

        List<Member> members=memberRepository.findAll();
        ModelView mv=new ModelView("members");
        mv.getModels().put("members", members);
        return mv;
    }
}
