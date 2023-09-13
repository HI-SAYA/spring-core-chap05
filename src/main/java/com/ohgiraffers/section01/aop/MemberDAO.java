package com.ohgiraffers.section01.aop;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository // 컴포넌트 스캔을 통해 빈으로 등록된다.
public class MemberDAO {

    private final Map<Long, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();
        memberMap.put(1L, new MemberDTO(1L, "홍길동"));
        memberMap.put(2L, new MemberDTO(2L, "유관순"));
    }

    public Map<Long, MemberDTO> selectMembers() {
        return memberMap;
    }

    public MemberDTO selectMember(Long id) {

        MemberDTO returnMember = memberMap.get(id);

        if(returnMember == null) throw new RuntimeException("해당하는 id의 회원이 없습니다.");
        // aop에서 advice를 예외상황 테스트도 하기 위해 예외상황을 만들었다.
        return returnMember;
    }
}
