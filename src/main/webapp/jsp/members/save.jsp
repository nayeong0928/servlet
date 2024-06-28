<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository memberRepository=MemberRepository.getInstance();
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));
  Member member=new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공!
<ul>
  <li>아이디: <%=member.getId()%></li>
  <li>이름: <%=member.getUsername()%></li>
  <li>나이: <%=member.getAge()%></li>
</ul>
</body>
</html>
