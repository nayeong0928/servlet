package hello.servlet.basic.web.frontcontroller.v3.controller;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.v3.ControllerV3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.HashMap;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> map) {
        String username=map.get("username");
        int age=Integer.parseInt(map.get("age"));
        Member member = memberRepository.save(new Member(username, age));

        ModelView mv=new ModelView("save-result");
        Map<String, Object> model=new HashMap<>();
        mv.getModels().put("member", member);
        return mv;
    }
}
