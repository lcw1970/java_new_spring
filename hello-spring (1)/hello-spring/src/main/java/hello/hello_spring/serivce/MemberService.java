package hello.hello_spring.serivce;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
   private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long join(Member member){
       memberRepository.findByName(member.getName())
              .ifPresent(m -> {
                   throw new IllegalStateException("이미 존재하는 회원입니다.");
               });
       memberRepository.save(member);
       return member.getId();
   }

   public List<Member> findMembers() {
       return memberRepository.findAll();
   }

   public Optional<Member> findOne(Long id){
       return memberRepository.findById(id);
   }

}
